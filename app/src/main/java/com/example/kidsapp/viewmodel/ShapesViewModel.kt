package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Shapes
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShapesViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {
    private val _shapesList = MutableStateFlow<Resource<List<Shapes>>>(Resource.Unspecified());
    val shapesList = _shapesList.asStateFlow()

    init {
        fetchShapesList()
    }

    private fun fetchShapesList() {
        firestore.collection("category").document("shapes").collection("shapes").get()
            .addOnSuccessListener {
                val shapesList = it.toObjects(Shapes::class.java)
                _shapesList.value = Resource.Success(shapesList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _shapesList.emit(Resource.Error(it.message.toString()))
                }
            }
    }

}