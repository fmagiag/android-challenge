package com.magiag.androidchallenge.view.activities

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import com.magiag.androidchallenge.GlideApp
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.ActDetailBinding
import com.magiag.androidchallenge.view.activities.ActivityInterface.Companion.ARGS_NAVIGATION
import java.lang.StringBuilder
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.View.GONE
import android.widget.Toast
import com.magiag.androidchallenge.convertHTML
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
                val builder = getAlertDialog()
                val dialog = builder.create()

                dialog.show()
            }
            R.id.save_menu -> {
                val message = StringBuilder(mShowEntity.name).append(" ")
                        .append(this.getString(R.string.dialog_shows_toast_message))
                viewmodel.insertShow(mShowEntity)

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAlertDialog(): AlertDialog.Builder {
        return AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setTitle(this.getString(R.string.dialog_favorites_title))
                .setMessage(this.getString(R.string.dialog_favorites_message))
                .setPositiveButton(this.getString(
                        R.string.dialog_favorites_positive_button)) { dialog, _ ->
                    val message = StringBuilder(mShowEntity.name).append(" ")
                            .append(this.getString(R.string.dialog_shows_toast_message))

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    viewmodel.deleteShow(mShowEntity)
                    finish()
                    dialog.dismiss()
                }.setNegativeButton(this.getString(
                        R.string.dialog_favorites_negative_button)) { dialog, _ ->
                    dialog.dismiss()
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = binding()
        viewmodel = viewModel()

        setSupportActionBar(bind.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

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
}
