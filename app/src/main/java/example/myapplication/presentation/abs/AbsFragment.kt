package example.myapplication.presentation.abs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import example.myapplication.auxiliary.rx.Schedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class AbsFragment: MvpAppCompatFragment(), HasAndroidInjector {

    protected abstract val layout: Int
    protected lateinit var disposables: CompositeDisposable

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var schedulers: Schedulers

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables = CompositeDisposable()

        view.isClickable = false
        view.isLongClickable = false

        view.isFocusable = true
        view.isFocusableInTouchMode = true

        view.isEnabled = false

        view.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposables.clear()
    }

}