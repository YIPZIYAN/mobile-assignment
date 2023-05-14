package com.example.assignment.employer

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.auth.AuthActivity
import com.example.assignment.auth.SignUpEmployerViewModel
import com.example.assignment.databinding.FragmentCareerDevelopmentEmployeeBinding
import com.example.assignment.databinding.FragmentCareerDevelopmentEmployerBinding
import com.example.assignment.databinding.FragmentFindJobsEmployeeBinding
import com.example.assignment.databinding.ItemJobPostBinding
import com.example.assignment.employee.recycleviews.CareerDevelopmentEmployeeRecyclerAdapter
import com.example.assignment.employee.recycleviews.JobPostRecyclerAdapter
import com.example.assignment.employer.CareerDevelopmentEmployerViewModel
import com.example.assignment.employer.recycleviews.CareerDevelopmentEmployerRecyclerAdapter

class CareerDevelopmentEmployerFragment : Fragment() {

    companion object {
        fun newInstance() = CareerDevelopmentEmployerFragment()
    }

    private lateinit var binding: FragmentCareerDevelopmentEmployerBinding
    private lateinit var manager: RecyclerView.LayoutManager
    val sharedViewModel: CareerDevelopmentEmployerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_career_development_employer,
            container,
            false
        )
        manager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel.getData()

        sharedViewModel.careerDevelopmentList.observe(viewLifecycleOwner, Observer {
            binding.careerDevelopmentEmployerRecycleView.apply {
                adapter = CareerDevelopmentEmployerRecyclerAdapter(sharedViewModel, it)
                layoutManager = manager
            }

            Log.d("acticity", "onActivityCreated: "+it)
        })

        //navigate to form
        binding.floatingActionButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_careerDevelopmentEmployerFragment_to_addCareerDevelopmentEmployerFragment)
        }

        binding.careerDevelopmentEmployerRefresh.setOnRefreshListener {
            sharedViewModel.getData()
            binding.careerDevelopmentEmployerRefresh.isRefreshing = false
        }

        sharedViewModel.getAllResponse.observe(viewLifecycleOwner, Observer { response ->
            if (!response.success) {
                sharedViewModel.isExpired.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        //dialog
                        val intent = Intent(requireActivity(), AuthActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), response.errorMsg, Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        })


    }

}