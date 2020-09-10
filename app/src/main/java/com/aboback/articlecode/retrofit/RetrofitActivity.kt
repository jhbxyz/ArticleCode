package com.aboback.articlecode.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aboback.articlecode.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author jhb
 * @date 2020/7/31
 */
class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化一个Retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //创建出GitHubApiService对象
        val service = retrofit.create(GitHubApiService::class.java)

        //普通的请求方式
        normalRequest(service)
        //RxJava的请求方式
//        rxJavaRequest(service)
        //Kotlin协程的请求方式
//        ktRequest(service)

    }

    /**
     * Kotlin协程的请求方式
     */
    private fun ktRequest(service: GitHubApiService) {
        //lifecycle提供的协程的Scope，因为suspend 函数需要运行在协程里面
        lifecycleScope.launchWhenResumed {
            try {
                val repo = service.listReposKt("octocat")
                "response name = ${repo[0].name}".log()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * RxJava的请求方式
     */
    @SuppressLint("CheckResult")
    private fun rxJavaRequest(service: GitHubApiService) {
        service.listReposRx("octocat")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repo ->
                "response name = ${repo[0].name}".log()
            }, { error ->
                error.printStackTrace()
            })

    }

    /**
     * 普通的请求方式
     */
    private fun normalRequest(service: GitHubApiService) {
        //返回一个 Call 对象
        val repos = service.listRepos("octocat")
        //调用 enqueue 方法在回调方法里处理结果
        repos.enqueue(object : Callback<List<Repo>?> {
            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                "response.code() = ${response.code()}".log()
            }
        })
    }


}