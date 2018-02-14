package saverecipes.thomasmacquart.com.recipeme.core

/**
 * Created by thomas.macquart on 07/01/2018.
 */
abstract class BasePresenter<T : MvpView> {

    //protected val disposables = CompositeDisposable()
    protected var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    fun destroy() {
        //disposables.clear()
        unbind()
    }

}