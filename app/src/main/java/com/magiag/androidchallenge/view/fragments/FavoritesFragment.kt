package com.magiag.androidchallenge.view.fragments

import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.databinding.FragFavoritesBinding
import com.magiag.androidchallenge.viewmodel.FavoritesViewModel

class FavoritesFragment : BaseFragment<FragFavoritesBinding, FavoritesViewModel>() {

    override fun getFragmentLayout(): Int {
        return R.layout.frag_favorites
    }

    override fun getViewModelClass(): Class<FavoritesViewModel>? {
        return FavoritesViewModel::class.java
    }

    override fun initBinding() {
    }
}
