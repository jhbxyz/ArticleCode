package com.jhb.androidplus5.hencoderplus.day1_5http.retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author jhb
 * @date 2020/7/31
 */
interface GitHubApiService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo>>

    //使用 Rxjava
    @GET("users/{user}/repos")
    fun listReposRx(@Path("user") user: String?): Single<List<Repo>>

    //使用 Kotlin 协程
    @GET("users/{user}/repos")
    suspend fun listReposKt(@Path("user") user: String?): List<Repo>


    //如果 GitHub 服务器访问异常,可以使用此测试接口,接口有 wanandroid 网站提供
    @GET("https://wanandroid.com/wxarticle/chapters/json")
    fun test(): Call<Any>

    //如果 GitHub 服务器访问异常,可以使用此测试接口,接口有 wanandroid 网站提供
    @GET("https://wanandroid.com/wxarticle/chapters/json")
    fun testRx(): Single<Any>

    //如果 GitHub 服务器访问异常,可以使用此测试接口,接口有 wanandroid 网站提供
    @GET("https://wanandroid.com/wxarticle/chapters/json")
    suspend fun testKt(): Any

}