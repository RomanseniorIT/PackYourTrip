package com.example.packyourtrip.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.packyourtrip.R
import com.example.packyourtrip.injectViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
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
                findNavController().navigate(R.id.action_splashFragment_to_tripCheckListFragment)
            } else {
                createSignInIntent()
            }
        })
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
            //, AuthUI.IdpConfig.PhoneBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_launcher_foreground)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(requireActivity(), "Авторизация: ${user.email}", Toast.LENGTH_LONG).show();
                findNavController().navigate(R.id.action_splashFragment_to_tripCheckListFragment)
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Ошибка авторизации: ${response?.error?.errorCode}",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 100
    }

}