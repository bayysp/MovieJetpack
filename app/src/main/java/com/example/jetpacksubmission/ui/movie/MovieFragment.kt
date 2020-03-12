package com.example.jetpacksubmission.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.ui.adapter.UpcomingAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() , MovieView{

    private lateinit var upcomingAdapter : UpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            upcomingAdapter = UpcomingAdapter()

            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            viewModel.setUpcomingMovie(this)

            with(fragmentmovie_rv_upcoming){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingAdapter
            }
        }
    }

    override fun onSuccess(data: ArrayList<UpcomingResultsItem?>?) {
        data?.let { upcomingAdapter.setData(it) }
    }

}
