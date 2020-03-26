package com.brokoli.activityresult.cleanResultApi

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import com.brokoli.activityresult.ActivityResult

class ActivityResultLiveData(private val registry : ActivityResultRegistry) : LiveData<ActivityResult>() {

    companion object {
        private const val START_FOR_RESULT_KEY = "START_FOR_RESULT_KEY"
    }

    lateinit var startForResult : ActivityResultLauncher<Intent>

    override fun onActive() {
        value = ActivityResult.Unknown
        prepareStartForResult()
    }

    override fun onInactive() {
        startForResult.dispose()
    }

    private fun prepareStartForResult() {
        startForResult = registry.registerActivityResultCallback(START_FOR_RESULT_KEY,
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            value = when(result.resultCode) {
                Activity.RESULT_OK -> ActivityResult.Success
                Activity.RESULT_CANCELED -> ActivityResult.Error
                else -> ActivityResult.Unknown
            }
        }
    }
}