package com.aboback.articlecode.okhttp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.aboback.articlecode.log
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

/**
 * @author jhb
 * @date 2020/9/7
 */
class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //总体请求的 Client
        val okHttpClient = OkHttpClient()
        //构造出一个 Request 对象
        val request = Request.Builder()
            //API 接口由 wanandroid.com 提供
            .url("https://wanandroid.com/wxarticle/chapters/json")
            .get()
            .build()
        //创建出一个执行的 Call 对象
        val call = okHttpClient.newCall(request)
        //异步执行请求
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                "response.code = ${response.code}".log()
            }
        })

        //同步执行
        thread {
            try {
                val response = call.execute()
                "response.code = ${response.code}".log()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}