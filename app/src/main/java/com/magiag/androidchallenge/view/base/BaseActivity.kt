package com.magiag.androidchallenge.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.viewmodel.base.BaseViewModel

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel>  : AppCompatActivity() {

    private var mViewDataBinding: B? = null
    private var mViewModel: V? = null

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<V>?

    val navController: NavController
        get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getContentLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId())
            mViewModel = ViewModelProviders.of(this).get(getViewModelClass()!!)
        }
    }

    protected fun binding(): B {
        return mViewDataBinding!!
    }

    protected fun viewModel(): V {
        return mViewModel!!
    }
}
