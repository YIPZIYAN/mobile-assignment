package com.example.assignment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.assignment.databinding.FragmentChangePasswordBinding
import com.example.assignment.databinding.FragmentEditProfileEmployeeBinding
import com.example.assignment.databinding.FragmentJobDetailsEmployeeBinding

class EditProfileEmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = EditProfileEmployeeFragment()
    }

    private lateinit var viewModel: EditProfileEmployeeViewModel

    private lateinit var binding: FragmentEditProfileEmployeeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentEditProfileEmployeeBinding>(
            inflater,
            R.layout.fragment_edit_profile_employee, container, false
        )

        binding.imageView.setOnClickListener { view ->
            view.findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditProfileEmployeeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}