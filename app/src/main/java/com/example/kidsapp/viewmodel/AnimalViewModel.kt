package com.example.kidsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsapp.data.Animal
import com.example.kidsapp.data.AnimalCategory
import com.example.kidsapp.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    private val _animalList = MutableStateFlow<Resource<List<Animal>>>(Resource.Unspecified())
    val animalList = _animalList.asStateFlow()

    private val _animalCategoryList = MutableStateFlow<Resource<List<AnimalCategory>>>(Resource.Unspecified())
    val animalCategoryList = _animalCategoryList.asStateFlow()

    init {
        fetchAnimalCategoryList()
    }

     fun fetchAnimalList(animalType: String) {
        firestore.collection("category").document("animals").collection("animals").document(animalType).collection(animalType).get()
            .addOnSuccessListener {
                val animalList = it.toObjects(Animal::class.java)
                animalList.forEach { animal ->
                    Log.d("vmodel", "Animal: ${animal.name}, ${animal.description}, ${animal.image}")
                }
                viewModelScope.launch {
                    _animalList.emit(Resource.Success(animalList))
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    _animalList.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    private fun fetchAnimalCategoryList() {
        firestore.collection("category").document("animals").collection("animals").get()
            .addOnSuccessListener {
                val categoryList = it.toObjects(AnimalCategory::class.java)
                _animalCategoryList.value = Resource.Success(categoryList)
            }.addOnFailureListener {
                viewModelScope.launch {
                    _animalCategoryList.emit(Resource.Error(it.message.toString()))
                }

            }
    }

}