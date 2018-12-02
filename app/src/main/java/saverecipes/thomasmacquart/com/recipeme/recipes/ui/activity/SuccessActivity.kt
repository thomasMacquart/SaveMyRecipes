package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.success_activity.*
import saverecipes.thomasmacquart.com.recipeme.R

class SuccessActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) : Intent{
            return Intent(context, SuccessActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.success_activity)

        lottie_animation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                setResult(Activity.RESULT_OK)
                finish()
            }
        })
    }
}