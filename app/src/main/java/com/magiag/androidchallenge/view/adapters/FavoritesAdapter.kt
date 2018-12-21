package com.magiag.androidchallenge.view.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData

import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.entity.ShowEntity
import androidx.recyclerview.widget.RecyclerView
import com.magiag.androidchallenge.GlideApp

class FavoritesAdapter(private val mShows: List<ShowEntity>, private val mContext: Context) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private val mOnClickAction = MutableLiveData<ShowEntity>()
    private val mOnClickItem = MutableLiveData<ShowEntity>()

    fun onClickAction(): MutableLiveData<ShowEntity> {
        return mOnClickAction
    }

    fun onClickItem(): MutableLiveData<ShowEntity> {
        return mOnClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val showEntity = mShows[position]
        holder.tvName.text = showEntity.name
        holder.ivAction.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_save))

        GlideApp.with(mContext)
                .load(showEntity.image!!.medium!!)
                .onlyRetrieveFromCache(true)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.ivCover)
        holder.mView.setOnClickListener { mOnClickItem.postValue(showEntity) }
        holder.ivAction.setOnClickListener {

            val builder = AlertDialog.Builder(mContext, R.style.AlertDialogStyle)
                    .setTitle(mContext.getString(R.string.dialog_favorites_title))
                    .setMessage(mContext.getString(R.string.dialog_favorites_message))
                    .setPositiveButton(mContext.getString(
                            R.string.dialog_favorites_positive_button)) { dialog, _ ->
                        val message = StringBuilder(showEntity.name).append(" ")
                                .append(mContext.getString(R.string.dialog_shows_toast_message))

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        mOnClickAction.postValue(showEntity)
                        dialog.dismiss()
                    }.setNegativeButton(mContext.getString(
                            R.string.dialog_favorites_negative_button)) { dialog, _ ->
                        dialog.dismiss()
                    }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return mShows.size
    }

    inner class ViewHolder internal constructor(var mView: View) : RecyclerView.ViewHolder(mView) {
        var ivCover: ImageView = mView.findViewById(R.id.ivCover)
        var tvName: TextView = mView.findViewById(R.id.tvName)
        var ivAction: ImageView = mView.findViewById(R.id.ivAction)
    }
}
