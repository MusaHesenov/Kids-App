package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Flags
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlagsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {
    private val _flagsList = MutableStateFlow<Resource<List<Flags>>>(Resource.Unspecified());
    val flagsList = _flagsList.asStateFlow()

    init {
        fetchFlagsList()
    }


    private fun fetchFlagsList(){
        firestore.collection("category").document("flags").collection("flags").get()
            .addOnSuccessListener {
                val flagsList = it.toObjects(Flags::class.java)
                _flagsList.value = Resource.Success(flagsList)
            }.addOnFailureListener{
                viewModelScope.launch {
                    _flagsList.emit(Resource.Error(it.message.toString()))
                }
            }
    }



}