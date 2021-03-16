package com.aspiration.interview.domain

import com.aspiration.interview.data.models.ListItem
import io.reactivex.Observable

interface GetPostsUseCase {

    fun execute(): Observable<ArrayList<ListItem>>

}