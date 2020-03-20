package com.example.jetpacksubmission.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.data.tvshow.popular.PopularTvshowResponse
import com.example.jetpacksubmission.ui.viewmodel.MovieViewModel
import com.example.jetpacksubmission.ui.viewmodel.TvshowViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private lateinit var movieViewModel : MovieViewModel
    private lateinit var tvshowViewModel : TvshowViewModel

    private var resultMovie : UpcomingMovieResponse? = UpcomingMovieResponse()
    private var resultTvshow : PopularTvshowResponse? = PopularTvshowResponse()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        val responseMovie = ApiMain().getMovieApi().getUpcomingMovie().execute()
        resultMovie = responseMovie.body()

        val responseTvshow = ApiMain().getTvshowApi().getPopularTvshow().execute()
        resultTvshow = responseTvshow.body()
    }

    @Test
    fun loadMovie(){

        Thread.sleep(3000)
        //checking is the movie recyclerview is oke
        Espresso.onView(ViewMatchers.withId(R.id.fragmentmovie_rv_upcoming)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragmentmovie_rv_upcoming)).perform(resultMovie?.results?.size?.toInt()?.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it)
        })
        Thread.sleep(3000)
    }

    @Test
    fun loadDetailMovie(){
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.fragmentmovie_rv_upcoming)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_title)).check(ViewAssertions.matches(ViewMatchers.withText(resultMovie?.results?.get(0)?.title)))

        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_rating)).check(ViewAssertions.matches(ViewMatchers.withText(resultMovie?.results?.get(0)?.voteAverage.toString())))

        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetail_tv_description)).check(ViewAssertions.matches(ViewMatchers.withText(resultMovie?.results?.get(0)?.overview)))

        Espresso.closeSoftKeyboard()
        Thread.sleep(3000)
    }

    @Test
    fun loadTvshow(){
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withText("Tv Show")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragmenttvshow_rv_popular)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragmenttvshow_rv_popular)).perform(resultTvshow?.results?.size?.toInt()?.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it)
        })
    }

    @Test
    fun loadDetailTvshow(){
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withText("Tv Show")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragmenttvshow_rv_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_title)).check(ViewAssertions.matches(ViewMatchers.withText(resultTvshow?.results?.get(0)?.name)))

        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_rating)).check(ViewAssertions.matches(ViewMatchers.withText(resultTvshow?.results?.get(0)?.voteAverage.toString())))

        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.contentdetailtvshow_tv_description)).check(ViewAssertions.matches(ViewMatchers.withText(resultTvshow?.results?.get(0)?.overview)))
    }
}