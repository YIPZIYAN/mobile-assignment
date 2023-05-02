package com.example.assignment.recycleviews

import android.content.Context
import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.databinding.FragmentFindJobsEmployeeBinding
import com.example.assignment.databinding.NavigationEmployeeBinding
import com.example.assignment.dataclass.JobPostItem
import com.example.assignment.employee.EmployeeNavHost

class JobPostRecyclerAdapter(private val dataList: List<JobPostItem>) : RecyclerView.Adapter<JobPostRecyclerAdapter.ViewHolder>() {
    private lateinit var binding2: NavigationEmployeeBinding

    inner class ViewHolder(val binding: FragmentFindJobsEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JobPostItem) {
            binding.jobPostItem = item
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentFindJobsEmployeeBinding.inflate(inflater, parent, false)
        binding.jobCard.setOnClickListener{
                view : View -> view.findNavController().navigate(R.id.action_findJobsEmployeeFragment_to_jobDetailsEmployeeFragment)

        }


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}
