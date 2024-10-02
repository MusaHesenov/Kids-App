package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Colors
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    private val _colorsList = MutableStateFlow<Resource<List<Colors>>>(Resource.Unspecified());
    val colorsList = _colorsList.asStateFlow()

    init {
        fetchColorsList()
    }

    private fun fetchColorsList() {
        firestore.collection("category").document("colors").collection("colors").get()
            .addOnSuccessListener {
                val colorsList = it.toObjects(Colors::class.java)
                _colorsList.value = Resource.Success(colorsList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _colorsList.emit(Resource.Error(it.message.toString()))
                }
            }
    }


}