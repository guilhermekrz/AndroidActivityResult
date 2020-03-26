package com.brokoli.activityresult.cleanLifecycleResultApi

import android.app.Activity
import android.content.Intent
import androidx.activity.invoke
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.brokoli.activityresult.ActivityResult

class ActivityAController(private val registry : ActivityResultRegistry) : DefaultLifecycleObserver {

    companion object {
        private const val START_FOR_RESULT_KEY = "START_FOR_RESULT_KEY"
    }

    private lateinit var startForResult : ActivityResultLauncher<Intent>
    val activityResult: MutableLiveData<ActivityResult> by lazy { MutableLiveData<ActivityResult>(ActivityResult.Unknown) }

    override fun onCreate(owner: LifecycleOwner) {
        prepareStartForResult(owner)
    }

    fun startActivityClicked(intent: Intent) {
        startForResult(intent)
    }

    private fun prepareStartForResult(owner: LifecycleOwner) {
        startForResult = registry.registerActivityResultCallback(START_FOR_RESULT_KEY, owner, ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
            activityResult.value = when(result.resultCode) {
                Activity.RESULT_OK -> ActivityResult.Success
                Activity.RESULT_CANCELED -> ActivityResult.Error
                else -> ActivityResult.Unknown
            }
        })
    }

}