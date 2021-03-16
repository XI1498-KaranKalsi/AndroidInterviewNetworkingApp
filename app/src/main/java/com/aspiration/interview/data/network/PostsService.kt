package com.aspiration.interview.data.network.service

import com.aspiration.interview.data.models.Post
import com.aspiration.interview.data.models.PostsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}