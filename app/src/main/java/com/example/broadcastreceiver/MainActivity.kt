package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val MY_MESSAGE = "com.givemepass.sendmessage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(mBroadcast, IntentFilter(MY_MESSAGE))
        send_broadcast.setOnClickListener {
            val intent = Intent()
            intent.action = MY_MESSAGE
            sendBroadcast(intent)
        }
    }

    private val mBroadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (MY_MESSAGE == intent?.action) {
                AlertDialog.Builder(this@MainActivity)
                        .setMessage("收到訊息!")
                        .setPositiveButton("確定") { _, _ -> }
                        .show()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mBroadcast)
    }
}