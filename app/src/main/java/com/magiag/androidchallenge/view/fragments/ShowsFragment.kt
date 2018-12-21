package com.magiag.androidchallenge.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magiag.androidchallenge.*
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.FragShowsBinding
import com.magiag.androidchallenge.view.adapters.ShowsAdapter
import com.magiag.androidchallenge.view.base.BaseFragment
import com.magiag.androidchallenge.view.fragments.FragmentInterface.Companion.ARGS_NAVIGATION
import com.magiag.androidchallenge.view.fragments.FragmentInterface.Companion.ARGS_SAVE
import com.magiag.androidchallenge.viewmodel.ShowsViewModel
import com.magiag.androidchallenge.viewmodel.ShowsViewModel.ShowsError

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
        viewmodel.onShowsError().observe(this, Observer<ShowsError> { this.onShowsError(it) })
        viewmodel.onSavingShowsError().observe(this, Observer<ShowEntity> { this.onSavingShowsError(it) })
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
        bundle.putBoolean(ARGS_SAVE, true)
        navController.navigate(R.id.detailActivity, bundle)
    }

    private fun onShowsError(error: ShowsError?){
        if(error == ShowsError.UNEXPECTED_ERROR){
            val dialog = showsErrorDialog()
            dialog.show()
        }
    }

    private fun onSavingShowsError(show: ShowEntity){
            val dialog = saveErrorDialog(show)
            dialog.show()
    }

    private fun showsErrorDialog(): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.getShows(1)
            dialog.dismiss()
        }
        return getShowsErrorDialog(context!!, onClickListener).create()
    }

    private fun saveErrorDialog(show: ShowEntity): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.insertShow(show)
            dialog.dismiss()
        }
        return savingErrorDialog(context!!, onClickListener).create()
    }
}
