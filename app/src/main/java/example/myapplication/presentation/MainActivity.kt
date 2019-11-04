package example.myapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import example.myapplication.presentation.user.GitHubUserFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content,
                    GitHubUserFragment.newInstance(),
                        GitHubUserFragment.FRAGMENT_TAG)
                .commit()
        }
    }

}