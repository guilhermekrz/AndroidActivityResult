package com.brokoli.activityresult.startActivityForResult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.CheckResult
import com.brokoli.activityresult.ActivityB
import com.brokoli.activityresult.R
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : ComponentActivity() {

    companion object {
        private const val ACTIVITY_A_REQUEST_CODE = 4132

        @CheckResult
        fun newIntent(context: Context): Intent {
            return Intent(context, ActivityA::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        startActivityButton.setOnClickListener {
            startActivityForResult(ActivityB.newIntent(this), ACTIVITY_A_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ACTIVITY_A_REQUEST_CODE) {
            resultTextView.setText(when(resultCode) {
                Activity.RESULT_OK -> R.string.result_success
                Activity.RESULT_CANCELED -> R.string.result_canceled
                else -> R.string.result_unknown
            })
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}