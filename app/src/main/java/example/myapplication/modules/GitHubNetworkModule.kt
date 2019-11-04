package example.myapplication.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import example.myapplication.BuildConfig
import example.myapplication.R
import example.myapplication.data.GitHub
import example.myapplication.data.GitHubInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GitHubNetworkModule {

    @Singleton
    @Provides
    fun providesGitHub(context: Context): GitHub =
            Retrofit.Builder()
                .baseUrl(context.getString(R.string.github_api))
                .client(OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) BODY else NONE
                        })
                    .addInterceptor(GitHubInterceptor(context))
                    .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHub::class.java)

}
