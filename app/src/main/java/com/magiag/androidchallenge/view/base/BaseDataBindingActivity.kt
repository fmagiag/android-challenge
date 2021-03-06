package com.magiag.androidchallenge.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.magiag.androidchallenge.R

abstract class BaseDataBindingActivity<B : ViewDataBinding> : AppCompatActivity() {

    private var mViewDataBinding: B? = null

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    val navController: NavController
        get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getContentLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId())
        }
    }

    protected fun binding(): B {
        return mViewDataBinding!!
    }
}
