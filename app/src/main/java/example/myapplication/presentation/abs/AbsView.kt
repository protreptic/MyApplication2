package example.myapplication.presentation.abs

import moxy.MvpView

interface AbsView: MvpView {

    fun showLoading() {
        /* пустая реализация */
    }

    fun showContent() {
        /* пустая реализация */
    }

    fun showError(error: Any?) {
        /* пустая реализация */
    }

}