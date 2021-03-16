package com.aspiration.interview.domain.repository

import com.aspiration.interview.data.models.Post
import io.reactivex.Flowable
import io.reactivex.Observable

interface PostsRepository {
    fun getPosts(): Observable<List<Post>>
}