package example.myapplication.presentation.abs

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class AbsPresenter<T: MvpView>: MvpPresenter<T>() {

    protected val attachedView: T by lazy { attachedViews.first() }
    protected val disposables = CompositeDisposable()

    override fun onDestroy() {
        disposables.clear()
    }

}