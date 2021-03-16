package com.aspiration.interview.presentation

import com.aspiration.interview.domain.GetPostsUseCase
import com.aspiration.interview.presentation.base.BasePresenterImpl
import com.aspiration.interview.utils.disposeBy
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getPostsUseCase: GetPostsUseCase) :
    BasePresenterImpl<MainAgreement.View>(), MainAgreement.Presenter {

    override fun loadPosts() {
        getPostsUseCase.execute()
            .bindLoading()
            .subscribe({
                view?.showPosts(it)
            }, {
                view?.showError()
            })
            .disposeBy(compositeDisposable)
    }
}