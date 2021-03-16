package com.aspiration.interview.data.repositories

import android.util.Log
import com.aspiration.interview.data.db.PostDao
import com.aspiration.interview.data.models.Post
import com.aspiration.interview.data.network.service.PostsService
import com.aspiration.interview.domain.GetPostsUseCaseImpl
import com.aspiration.interview.domain.repository.PostsRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class PostsRepositoryImpl(private val postsService: PostsService, private val postsDao: PostDao): PostsRepository {

    /**
     * Get persisted posts from DB
     * Fetch posts from Network and persist, return posts.
     * Post Model is being used for Network and DB. The reason is the simple model used for it.
     * Normally there should be a mapping between different models for DB - Network.
     */
    override fun getPosts(): Observable<List<Post>> {
        return Observable.create { emitter ->
            val posts = postsDao.getAll()
            if(posts.isNotEmpty()) {
                emitter.onNext(posts)
                Log.d(GetPostsUseCaseImpl.TAG, "Got posts from DB")
            }

            postsService.getPosts()
                .subscribe({
                    postsDao.insertAll(it)
                    Log.d(GetPostsUseCaseImpl.TAG, "Got posts from Network")
                    emitter.onNext(postsDao.getAll())
                    emitter.onComplete()
                }, {
                    emitter.onError(it)
                } )
        }
    }

}