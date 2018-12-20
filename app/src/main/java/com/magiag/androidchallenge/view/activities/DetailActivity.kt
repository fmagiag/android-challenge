package com.magiag.androidchallenge.view.activities

import android.os.Bundle
import android.view.MenuItem
import com.magiag.androidchallenge.GlideApp
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.utils.Utils
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.ActDetailBinding
import com.magiag.androidchallenge.view.activities.ActivityInterface.Companion.ARGS_NAVIGATION
import com.magiag.androidchallenge.view.base.BaseActivity
import java.lang.StringBuilder


class DetailActivity : BaseActivity<ActDetailBinding>() {
    lateinit var bind: ActDetailBinding
    lateinit var showEntity: ShowEntity


    override fun getContentLayoutId(): Int {
        return R.layout.act_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = binding()

        setSupportActionBar(bind.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        showEntity = intent.extras!!.getParcelable<ShowEntity>(ARGS_NAVIGATION)!!

        GlideApp.with(this)
                .load(showEntity.image!!.original!!)
                .skipMemoryCache(true)
                .centerCrop()
                .into(bind.ivCover)

        val screenTitle = StringBuilder("TV Show's: ")
        screenTitle.append(showEntity.name!!)

        bind.tvTitle.text = screenTitle
        bind.tvSummary.text = Utils.from(showEntity.summary!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId != android.R.id.home) {
            super.onOptionsItemSelected(item)
        } else {
            onBackPressed()
            true
        }
    }
}
