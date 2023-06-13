package com.binar.projekakhir.viewmodel

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.binar.projekakhir.model.auth.*
import com.binar.projekakhir.network.ApiService
import com.google.android.play.integrity.internal.t
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val api: ApiService
) : ViewModel() {

    var livedataregister: MutableLiveData<Data?> = MutableLiveData()

    fun getlivedataregister(): MutableLiveData<Data?> {
        return livedataregister
    }

    var livedatalogin: MutableLiveData<List<Data>> = MutableLiveData()

    fun getlivedatalogin(): MutableLiveData<List<Data>> {
        return livedatalogin
    }


    fun postregist(fullName : String, email : String, password : String, telephone : String) {
        api.register(fullName, email, password, telephone).enqueue(object : Callback<Data>{
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>
            ) {
                if (response.isSuccessful) {
                    livedataregister.postValue(response.body()!!)
                    Log.i("STATUS", response.body()!!.status.toString())
                }


            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                livedataregister.postValue(null)

            }

        })




    }

//    fun postlogin(loginBody: LoginBody) {
//        api.login(loginBody).enqueue(object : Callback<List<Data>> {
//            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
//                if (response.isSuccessful) {
//                    livedatalogin.postValue(response.body())
//                } else {
//                    livedatalogin.postValue(null)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
//
//            }
//
//        })
//    }


//
//    fun register(registerData: Data) {
//        api.register(registerData).enqueue(object : Callback<List<RegisterBody>> {
//            override fun onResponse(
//                call: Call<List<RegisterBody>>,
//                response: Response<List<RegisterBody>>
//            ) {
//                if (response.isSuccessful){
//                    livedataregister.postValue(response.body())
//                }else{
//                    livedataregister.postValue(emptyList())
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<RegisterBody>>, t: Throwable) {
//                livedataregister.postValue(emptyList())
//            }
//
//
//        })
//
//
//    }
//
//    fun postlogin(loginData : Data){
//        api.login(loginData).enqueue(object : Callback<List<LoginBody>>{
//            override fun onResponse(
//                call: Call<List<LoginBody>>,
//                response: Response<List<LoginBody>>
//            ) {
//                if (response.isSuccessful){
//                    livedatalogin.postValue(response.body())
//                }else{
//                    livedataregister.postValue(emptyList())
//                }
//            }
//
//            override fun onFailure(call: Call<List<LoginBody>>, t: Throwable) {
//                livedataregister.postValue(emptyList())
//            }
//
//        })
//
//    }

}

