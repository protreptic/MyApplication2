package example.myapplication.presentation

import androidx.fragment.app.FragmentManager
import example.myapplication.data.model.GitHubRepository
import example.myapplication.presentation.abs.AbsRouter
import example.myapplication.presentation.repository.GitHubRepositoryFragment
import example.myapplication.presentation.repository.search.GitHubRepositorySearchFragment

class MainRouter(fragmentManager: FragmentManager?): AbsRouter(fragmentManager) {

    fun toGitHubRepositorySearch(query: String) {
        push(GitHubRepositorySearchFragment.newInstance(query),
             GitHubRepositorySearchFragment.FRAGMENT_TAG)
    }

    fun toGitHubRepository(gitHubRepository: GitHubRepository) {
        push(GitHubRepositoryFragment.newInstance(gitHubRepository.id),
             GitHubRepositoryFragment.FRAGMENT_TAG)
    }

}