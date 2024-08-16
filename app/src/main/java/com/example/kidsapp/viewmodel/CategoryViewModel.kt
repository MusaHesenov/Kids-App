package com.example.kidsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kidsapp.data.Category
import com.example.kidsapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ViewModel() {
    private val _categoryList = MutableStateFlow<Resource<List<Category>>>(Resource.Unspecified());
    val categoryList = _categoryList.asStateFlow()

    private val _categoryListLimit = MutableStateFlow<Resource<List<Category>>>(Resource.Unspecified());
    val categoryListLimit = _categoryListLimit.asStateFlow()

    init {
        fetchCategoryList()
        fetchCategoryListLimit()
    }

    private fun fetchCategoryListLimit() {
        firestore.collection("category").limit(3).get()
            .addOnSuccessListener {
                val categoryListLimit = it.toObjects(Category::class.java)
                _categoryListLimit.value = Resource.Success(categoryListLimit)
            }
            .addOnFailureListener {
                _categoryList.value = Resource.Error(it.message.toString())
            }
    }

    fun fetchCategoryList(){
        firestore.collection("category").get()
            .addOnSuccessListener {
                val categoryList = it.toObjects(Category::class.java)
                _categoryList.value = Resource.Success(categoryList)
            }
            .addOnFailureListener {
                _categoryList.value = Resource.Error(it.message.toString())
            }
    }
}