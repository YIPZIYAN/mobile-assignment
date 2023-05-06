package com.example.assignment.recycleviews

import android.content.Context
import android.os.Bundle
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
import com.example.assignment.databinding.ItemJobPostBinding
import com.example.assignment.databinding.NavigationEmployeeBinding
import com.example.assignment.dataclass.JobPostItem
import com.example.assignment.employee.EmployeeNavHost
import com.example.assignment.employee.JobDetailsEmployeeFragment

class JobPostRecyclerAdapter(private val dataList: List<JobPostItem>) : RecyclerView.Adapter<JobPostRecyclerAdapter.ViewHolder>() {

    lateinit var binding : ItemJobPostBinding

    inner class ViewHolder(val binding: ItemJobPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JobPostItem) {
            binding.jobPostItem = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemJobPostBinding.inflate(inflater, parent, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
        holder.binding.jobId.text = item.id.toString()
        holder.binding.jobCard.setOnClickListener {view ->
            val bundle = Bundle()
            bundle.putString("position", holder.binding.jobId.text.toString())
            view.findNavController().navigate(R.id.action_findJobsEmployeeFragment_to_jobDetailsEmployeeFragment, bundle)

        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}
