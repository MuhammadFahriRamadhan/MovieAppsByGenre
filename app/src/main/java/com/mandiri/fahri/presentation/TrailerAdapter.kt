package com.mandiri.fahri.presentation

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.fahri.R
import com.mandiri.fahri.domain.TrailerEntity
import kotlinx.android.synthetic.main.item_video.view.*

class TrailerAdapter(private var result: MutableList<TrailerEntity.TrailerResults?>) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {
    private lateinit var movielistener: (TrailerEntity.TrailerResults) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false))
    }

    override fun getItemCount(): Int {
        return result.count()
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(result[holder.adapterPosition])
    }

    inner class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(result: TrailerEntity.TrailerResults?) {
                with(itemView) {
                    item_video_title.text = result?.name ?: "Untitled"
                    itemView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+ result!!.key))
                    context.startActivity(intent)
                    }

                }
            }
    }

}
