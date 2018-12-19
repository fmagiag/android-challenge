package com.magiag.androidchallenge.view.activities

import android.os.Bundle
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.databinding.ActDetailBinding
import com.magiag.androidchallenge.view.base.BaseActivity

class DetailActivity : BaseActivity<ActDetailBinding>() {
    lateinit var bind: ActDetailBinding

    override fun getContentLayoutId(): Int {
        return R.layout.act_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = binding()
    }
}
