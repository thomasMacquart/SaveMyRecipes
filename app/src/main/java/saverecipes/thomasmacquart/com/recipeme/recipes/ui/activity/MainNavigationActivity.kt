package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_navigation_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.DailyRecipesFragment
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.RecipesListFragment


class MainNavigationActivity : AppCompatActivity() {

    companion object {
        private const val FRAGMENT_TAG_YOUR_RECIPES = "FRAGMENT_TAG_YOUR_RECIPES"
        private const val FRAGMENT_TAG_DAILY_RECIPES = "FRAGMENT_TAG_DAILY_RECIPES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_navigation_activity)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, RecipesListFragment.newInstance()).commit()

        navigation.setOnNavigationItemSelectedListener {
            var fragment : Fragment?
            val tag : String?
            when(it.itemId) {
                R.id.tab_your_recipes -> {
                    tag = FRAGMENT_TAG_YOUR_RECIPES
                    fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_YOUR_RECIPES)
                    if (fragment == null) {
                        fragment = RecipesListFragment.newInstance()
                    }
                }

                R.id.tab_daily_recipes -> {
                    tag = FRAGMENT_TAG_DAILY_RECIPES
                    fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_DAILY_RECIPES)
                    if (fragment == null) {
                        fragment = DailyRecipesFragment.newInstance()
                    }
                }
                else -> {
                    fragment = null
                    tag = null
                }
            }

            if (fragment != null || tag == null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment!!, tag).commit()
            }

            true
        }
    }
}
