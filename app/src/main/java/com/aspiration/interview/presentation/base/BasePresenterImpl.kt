package com.aspiration.interview.presentation.base

import com.aspiration.interview.di.qualifiers.SchedulerUI
import com.aspiration.interview.utils.disposeBy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.intellij.lang.annotations.Flow
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    @Inject lateinit var compositeDisposable: CompositeDisposable
    @Inject @field:SchedulerUI lateinit var uiScheduler: Scheduler

    private var viewWeakReference: WeakReference<V>? = null
    protected val view: V?
        get() = viewWeakReference?.get()

    protected fun resetState() {}

    override fun bindView(view: V) {
        this.viewWeakReference = WeakReference(view)
    }

    override fun unbindView() {
        this.viewWeakReference = null
    }

    fun Disposable.disposeByBagProvider() = disposeBy(compositeDisposable)

    protected fun <T> Observable<T>.bindLoading(): Observable<T> =
        this.observeOn(uiScheduler)
            .doOnSubscribe { view?.showLoading() }
            .doOnNext { view?.hideLoading() }
            .doOnTerminate { view?.hideLoading() }
            .doOnError { view?.hideLoading() }

    protected fun <T> Single<T>.bindLoading(): Single<T> =
        this.observeOn(uiScheduler)
            .doOnSubscribe { view?.showLoading() }
            .doOnSuccess { view?.hideLoading() }
            .doOnError { view?.hideLoading() }

    protected fun <T> Flowable<T>.bindLoading(): Flowable<T> =
        this.observeOn(uiScheduler)
            .doOnSubscribe { view?.showLoading() }
            .doOnNext { view?.hideLoading() }
            .doOnError { view?.hideLoading() }
}