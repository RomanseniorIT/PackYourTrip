package com.example.packyourtrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.packyourtrip.ui.checklist.TripCheckListFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TripCheckListFragment.newInstance())
                .commit()
        }
    }
}