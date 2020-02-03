package com.sanmidev.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notification = Notification(this)

        btn_notify.setOnClickListener {
            notification.fireNotification()
        }

        btn_cancel.setOnClickListener {
            notification.cancelNotification()
        }

        btn_update.setOnClickListener {
            notification.fireSecondNotification()
        }

    }
}
