package example.myapplication.data

import android.content.Context
import example.myapplication.R
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor(context: Context) : Interceptor {

    private val token: String by lazy {
        context.getString(R.string.github_api_token)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(
                request.newBuilder()
                    .header("Authorization", "token $token")
                    .build())
    }

}