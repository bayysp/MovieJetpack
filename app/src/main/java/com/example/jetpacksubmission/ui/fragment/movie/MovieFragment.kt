package com.example.jetpacksubmission.ui.fragment.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.ui.adapter.UpcomingMovieAdapter
import com.example.jetpacksubmission.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), MovieView {

    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MovieFragment", "Enter onActivityCreated")
        if (activity != null) {
            Log.d("MovieFragment", "Enter onActivityCreated - activity not null")
            upcomingMovieAdapter = UpcomingMovieAdapter()

            //set viewmodel and get the data
            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            viewModel.setUpcomingMovie(this)

            //if the phone get rotate, check view model, if not null, put the data to adapter
            if (viewModel.getUpcomingListData()?.size != null) {
                Log.d(
                    "MovieFragment",
                    "Enter onActivityCreated - viewmodel.UpcomingListData not null"
                )
                upcomingMovieAdapter.setData(viewModel.getUpcomingListData()!!)
            }

            with(fragmentmovie_rv_upcoming) {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = upcomingMovieAdapter
            }
        }
    }

    override fun onSuccess(data: ArrayList<UpcomingResultsItem?>?) {
        data?.let {
            Log.d("MovieFragment", "Enter onSuccess")
            upcomingMovieAdapter.setData(it)
        }
    }

}
