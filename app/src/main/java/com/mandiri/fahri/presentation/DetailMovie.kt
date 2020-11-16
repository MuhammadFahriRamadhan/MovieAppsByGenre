package com.mandiri.fahri.presentation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandiri.fahri.R
import com.mandiri.fahri.Utils.ImageLoad
import com.mandiri.fahri.domain.HomeEntity
import com.mandiri.fahri.domain.ReviewEntity
import com.mandiri.fahri.domain.TrailerEntity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailMovie : DaggerAppCompatActivity() , DetailMovieView {

    @Inject
    lateinit var presenter: DetailMoviePresenter
    var madapter : TrailerAdapter? = null
    var madapterreview : ReviewAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val detailmovies : HomeEntity.Results = intent.getParcelableExtra("DetailMovie")!!

        detailmovies.let {
            it.poster_path.let {
                image_detail.ImageLoad(it)
                backdropImageView.ImageLoad(it)

            }
            title_tv.text = it.title
            release_date_tv.text = it.release_date
            plotTextView.text = it.overview
            ratingTextView.text = it.vote_average
            popularityTextView.text = it.popularity
            if (it.id != 0) {
                presenter.getTrailer(it.id)
                presenter.getReview(it.id)
            }
        }
    }

    override fun onSucessTrailer(entity: TrailerEntity) {
        madapter = TrailerAdapter(entity.results.toMutableList())
        detail_body_recyclerView_trailers.adapter = madapter
        val horizontalLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        detail_body_recyclerView_trailers.layoutManager = horizontalLayoutManager

    }

    override fun onSucessReview(entity: ReviewEntity) {
        madapterreview = ReviewAdapter(entity.results.toMutableList())
        detail_body_recyclerView_reviews.adapter = madapterreview
        val lineraLayoutManager = LinearLayoutManager(this)
        detail_body_recyclerView_reviews.layoutManager = lineraLayoutManager
    }

    override fun onError(error: Throwable) {
        error.printStackTrace()
    }
}