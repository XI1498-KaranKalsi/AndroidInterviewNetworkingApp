package com.aspiration.interview.presentation

import com.aspiration.interview.data.models.ListItem
import com.aspiration.interview.data.models.Post
import com.aspiration.interview.presentation.base.BasePresenter
import com.aspiration.interview.presentation.base.BaseView

interface MainAgreement {
    interface View : BaseView {
        fun showPosts(posts: List<ListItem>)
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadPosts()
    }
}