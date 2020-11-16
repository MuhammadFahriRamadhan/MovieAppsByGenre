package com.mandiri.fahri.presentation

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.fahri.R
import com.mandiri.fahri.Utils.ImageLoad
import com.mandiri.fahri.domain.HomeEntity
import kotlinx.android.synthetic.main.item_movies.view.*

enum class Type{
    DATA,
    LOADING
}
class HomeMovieAdapter(private var result: MutableList<HomeEntity.Results?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var  movielistener :(HomeEntity.Results) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
           Type.DATA.ordinal -> {
               HomeViewHolder(
                   LayoutInflater
                       .from(parent.context)
                       .inflate(
                           R.layout.item_movies,
                           parent,
                           false
                       )
               )
           }
            Type.LOADING.ordinal -> {
                LoadingViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(
                            R.layout.item_loading,
                            parent,
                            false
                        )
                )
            }
            else ->  throw RuntimeException("Illegal view type")
        }
    }

    override fun getItemCount(): Int {
        return result.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            result[position] == null -> Type.LOADING.ordinal
            else -> Type.DATA.ordinal
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(result[holder.adapterPosition])
            }
        }
    }

    fun showLoading() {
        result.add(null)
        Handler().post { notifyItemInserted(result.count().minus(1)) }
    }

    fun hideLoading() {
        result.removeAt(result.count().minus(1))
        Handler().post { notifyItemRemoved(result.count()) }
    }

    fun loadMore(results: MutableList<HomeEntity.Results?>) {

        this.result.addAll(results)
        Handler().post { notifyDataSetChanged() }
    }
    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: HomeEntity.Results?) {
            with(itemView) {
                Log.i("TAGED",""+ result!!.title)
                tv_title.text = result?.title ?: "Untitled"
                tv_overview.text = result?.overview ?: "No Description"
                tv_releasedate.text = result?.release_date ?: "No release"
                result.poster_path.let { imageViews.ImageLoad(it) }

                crdviewmovies.setOnClickListener {
                    movielistener(result)
                }
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setMovieOnclickListener(movielistener : (HomeEntity.Results) -> Unit){
        this.movielistener = movielistener

    }
}