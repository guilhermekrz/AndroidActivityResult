package com.brokoli.activityresult

sealed class ActivityResult {
    object Unknown: ActivityResult()
    object Success: ActivityResult()
    object Error: ActivityResult()
}