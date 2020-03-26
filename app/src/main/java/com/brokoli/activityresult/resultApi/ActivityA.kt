package com.brokoli.activityresult.resultApi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.invoke
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CheckResult
import com.brokoli.activityresult.ActivityB
import com.brokoli.activityresult.R
import com.brokoli.activityresult.cleanResultApi.ActivityA
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : ComponentActivity() {

    companion object {
        @CheckResult
        fun newIntent(context: Context): Intent {
            return Intent(context, ActivityA::class.java)
        }
    }

    private val startForResult = prepareCall(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        resultTextView.setText(when(result.resultCode) {
            Activity.RESULT_OK -> R.string.result_success
            Activity.RESULT_CANCELED -> R.string.result_canceled
            else -> R.string.result_unknown
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        startActivityButton.setOnClickListener {
            startForResult(ActivityB.newIntent(this))
        }
    }

}