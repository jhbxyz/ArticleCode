package com.aboback.articlecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 首次安装立即打开,后续切后台,没有杀死 APP 的情况下,会触发反复进入 launcher Activity 的 bug
        if ((intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }

        setContentView(R.layout.activity_main)
    }
}
