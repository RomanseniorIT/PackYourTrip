package com.example.packyourtrip.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.packyourtrip.R
import com.example.packyourtrip.injectViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_splash,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initObservers()
        Handler(Looper.getMainLooper()).postDelayed({
            splashViewModel.isUserLoggedIn()
        }, 1800)
    }

    private fun init() {
        splashViewModel = injectViewModel(viewModelFactory)
    }

    private fun initObservers() {
        splashViewModel.isLoggedIn.observe(viewLifecycleOwner, { loggedIn ->
            if (loggedIn) {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_authFragment)
            }
        })
    }

}