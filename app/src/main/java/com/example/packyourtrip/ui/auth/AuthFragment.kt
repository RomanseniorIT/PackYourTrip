package com.example.packyourtrip.ui.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.packyourtrip.R
import com.example.packyourtrip.injectViewModel
import com.firebase.ui.auth.IdpResponse
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AuthFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_auth,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initObservers()

        authViewModel.signIn()
    }

    private fun init() {
        authViewModel = injectViewModel(viewModelFactory)
    }

    private fun initObservers() {
        authViewModel.signIn.observe(viewLifecycleOwner, {
            startForAuthUI.launch(it)
        })

        authViewModel.isLoggedIn.observe(viewLifecycleOwner, { loggedIn ->
            if (loggedIn) {
                findNavController().navigate(
                    AuthFragmentDirections.actionAuthFragmentToMainFragment()
                )
            } else {
                authViewModel.signIn()
            }
        })
    }

    private val startForAuthUI =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                authViewModel.isUserLoggedIn()
            } else {
                val response = IdpResponse.fromResultIntent(result.data)
                Toast.makeText(
                    requireActivity(),
                    "Ошибка авторизации: ${response?.error?.errorCode}",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
}