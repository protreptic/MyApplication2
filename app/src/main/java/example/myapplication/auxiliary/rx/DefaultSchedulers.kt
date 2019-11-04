package example.myapplication.auxiliary.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class DefaultSchedulers : Schedulers {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.computation()
    override fun newThread(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

}