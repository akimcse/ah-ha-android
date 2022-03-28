package com.example.ahha_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.databinding.FragmentSignBinding
import com.example.ahha_android.ui.main.MainActivity
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.setStatusBarColor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope

class SignFragment : Fragment() {
    private lateinit var binding: FragmentSignBinding
    private val viewModel: SignViewModel by activityViewModels()
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val pushToken =
                EasyPeasySharedPreference.getPushToken() ?: return@registerForActivityResult
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.loginUser(
                    authCode = account.serverAuthCode,
                    pushToken = pushToken
                )
            } catch (e: ApiException) {
                Log.e(SignFragment::class.java.simpleName, e.stackTraceToString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setStatusBarColor(requireActivity(), R.color.blue)

        viewModel.setAccessToken(null)
        addListener()
        addObserver()

        return binding.root
    }

    private fun addListener() {
        binding.buttonSignUp.setOnClickListener {
            requestGoogleLogin()
        }
    }

    private fun addObserver() {
        viewModel.accessToken.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            val hasPlant = viewModel.hasPlant.value
            if (hasPlant == true) {
                viewModel.saveUserAccessToken(viewModel.accessToken.value)
                moveMainActivity()
            } else {
                moveSignPlantFragment()
            }
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope("https://www.googleapis.com/auth/gmail.readonly"))
            .requestScopes(Scope("https://www.googleapis.com/auth/pubsub"))
            .requestServerAuthCode(getString(R.string.google_login_client_id), true)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

    private fun moveSignPlantFragment() {
        findNavController().navigate(R.id.actionSignFragmentToSignPlantFragment)
    }

    private fun moveMainActivity() {
        requireActivity().run {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            finish()
        }
    }
}