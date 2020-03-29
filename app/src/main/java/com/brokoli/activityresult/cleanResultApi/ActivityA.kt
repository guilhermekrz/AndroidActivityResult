package com.brokoli.activityresult.cleanResultApi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.CheckResult
import androidx.lifecycle.Observer
import com.brokoli.activityresult.ActivityB
import com.brokoli.activityresult.ActivityResult
import com.brokoli.activityresult.R
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : ComponentActivity() {

    private lateinit var controller : ActivityAController
    private val activityResultObserver: Observer<ActivityResult> = Observer { result ->
        resultTextView.text = getString(when(result) {
            ActivityResult.Unknown -> R.string.result_unknown
            ActivityResult.Success -> R.string.result_success
            ActivityResult.Error -> R.string.result_canceled
        })
    }

    companion object {
        @CheckResult
        fun newIntent(context: Context): Intent {
            return Intent(context, ActivityA::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        startActivityButton.setOnClickListener {
            controller.startActivityClicked(ActivityB.newIntent(this))
        }

        controller = ActivityAController(activityResultRegistry)
        controller.activityResult.observeForever(activityResultObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.activityResult.removeObserver(activityResultObserver)
    }

}
