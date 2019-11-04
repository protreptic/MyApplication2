package example.myapplication.modules

import dagger.Module
import dagger.Provides
import example.myapplication.data.repository.repository.GitHubRepositoryRepositoryImpl
import example.myapplication.data.repository.repository.source.GitHubRepositoryDataSource
import example.myapplication.data.repository.user.GitHubUserRepositoryImpl
import example.myapplication.data.repository.user.source.GitHubUserDataSource
import example.myapplication.domain.repository.GitHubRepositoryInteractor
import example.myapplication.domain.repository.GitHubRepositoryInteractorImpl
import example.myapplication.domain.repository.GitHubRepositoryRepository
import example.myapplication.domain.user.GitHubUserInteractor
import example.myapplication.domain.user.GitHubUserInteractorImpl
import example.myapplication.domain.user.GitHubUserRepository
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ GitHubDataModule::class ])
class GitHubDomainModule {

    @Singleton
    @Provides
    fun provideGitHubUserRepository(
            @Named("cloud") cloud: GitHubUserDataSource,
            @Named("cache") cache: GitHubUserDataSource): GitHubUserRepository =
                GitHubUserRepositoryImpl(cloud, cache)

    @Singleton
    @Provides
    fun provideGitHubUserInteractor(
            gitHubUserRepository: GitHubUserRepository): GitHubUserInteractor =
                GitHubUserInteractorImpl(gitHubUserRepository)

    @Singleton
    @Provides
    fun provideGitHubRepositoryRepository(
        @Named("cloud") cloud: GitHubRepositoryDataSource,
        @Named("cache") cache: GitHubRepositoryDataSource): GitHubRepositoryRepository =
            GitHubRepositoryRepositoryImpl(cloud, cache)

    @Singleton
    @Provides
    fun provideGitHubRepositoryInteractor(
            gitHubRepositoryRepository: GitHubRepositoryRepository): GitHubRepositoryInteractor =
                GitHubRepositoryInteractorImpl(gitHubRepositoryRepository)

}
