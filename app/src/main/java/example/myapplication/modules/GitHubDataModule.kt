package example.myapplication.modules

import dagger.Module
import dagger.Provides
import example.myapplication.data.GitHub
import example.myapplication.data.repository.repository.source.impl.GitHubRepositoryCacheDataSource
import example.myapplication.data.repository.repository.source.impl.GitHubRepositoryCloudDataSource
import example.myapplication.data.repository.repository.source.GitHubRepositoryDataSource
import example.myapplication.data.repository.user.source.impl.GitHubUserCacheDataSource
import example.myapplication.data.repository.user.source.impl.GitHubUserCloudDataSource
import example.myapplication.data.repository.user.source.GitHubUserDataSource
import javax.inject.Named

@Module(includes = [ GitHubNetworkModule::class ])
class GitHubDataModule {

    @Named("cloud")
    @Provides
    fun provideGitHubUserCloudDataSource(gitHub: GitHub): GitHubUserDataSource =
        GitHubUserCloudDataSource(gitHub)

    @Named("cache")
    @Provides
    fun provideGitHubUserCacheDataSource(): GitHubUserDataSource =
        GitHubUserCacheDataSource()

    @Named("cloud")
    @Provides
    fun provideGitHubRepositoryCloudDataSource(gitHub: GitHub): GitHubRepositoryDataSource =
        GitHubRepositoryCloudDataSource(gitHub)

    @Named("cache")
    @Provides
    fun provideGitHubRepositoryCacheDataSource(): GitHubRepositoryDataSource =
        GitHubRepositoryCacheDataSource()

}
