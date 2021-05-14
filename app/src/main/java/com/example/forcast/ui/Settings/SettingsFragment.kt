package com.example.forcast.ui.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat

import com.example.forcast.R

class SettingsFragment : PreferenceFragmentCompat()
{
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefrences)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Settings"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
    }
 //    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View?
 //    {
 //        val root = inflater.inflate(R.layout.fragment_settings, container, false)
//
//
//
 //        return root
 //    }

}
