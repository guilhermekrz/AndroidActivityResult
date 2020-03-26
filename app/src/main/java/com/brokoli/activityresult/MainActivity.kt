package com.brokoli.activityresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivityForResultButton.setOnClickListener {
            val intent = com.brokoli.activityresult.startActivityForResult.ActivityA.newIntent(this)
            startActivity(intent)
        }
        resultApiButton.setOnClickListener {
            val intent = com.brokoli.activityresult.resultApi.ActivityA.newIntent(this)
            startActivity(intent)
        }
        cleanLifecycleResultApiButton.setOnClickListener {
            val intent = com.brokoli.activityresult.cleanLifecycleResultApi.ActivityA.newIntent(this)
            startActivity(intent)
        }
        cleanResultApiButton.setOnClickListener {
            val intent = com.brokoli.activityresult.cleanResultApi.ActivityA.newIntent(this)
            startActivity(intent)
        }
    }

}