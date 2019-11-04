package example.myapplication

import dagger.android.DaggerApplication
import example.myapplication.auxiliary.rx.Schedulers

class MyApplication: DaggerApplication() {

    override fun applicationInjector() =
        DaggerMyApplicationComponent.builder()
            .withContext(this)
            .withSchedulers(Schedulers.schedulers())
            .build()

}