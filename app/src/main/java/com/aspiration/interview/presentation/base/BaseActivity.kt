package com.aspiration.interview.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.aspiration.interview.di.qualifiers.SchedulerUI
import com.aspiration.interview.utils.disposeBy
import com.aspiration.interview.utils.throttleFirst
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<V>, V : BaseView> :
    AppCompatActivity(), BaseView {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject @field:SchedulerUI lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInputs()
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(returnThisHerePlease())
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }

    protected fun Disposable.disposeByBagProvider() = disposeBy(compositeDisposable)

    protected fun View.bindClick(block: () -> Unit) = RxView.clicks(this)
        .throttleFirst()
        .subscribe { block.invoke() }
        .disposeByBagProvider()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    abstract fun setupInputs()

    abstract fun returnThisHerePlease(): V
}