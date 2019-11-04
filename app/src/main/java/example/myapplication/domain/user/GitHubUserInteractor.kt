package example.myapplication.domain.user

import example.myapplication.data.model.GitHubUser
import io.reactivex.Single

interface GitHubUserInteractor {

    /**
     * Возвращает профиль пользователя.
     *
     * @return пользователь
     */
    fun user(): Single<GitHubUser>

}