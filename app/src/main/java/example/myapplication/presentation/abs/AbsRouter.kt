package example.myapplication.presentation.abs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import example.myapplication.auxiliary.extensions.defaultAnimation

abstract class AbsRouter(private val fragmentManager: FragmentManager?) {

    fun push(fragment: Fragment, tag: String) {
        fragmentManager
            ?.beginTransaction()
            ?.defaultAnimation()
            ?.add(android.R.id.content, fragment, tag)
            ?.addToBackStack(tag)
            ?.commit()
    }

    fun back() {
        fragmentManager?.popBackStack()
    }

}