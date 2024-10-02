package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Jobs
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel(){
    private val _jobsList = MutableStateFlow<Resource<List<Jobs>>>(Resource.Unspecified())
    val jobsList = _jobsList.asStateFlow()

    init {
        fetchJobsList()
    }

    private fun fetchJobsList(){
        firestore.collection("category").document("jobs").collection("jobs").get()
            .addOnSuccessListener {
                val jobsList = it.toObjects(Jobs::class.java)
                _jobsList.value = Resource.Success(jobsList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _jobsList.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}