package com.example.assignment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.assignment.databinding.FragmentFindJobsEmployeeBinding


class FindJobsEmployeeFragment : Fragment() {

    private lateinit var binding: FragmentFindJobsEmployeeBinding

    companion object {
        fun newInstance() = FindJobsEmployeeFragment()
    }

    private lateinit var viewModel: FindJobsEmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFindJobsEmployeeBinding>(inflater, R.layout.fragment_find_jobs_employee, container, false)
        binding.jobs1.setOnClickListener{
            view : View -> view.findNavController().navigate(R.id.action_findJobsEmployeeFragment_to_jobDetailsEmployeeFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindJobsEmployeeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}