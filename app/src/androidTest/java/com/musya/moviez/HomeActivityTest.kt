package com.musya.moviez

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.musya.moviez.ui.home.HomeActivity
import com.musya.moviez.utils.DataDummy
import com.musya.moviez.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies(false)
    private val dummyTvShow = DataDummy.generateDummyMovies(true)

    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test1LoadMovies(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_handle_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size-1))
    }

    @Test
    fun test2DetailMovie(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_handle_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyMovie[0].rate.toString())))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyMovie[0].synopsis)))
        onView(withId(R.id.tv_actors)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_actors)).check(matches(withText(dummyMovie[0].actors)))
        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(withText(dummyMovie[0].genres)))
    }

    @Test
    fun test3LoadTvShow(){
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.btn_handle_tv_show)).perform(ViewActions.swipeUp())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size-1))
    }

    @Test
    fun test4DetailTvShow(){
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_handle_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyTvShow[0].rate.toString())))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyTvShow[0].releaseDate)))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyTvShow[0].synopsis)))
        onView(withId(R.id.tv_actors)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_actors)).check(matches(withText(dummyTvShow[0].actors)))
        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(withText(dummyTvShow[0].genres)))
    }

    @Test
    fun test5AddFavorite(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()
    }

    @Test
    fun test6LoadFavorite(){
        onView(withText("Favorite")).perform(click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size-1))
    }
}