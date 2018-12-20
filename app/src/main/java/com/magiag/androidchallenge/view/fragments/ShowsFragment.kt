package com.magiag.androidchallenge.view.fragments

import android.os.Bundle
import android.view.View.GONE
import androidx.constraintlayout.solver.GoalRow
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.FragShowsBinding
import com.magiag.androidchallenge.view.adapters.ShowsAdapter
import com.magiag.androidchallenge.view.base.BaseFragment
import com.magiag.androidchallenge.view.fragments.FragmentInterface.Companion.ARGS_NAVIGATION
import com.magiag.androidchallenge.viewmodel.ShowsViewModel

class ShowsFragment : BaseFragment<FragShowsBinding, ShowsViewModel>() {

    lateinit var bind: FragShowsBinding
    lateinit var viewmodel: ShowsViewModel

    override fun getFragmentLayout(): Int {
        return R.layout.frag_shows
    }

    override fun getViewModelClass(): Class<ShowsViewModel>? {
        return ShowsViewModel::class.java
    }

    override fun initBinding() {
        bind = binding()
        viewmodel = viewModel()
        viewmodel.onShowsResult().observe(this, Observer<MutableList<ShowEntity>> { this.onShowsResult(it) })
        viewmodel.getShows(1)
    }

    private fun onShowsResult(list: MutableList<ShowEntity>) {
        bind.pgLoading.visibility = GONE
        val adapter = ShowsAdapter(list, context!!)
        adapter.onClickAction().observe(this, Observer<ShowEntity> { this.onSaveShow(it) })
        adapter.onClickItem().observe(this, Observer<ShowEntity> { this.onNavDetail(it) })
        bind.rvList.layoutManager = LinearLayoutManager(context)
        bind.rvList.setHasFixedSize(true)
        bind.rvList.adapter = adapter
    }

    private fun onSaveShow(show: ShowEntity) {
        viewmodel.insertShow(show)
    }

    private fun onNavDetail(show: ShowEntity) {
        val bundle = Bundle()
        bundle.putParcelable(ARGS_NAVIGATION, show)
        navController.navigate(R.id.detailActivity, bundle)
    }
}
