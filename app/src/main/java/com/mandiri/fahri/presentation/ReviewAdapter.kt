package com.mandiri.fahri.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.fahri.R
import com.mandiri.fahri.domain.ReviewEntity
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private var result: MutableList<ReviewEntity.ReviewResults?>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review,parent,false))
    }

    override fun getItemCount(): Int {
        return result.count()
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(result[holder.adapterPosition])
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: ReviewEntity.ReviewResults?) {
            with(itemView) {
                item_review_title.text = result?.author ?: "null"
                item_review_content.text = result?.content ?: "null"
                }

            }
        }
    }


