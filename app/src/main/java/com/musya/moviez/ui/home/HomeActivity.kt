package com.musya.moviez.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.musya.moviez.databinding.ActivityHomeBinding
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    companion object{
        private val TAB_TITLES = arrayOf("Movies", "Tv Show", "Favorite")
    }

    private val adapter: SectionPageAdapter by lazy {
        SectionPageAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        reduceDragSensitivity(activityHomeBinding)
        activityHomeBinding.viewPager.also {
            it.adapter = adapter
            it.offscreenPageLimit = 1
        }

        TabLayoutMediator(activityHomeBinding.tabLayout, activityHomeBinding.viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()


        supportActionBar?.elevation = 0f
    }

    private fun reduceDragSensitivity(binding: ActivityHomeBinding) {
        try {
            val ff =
                    ViewPager2::class.java.getDeclaredField("mRecyclerView")
            ff.isAccessible = true
            val recyclerView = ff[binding.viewPager] as RecyclerView
            val touchSlopField =
                    RecyclerView::class.java.getDeclaredField("mTouchSlop")
            touchSlopField.isAccessible = true
            val touchSlop = touchSlopField[recyclerView] as Int
            touchSlopField[recyclerView] = touchSlop * 4
        } catch (e: NoSuchFieldException){
            e.printStackTrace()
        } catch (e: IllegalAccessException){
            e.printStackTrace()
        }
    }
}