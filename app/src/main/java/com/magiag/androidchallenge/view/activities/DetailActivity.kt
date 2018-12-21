package com.magiag.androidchallenge.view.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.ActDetailBinding
import com.magiag.androidchallenge.view.activities.ActivityInterface.Companion.ARGS_NAVIGATION
import java.lang.StringBuilder
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.magiag.androidchallenge.*
import com.magiag.androidchallenge.view.activities.ActivityInterface.Companion.ARGS_SAVE
import com.magiag.androidchallenge.view.base.BaseActivity
import com.magiag.androidchallenge.viewmodel.DetailViewModel


class DetailActivity : BaseActivity<ActDetailBinding, DetailViewModel>() {

    lateinit var bind: ActDetailBinding
    lateinit var viewmodel: DetailViewModel
    lateinit var mShowEntity: ShowEntity
    var isSave: Boolean = false

    override fun getContentLayoutId(): Int {
        return R.layout.act_detail
    }

    override fun getViewModelClass(): Class<DetailViewModel>? {
        return DetailViewModel::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(getMenu(), menu)
        return true
    }

    private fun getMenu(): Int {
        return if (isSave) R.menu.save_menu else R.menu.remove_menu
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
            R.id.remove_menu -> {
                val dialog = removeDialog()
                dialog.show()
            }
            R.id.save_menu -> {
                viewmodel.insertShow(mShowEntity)
                showSaveToast(this, mShowEntity.name!!)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeDialog(): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.deleteShow(mShowEntity)
            showDeleteToast(this, mShowEntity.name!!)
            dialog.dismiss()
            finish()
        }
        return deleteDialog(this, onClickListener).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = binding()
        viewmodel = viewModel()

        setSupportActionBar(bind.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        viewmodel.onSavingShowsError().observe(this, Observer<ShowEntity> { this.onSavingShowsError(it) })
        viewmodel.onDeletingShowsError().observe(this, Observer<ShowEntity> { this.onDeletingShowsError(it) })

        mShowEntity = intent.extras!!.getParcelable<ShowEntity>(ARGS_NAVIGATION)!!
        isSave = intent.extras!!.getBoolean(ARGS_SAVE)

        GlideApp.with(this)
                .load(mShowEntity.image!!.original!!)
                .skipMemoryCache(true)
                .centerCrop()
                .into(bind.ivCover)

        val screenTitle = StringBuilder("TV Show's: ").append(mShowEntity.name!!)

        bind.tvTitle.text = screenTitle
        bind.tvSummary.text = convertHTML(mShowEntity.summary!!)
        if (mShowEntity.externals != null) {
            bind.btImdp.setOnClickListener {
                val uri = StringBuilder("https://www.imdb.com/title/")
                uri.append(mShowEntity.externals!!.imdb)
                val viewIntent = Intent("android.intent.action.VIEW",
                        Uri.parse(uri.toString()))
                startActivity(viewIntent)
            }
        } else {
            bind.btImdp.visibility = GONE
        }
    }

    private fun onSavingShowsError(show: ShowEntity){
        val dialog = saveErrorDialog(show)
        dialog.show()
    }

    private fun onDeletingShowsError(show: ShowEntity){
        val dialog = deleteErrorDialog(show)
        dialog.show()
    }

    private fun saveErrorDialog(show: ShowEntity): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.insertShow(show)
            dialog.dismiss()
        }
        return savingErrorDialog(this, onClickListener).create()
    }

    private fun deleteErrorDialog(show: ShowEntity): AlertDialog {
        val onClickListener = DialogInterface.OnClickListener { dialog, _ ->
            viewmodel.deleteShow(show)
            dialog.dismiss()
        }
        return deletingErrorDialog(this, onClickListener).create()
    }
}
