package com.example.jetpacksubmission.ui.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.tvshow.popular.PopularResultsItem
import com.example.jetpacksubmission.ui.adapter.PopularTvshowAdapter
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TvshowFragment : Fragment(), TvshowView {

    private lateinit var popularTvshowAdapter: PopularTvshowAdapter
    private lateinit var viewModel: TvshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("TvshowFragment", "Enter onActivityCreated")
        if (activity != null) {
            popularTvshowAdapter = PopularTvshowAdapter()

            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvshowViewModel::class.java]

            viewModel.setPopularTvshow(this)

            if (viewModel.getPopularListData() != null){
                Log.d(
                    "TvshowFragment",
                    "Enter onActivityCreated - viewmodel.PopularListData not null"
                )
                popularTvshowAdapter.setData(viewModel.getPopularListData()!!)
            }

            with(fragmenttvshow_rv_popular) {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = popularTvshowAdapter
            }
        }
    }

    override fun onSuccess(data: ArrayList<PopularResultsItem?>?) {
        data?.let {
            Log.d("TvshowFragment", "Enter onSuccess")
            popularTvshowAdapter.setData(it)
        }
    }

}
