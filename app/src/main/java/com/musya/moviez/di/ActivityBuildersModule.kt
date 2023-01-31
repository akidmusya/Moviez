package com.musya.moviez.di

import com.musya.moviez.di.home.HomeFragmentBuildersModule
import com.musya.moviez.ui.detail.DetailActivity
import com.musya.moviez.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity


}