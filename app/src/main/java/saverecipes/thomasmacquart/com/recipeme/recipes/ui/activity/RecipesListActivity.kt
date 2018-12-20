package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes_list_activity.*
import kotlinx.android.synthetic.main.recipes_list_fragment.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.BaseViewModelActivity
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.RecipesListFragment
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel


class RecipesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.recipes_list_activity)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, RecipesListFragment.newInstance()).commit()
    }
}
