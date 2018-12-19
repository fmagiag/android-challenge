package com.magiag.androidchallenge.view.fragments

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.FragFavoritesBinding
import com.magiag.androidchallenge.view.adapters.FavoritesAdapter
import com.magiag.androidchallenge.view.base.BaseFragment
import com.magiag.androidchallenge.viewmodel.FavoritesViewModel

class FavoritesFragment : BaseFragment<FragFavoritesBinding, FavoritesViewModel>() {

    lateinit var bind: FragFavoritesBinding
    lateinit var viewmodel: FavoritesViewModel

    override fun getFragmentLayout(): Int {
        return R.layout.frag_favorites
    }

    override fun getViewModelClass(): Class<FavoritesViewModel>? {
        return FavoritesViewModel::class.java
    }

    override fun initBinding() {
        bind = binding()
        viewmodel = viewModel()
        viewmodel.getAllShows().observe(this, Observer<List<ShowEntity>> { this.onFavoritesResult(it) })
    }

    private fun onFavoritesResult(list: List<ShowEntity>){
        val adapter = FavoritesAdapter(list, context!!)
        adapter.onClickAction().observe(this, Observer<ShowEntity> { this.onDeleteShow(it)  })
        bind.rvList.layoutManager = LinearLayoutManager(context)
        bind.rvList.setHasFixedSize(true)
        bind.rvList.adapter = adapter }

    private fun onDeleteShow(show: ShowEntity){
        viewmodel.deleteShow(show)
    }
}
