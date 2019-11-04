package example.myapplication.data.repository.user.source

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.model.GitHubUser
import io.reactivex.Single

interface GitHubUserDataSource {

    /**
     * Возвращает профиль пользователя.
     *
     * @return GitHub-пользователь
     */
    fun user(): Single<GitHubUser>

    /**
     * Возвращает список репозиториев пользователя.
     *
     * @return репозитории пользователя
     */
    fun repositories(): Single<List<GitHubRepository>>

    /**
     * Сохраняем пользователя.
     *
     * @param gitHubUser github-пользователь
     * @return github-пользователь
     */
    fun retain(gitHubUser: GitHubUser): Single<GitHubUser>

}