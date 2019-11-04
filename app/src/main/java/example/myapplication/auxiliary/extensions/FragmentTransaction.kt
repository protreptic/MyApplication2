package example.myapplication.auxiliary.extensions

import androidx.fragment.app.FragmentTransaction
import example.myapplication.R

fun FragmentTransaction.defaultAnimation(): FragmentTransaction {
    setCustomAnimations(
        R.anim.slide_bottom_to_top, R.anim.slide_top_to_bottom,
        R.anim.slide_bottom_to_top, R.anim.slide_top_to_bottom)

    return this
}