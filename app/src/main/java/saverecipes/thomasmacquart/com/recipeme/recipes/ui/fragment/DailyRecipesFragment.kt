package saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.daily_recipes_fragment.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.BaseViewModelFragment
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipeDetailsActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.DailyRecipesState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.DailyRecipesViewModel

class DailyRecipesFragment : BaseViewModelFragment<DailyRecipesViewModel>() {

    companion object {
        fun newInstance() : DailyRecipesFragment {
            return DailyRecipesFragment()
        }
    }

    private val recipesAdapter : RecipesListAdapter by lazy { RecipesListAdapter {startActivity(activity?.let { it1 -> RecipeDetailsActivity.getStartIntent(it1, it.id) })} }
    private lateinit var recipesLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.daily_recipes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesLayoutManager = LinearLayoutManager(activity)

        daily_recipes_list.apply {
            adapter = recipesAdapter
            setHasFixedSize(true)
            layoutManager = recipesLayoutManager
        }

        observe()

        viewModel.loadDailyRecipes()
    }

    private fun observe() {
        viewModel.recipesObservable.observe(this, Observer {
            when (it) {

                is DailyRecipesState.ShowRecipes -> {
                    daily_content_state.showContent()
                    recipesAdapter.submitList(it.recipes)
                    recipesAdapter.notifyDataSetChanged()
                }
                DailyRecipesState.Loading -> daily_content_state.showLoading()
                is DailyRecipesState.ShowError -> daily_content_state.showError(it.errStr) {viewModel.loadDailyRecipes()}
                DailyRecipesState.ShowEmpty -> daily_content_state.showEmpty()
            }.exhaustive
        })
    }

    override fun createViewModel(): DailyRecipesViewModel {
        return ViewModelProviders.of(this, factory)
                .get(DailyRecipesViewModel::class.java)
    }
}