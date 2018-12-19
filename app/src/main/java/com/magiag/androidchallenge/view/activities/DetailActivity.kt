package com.magiag.androidchallenge.view.activities

import android.os.Bundle
import android.util.Log
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.databinding.ActDetailBinding
import com.magiag.androidchallenge.view.activities.ActivityInterface.Companion.ARGS_NAVIGATION
import com.magiag.androidchallenge.view.base.BaseActivity

class DetailActivity : BaseActivity<ActDetailBinding>() {
    lateinit var bind: ActDetailBinding
    lateinit var showEntity: ShowEntity


    override fun getContentLayoutId(): Int {
        return R.layout.act_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = binding()
        showEntity = intent.extras!!.getParcelable<ShowEntity>(ARGS_NAVIGATION)!!
    }
}
