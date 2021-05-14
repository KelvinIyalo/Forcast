package com.example.forcast.ui.weather.future.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.forcast.R

class WheatherDeatilsFragment : Fragment() {

    companion object {
        fun newInstance() =
            WheatherDeatilsFragment()
    }

    private lateinit var viewModel: WheatherDeatilsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wheather_deatils_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WheatherDeatilsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
