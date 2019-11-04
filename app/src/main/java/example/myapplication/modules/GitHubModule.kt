package example.myapplication.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.myapplication.presentation.MainActivity
import example.myapplication.presentation.repository.GitHubRepositoryFragment
import example.myapplication.presentation.repository.search.GitHubRepositorySearchFragment
import example.myapplication.presentation.user.GitHubUserFragment

@Suppress("unused")
@Module(includes = [ GitHubDomainModule::class ])
abstract class GitHubModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeGitHubUserFragment(): GitHubUserFragment

    @ContributesAndroidInjector
    abstract fun contributeGitHubRepositorySearchFragment(): GitHubRepositorySearchFragment

    @ContributesAndroidInjector
    abstract fun contributeGitHubRepositoryFragment(): GitHubRepositoryFragment

}
