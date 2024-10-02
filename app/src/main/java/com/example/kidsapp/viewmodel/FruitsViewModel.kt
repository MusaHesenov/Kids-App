package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Fruits
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {
    private val _fruitsList = MutableStateFlow<Resource<List<Fruits>>>(Resource.Unspecified());
    val fruitsList = _fruitsList.asStateFlow()

    init {
        fetchFruitsList()
    }

    private fun fetchFruitsList(){
        firestore.collection("category").document("fruits").collection("fruits").get()
            .addOnSuccessListener {
                val fruitsList = it.toObjects(Fruits::class.java)
                _fruitsList.value = Resource.Success(fruitsList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _fruitsList.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}


