package com.aspiration.interview.domain

import android.util.Log
import com.aspiration.interview.data.models.ListItem
import com.aspiration.interview.domain.repository.PostsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GetPostsUseCaseImpl(
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler,
    private val repository: PostsRepository
) : GetPostsUseCase {

    companion object {
        val TAG = this::class.java.simpleName
    }

    override fun execute(): Observable<ArrayList<ListItem>> {
        return repository.getPosts()
            .map {
                val items = arrayListOf<ListItem>()
                it.forEach { post ->
                    items.add(ListItem(ListItem.DATE, date = getDateWithId(post.id)))
                    items.add(ListItem(ListItem.POST, post = post))
                }
                items
            }
            .doOnNext { Log.d(TAG, "Success getting posts") }
            .doOnError { Log.e(TAG, "Error getting posts", it) }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
    }

    private fun getDateWithId(id: Long): String {
        val d1 = Date()
        val d2 = Date(d1.time + id * 60 * 60 * 1000)
        val pattern = "EEE MMM dd HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(d2)
    }
}