package com.example.packyourtrip.ui.main.defaultlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.packyourtrip.R


class SavedListsFragment : Fragment(R.layout.fragment_saved_lists) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SavedListsFragment()
    }
}