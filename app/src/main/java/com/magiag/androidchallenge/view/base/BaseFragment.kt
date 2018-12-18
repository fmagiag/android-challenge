package com.magiag.androidchallenge.view.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.viewmodel.base.BaseViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private var mViewDataBinding: B? = null
    private var mViewModel: V? = null

    protected abstract fun getFragmentLayout(): Int

    protected abstract fun getViewModelClass(): Class<V>?

    protected abstract fun initBinding()

    val navController: NavController
        get() = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (activity == null) return null

        mViewDataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)

        mViewModel = ViewModelProviders.of(this).get(getViewModelClass()!!)
        initBinding()
        return mViewDataBinding!!.root
    }

    protected fun binding(): B {
        return mViewDataBinding!!
    }

    protected fun viewModel(): V {
        return mViewModel!!
    }
}