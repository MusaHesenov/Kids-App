package com.example.kidsapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.elvin.e_commerce.utils.RegisterValidation
import com.elvin.e_commerce.utils.validateEmail
import com.example.kidsapp.data.User
import com.example.kidsapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UserAccountViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: StorageReference,
    private val app: Application
): AndroidViewModel(app) {

    private val _user = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val user = _user.asStateFlow()

    private val _updateInfo = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val updateInfo = _updateInfo.asStateFlow()

    var currentUser = User()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            _user.emit(Resource.Loading())
        }

        firestore.collection("users").document(auth.uid!!).get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                user?.let {
                    viewModelScope.launch {
                        _user.emit(Resource.Success(it))
                        currentUser = it
                    }
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _user.emit(Resource.Error(it.message.toString()))
                }
            }

    }

    fun updateUser(imgUri: Uri?, age: String?){
        val areInputsValid = validateEmail(currentUser.email) is RegisterValidation.Success

        if(!areInputsValid){
            viewModelScope.launch {
                _user.emit(Resource.Error("Check your inputs"))
            }
            return
        }
        viewModelScope.launch {
            _updateInfo.emit(Resource.Loading())
        }
        if(imgUri == null){
            saveUserInformation(currentUser, true, age)
        }else{
            saveUserInformationWithNewImage(currentUser, imgUri, age)
        }
    }

    private fun saveUserInformationWithNewImage(user: User, imgUri: Uri, age: String?) {
        viewModelScope.launch {
            try {
                val imageUri = MediaStore.Images.Media.getBitmap(app.contentResolver, imgUri)
                val byteArrayOutputStream = ByteArrayOutputStream()
                imageUri.compress(Bitmap.CompressFormat.JPEG, 96, byteArrayOutputStream)
                val imageByteArray = byteArrayOutputStream.toByteArray()
                val imageDirectory = storage.child("profileImages/${auth.uid}/${UUID.randomUUID()}")
                val result = imageDirectory.putBytes(imageByteArray).await()
                val imageUrl = result.storage.downloadUrl.await().toString()
                saveUserInformation(user.copy(imagePath = imageUrl, age = age ?: "0"), false, age)

            }catch (e: Exception){
                viewModelScope.launch {
                    _updateInfo.emit(Resource.Error(e.message.toString()))
                }
            }

        }

    }

    private fun saveUserInformation(user: User, shouldRetrieveOldImage: Boolean, age: String?) {
        firestore.runTransaction { transaction ->
            val documentRef = firestore.collection("users").document(auth.uid!!)
            if(shouldRetrieveOldImage){
                val currentUser = transaction.get(documentRef).toObject(User::class.java)
                val newUser = user.copy(imagePath = currentUser?.imagePath?: "", age = age?: "0")
                transaction.set(documentRef, newUser)
            }else{
                transaction.set(documentRef, user)
            }
        }.addOnSuccessListener {
            viewModelScope.launch {
                _updateInfo.emit(Resource.Success(user))
            }
        }.addOnFailureListener {
            viewModelScope.launch {
                _updateInfo.emit(Resource.Error(it.message.toString()))
            }
        }

    }

}