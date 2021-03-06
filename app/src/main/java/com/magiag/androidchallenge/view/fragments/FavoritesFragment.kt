package com.magiag.androidchallenge.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.FragFavoritesBinding
import com.magiag.androidchallenge.deletingErrorDialog
import com.magiag.androidchallenge.savingErrorDialog
import com.magiag.androidchallenge.view.adapters.FavoritesAdapter
import com.magiag.androidchallenge.view.base.BaseFragment
import com.magiag.androidchallenge.view.fragments.FragmentInterface.Companion.ARGS_NAVIGATION
import com.magiag.androidchallenge.view.fragments.FragmentInterface.Companion.ARGS_SAVE
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
        viewmodel.onDeletingShowsError().observe(this, Observer<ShowEntity> { this.onDeletingShowsError(it) })
    }

    private fun onFavoritesResult(list: List<ShowEntity>) {
        val adapter = FavoritesAdapter(list, context!!)
        adapter.onClickAction().observe(this, Observer<ShowEntity> { this.onDeleteShow(it) })
        adapter.onClickItem().observe(this, Observer<ShowEntity> { this.onNavDetail(it) })
        bind.rvList.layoutManager = LinearLayoutManager(context)
        bind.rvList.setHasFixedSize(true)
        bind.rvList.adapter = adapter
    }

    private fun onDeleteShow(show: ShowEntity) {
        viewmodel.deleteShow(show)
    }

    private fun onNavDetail(show: ShowEntity) {
        val bundle = Bundle()
        bundle.putParcelable(ARGS_NAVIGATION, show)
        bundle.putBoolean(ARGS_SAVE, false)
        navController.navigate(R.id.detailActivity, bundle)
    }

    private fun onDeletingShowsError(show: ShowEntity){
        val dialog = deleteErrorDialog(show)
        dialog.show()
    }

    private fun deleteErrorDialog(show: ShowEntity): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.deleteShow(show)
            dialog.dismiss()
        }
        return deletingErrorDialog(context!!, onClickListener).create()
    }
}
