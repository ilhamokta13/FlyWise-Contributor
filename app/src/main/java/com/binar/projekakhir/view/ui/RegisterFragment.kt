package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRegisterBinding
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.model.auth.RegisterBody
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val userVm : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDaftar.setOnClickListener {
            register()
        }

        binding.tvMasukdisini.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


    }

    private fun register() {
            val fullName = binding.txtInputLayoutNama.text.toString()
            val email = binding.txtInputLayoutEmail.text.toString()
            val telephone = binding.txtInputNoTelp.text.toString()
            val password = binding.txtInputLayoutPass.text.toString()

        if (fullName.isEmpty() || email.isEmpty() || telephone.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT)
                .show()
        } else {
            userVm.postregist(email,fullName, telephone,password)
            Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)


        }
    }

    }

