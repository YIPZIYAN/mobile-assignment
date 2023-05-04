package com.example.assignment.employee

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.api.RetrofitBuild
import com.example.assignment.dataclass.*
import com.google.gson.Gson
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterviewEmployeeViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var currentUser: User

    val sharedPreferences = application.getSharedPreferences("User", Context.MODE_PRIVATE)
    val loginResponse: MutableLiveData<ResponseForUI> by lazy {
        MutableLiveData<ResponseForUI>()
    }

    val jobInterviewList: MutableLiveData<List<JobInterviewItem>> by lazy {
        MutableLiveData<List<JobInterviewItem>>()
    }

    fun autoLogin() {
        val build = RetrofitBuild.build().autoLogin(
            sharedPreferences.getString("Token", "")!!,
            sharedPreferences.getString("Id", "")!!
        )



        build.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    currentUser = response.body()!!
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {

                Log.i("check", "onFailure: no")
            }
        })
    }

    fun getData() {
        val build = RetrofitBuild.build().getJobInterview(
            sharedPreferences.getString("Token", "")!!
        )

        build.enqueue(object : Callback<List<JobInterviewItem>?> {
            override fun onResponse(
                call: Call<List<JobInterviewItem>?>,
                response: Response<List<JobInterviewItem>?>
            ) {
                if (response.isSuccessful) {

                    loginResponse.value = ResponseForUI(true, "")
                    jobInterviewList.value = response.body()!!
                    Log.d("success", "onResponse: "+jobInterviewList.value)

                } else { //unknown error

                    loginResponse.value = ResponseForUI(false, "Something Went Wrong")

                }
            }

            override fun onFailure(call: Call<List<JobInterviewItem>?>, t: Throwable) {
                Log.d("fail", "onFailure: " + t.message)

                loginResponse.value =
                    ResponseForUI(false, "Something Went Wrong. Kindly check your connection")


            }


        })

    }
}