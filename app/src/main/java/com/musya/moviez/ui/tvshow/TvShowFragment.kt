package com.musya.moviez.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.musya.moviez.databinding.FragmentTvShowBinding
import com.musya.moviez.viewmodel.ViewModelFactory
import com.musya.moviez.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvAdapter = TvAdapter()

            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null){
                    when(tvShow.status){
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            tvAdapter.setTvShow(tvShow.data)
                            tvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShow){
                layoutManager = GridLayoutManager(context, 2)
                adapter = tvAdapter
            }
        }
    }
}