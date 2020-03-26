package com.brokoli.activityresult.cleanResultApi

import android.content.Intent
import androidx.activity.invoke
import androidx.activity.result.ActivityResultRegistry

class ActivityAController(registry : ActivityResultRegistry) {

    val activityResult: ActivityResultLiveData = ActivityResultLiveData(registry)

    fun startActivityClicked(intent: Intent) {
        activityResult.startForResult(intent)
    }

}