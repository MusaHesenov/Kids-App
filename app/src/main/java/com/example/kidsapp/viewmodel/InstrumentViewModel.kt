package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Instruments
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstrumentViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel(){
    private val  _instrumentsList = MutableStateFlow<Resource<List<Instruments>>>(Resource.Unspecified())
    val instrumentsList = _instrumentsList.asStateFlow()


    init {
        fetchInstrumentsList()
    }

    private fun fetchInstrumentsList() {
        firestore.collection("category").document("instruments").collection("instruments").get()
            .addOnSuccessListener {
                val instrumentsList = it.toObjects(Instruments::class.java)
                _instrumentsList.value = Resource.Success(instrumentsList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _instrumentsList.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}