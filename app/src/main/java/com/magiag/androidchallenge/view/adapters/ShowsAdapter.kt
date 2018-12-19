package com.magiag.androidchallenge.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import androidx.recyclerview.widget.RecyclerView
import com.magiag.androidchallenge.GlideApp
import java.lang.StringBuilder

class ShowsAdapter(private val mShows: MutableList<ShowEntity>, private val mContext: Context) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {

    private val mOnClickAction = MutableLiveData<ShowEntity>()

    fun onClickAction(): MutableLiveData<ShowEntity> {
        return mOnClickAction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowsAdapter.ViewHolder, position: Int) {
        val showEntity = mShows[position]
        holder.tvName.text = showEntity.name
        GlideApp.with(mContext)
                .load(showEntity.image!!.medium!!)
                .skipMemoryCache(true)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.ivCover)
        holder.ivAction.setOnClickListener {

            val message = StringBuilder(showEntity.name)
            message.append(" was saved in favorites!")

            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
            removeAt(position)
            mOnClickAction.postValue(showEntity)
        }
    }

    override fun getItemCount(): Int {
        return mShows.size
    }

    fun removeAt(position: Int) {
        mShows.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mShows.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCover: ImageView = itemView.findViewById(R.id.ivCover)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var ivAction: ImageView = itemView.findViewById(R.id.ivAction)
    }

}
