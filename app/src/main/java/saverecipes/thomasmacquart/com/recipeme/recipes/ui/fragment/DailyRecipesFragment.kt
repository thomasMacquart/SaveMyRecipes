package saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saverecipes.thomasmacquart.com.recipeme.R

class DailyRecipesFragment : Fragment() {

    companion object {
        fun newInstance() : DailyRecipesFragment {
            return DailyRecipesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.daily_recipes_fragment, container, false)
    }
}