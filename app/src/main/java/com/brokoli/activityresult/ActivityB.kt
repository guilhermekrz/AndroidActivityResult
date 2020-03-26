package com.brokoli.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.CheckResult
import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : ComponentActivity() {

    companion object {
        @CheckResult
        fun newIntent(context: Context): Intent {
            return Intent(context, ActivityB::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        sendSuccessResultButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        sendCanceledResultButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}