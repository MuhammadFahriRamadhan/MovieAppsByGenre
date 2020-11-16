package com.mandiri.fahri.presentation

import android.content.Context
import android.content.SharedPreferences
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.mandiri.fahri.R
import com.mandiri.fahri.Utils.ConnectivityViewModel
import com.mandiri.fahri.Utils.Utils.snackbar
import com.mandiri.fahri.domain.GenreEntity
import com.mandiri.fahri.domain.GenreEntity.GenresResult
import com.mandiri.fahri.domain.HomeEntity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class HomeMovieActivity :  DaggerAppCompatActivity() , HomeMovieView{

    @Inject
    lateinit var presenter: HomeMoviePresenter
    var connectmodel : ConnectivityViewModel?=null
    private var listgenre = ArrayList<GenreEntity.GenresResult>()
    private var adapter: HomeMovieAdapter? = null
    private var isLoading: Boolean = false
    private var currentPage: Long = -1L
    private var  id1 : String? = null
    lateinit var mSave : SharedPreferences
    var mEditor : SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcv_listmovie.visibility = View.VISIBLE
        mSave = getSharedPreferences("save_genre", Context.MODE_PRIVATE)
        mEditor = mSave.edit()
        connectmodel = ConnectivityViewModel(application)
        checkconnection()
    }

    private fun checkconnection() {
        connectmodel!!.getType().observe(this, Observer {
            if (it != null) {
                if (it.connect == NetworkInfo.State.CONNECTED){
                    presenter.getGenresMovie()
                    snackbar(this,parent_layout,"Connected",R.drawable.ic_info_outline_white_24dp,true)
                }else{
                    rcv_listmovie.adapter = null
                    snackbar(this,parent_layout,"Disconected",R.drawable.ic_info_outline_white_24dp,false)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onShowLoading() {
        pb_homes.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        pb_homes.visibility = View.GONE
        rcv_listmovie.visibility = View.VISIBLE
    }

    override fun onSucessGenre(entity: GenreEntity) {

        for (i in entity.genres){
            listgenre.add(i)

        }

        val spnradapter = ArrayAdapter<GenreEntity.GenresResult>(applicationContext, android.R.layout.simple_spinner_dropdown_item, listgenre)
        spnr_listmovie.adapter = spnradapter

        spnr_listmovie.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                val id1 = parent.selectedItem as GenresResult
                mEditor!!.putString("id_genre",id1.id.toString())
                mEditor!!.apply()
                presenter.discoverMovie(id1.id.toString(),1)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }
    override fun onSuccess(entity: HomeEntity) {
        adapter = HomeMovieAdapter(entity.results.toMutableList())
        rcv_listmovie.addItemDecoration(DividerItemDecoration(this@HomeMovieActivity,DividerItemDecoration.VERTICAL))
        rcv_listmovie.adapter = adapter
        currentPage = entity.page
        val mLayoutManager =  LinearLayoutManager(this);
        rcv_listmovie.layoutManager = mLayoutManager
        adapter!!.setMovieOnclickListener {
            ctx.startActivity<DetailMovie>("DetailMovie" to it)
        }

        rcv_listmovie.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (currentPage >= entity.totalPages || isLoading) return

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val idgenres = mSave.getString("id_genre",null)
                Log.i("TAGED","$idgenres")
                if (visibleItemCount.plus(firstVisibleItemPosition) >= totalItemCount) {
                    adapter?.showLoading()
                    isLoading = true
                    currentPage++
                    presenter.loadMore(idgenres.toString(),currentPage)
                }
            }
        })

    }

    override fun onError(error: Throwable) {
       Log.i("TAGED",""+error.printStackTrace())
    }

    override fun onPaginationSuccess(entity: HomeEntity) {
        currentPage = entity.page
        adapter?.hideLoading()
        isLoading = false
        adapter?.loadMore(entity.results.toMutableList())
    }

    override fun onPaginationError(error: Throwable) {
        currentPage--
        adapter?.hideLoading()
        isLoading = false
    }
}