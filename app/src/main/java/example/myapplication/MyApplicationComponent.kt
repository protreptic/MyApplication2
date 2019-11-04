package example.myapplication

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import example.myapplication.auxiliary.rx.Schedulers
import example.myapplication.modules.GitHubModule
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(modules = [ AndroidInjectionModule::class, GitHubModule::class ])
interface MyApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): MyApplicationComponent

    }

}
