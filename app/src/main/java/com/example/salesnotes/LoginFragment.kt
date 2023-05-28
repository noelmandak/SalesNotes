package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.salesnotes.data.LoginResult
import com.example.salesnotes.data.TokenManager
import com.example.salesnotes.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(username,password)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Sales Notes Apps")


        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is LoginResult.Success -> {
                    val idSales = result.idSales
                    val name = result.name
                    val token = result.token
                    val status = result.status
                    if (status == "success") {
                        if (token != null) sharedViewModel.token = token
                        if (name != null) sharedViewModel.salesName = name
                        if (idSales != null) sharedViewModel.salesId = idSales

                        findNavController().navigate(R.id.action_login2_to_order)
                        sharedViewModel.isLogin.value = false
                    } else {
                        Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_LONG).show()
                    }
                }
                is LoginResult.Error -> {
                    val errorMessage = result.errorMessage
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
        return binding.root
    }

}