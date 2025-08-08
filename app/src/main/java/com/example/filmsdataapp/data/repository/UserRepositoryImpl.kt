package com.example.filmsdataapp.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : UserRepository {

    override fun createUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit) {
        val finalImageUri =
            image ?: Uri.parse("android.resource://${context.packageName}/${R.drawable.user_icon}")
        val currentUser = auth.currentUser ?: return
        val uid = currentUser.uid

        val profileUpdates = userProfileChangeRequest {
            displayName = nickname
            photoUri = finalImageUri
        }

        uploadImageToStorage(finalImageUri) { downloadUrl ->
            currentUser.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = hashMapOf(
                            "nickname" to nickname,
                            "description" to description,
                            "image" to downloadUrl
                        )

                        firestore.collection("users").document(uid).set(user)
                            .addOnSuccessListener {
                                Log.d("DEBUG", "User data saved with UID: $uid")
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                Log.w("DEBUG", "Error adding user document", e)
                            }
                    } else {
                        Log.e("DEBUG", "Failed to update profile", task.exception)
                    }
                }
        }


    }

    override fun setUserRatingForTitle(title: Title, rating: Float, where: String) {
        val uid = auth.currentUser?.uid ?: return
        val db = firestore
        val titleId = title.id ?: return
        val titleName = title.primaryTitle ?: "Unknown title"
        val docRef = db.collection("users")
            .document(uid)
            .collection(where)
            .document(titleId)
        docRef.update("userRating", (rating*2).toInt())
            .addOnSuccessListener {
                val historyRef = db.collection("users")
                    .document(uid)
                    .collection("history")
                    .document()
                val historyEntry = mapOf(
                    "message" to "$titleName was rated ${(rating*2).toInt()}.",
                    "timestamp" to FieldValue.serverTimestamp()
                )
                historyRef.set(historyEntry)
                    .addOnSuccessListener {
                        Log.d("HISTORY", "History entry added.")
                    }
                    .addOnFailureListener { e ->
                        Log.e("HISTORY", "Failed to add history entry", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.e("RATING", "Failed to update rating for $titleId", e)
            }

    }

    override fun getUserRatingForTitle(titleId: String, where: String, onResult: (Int?) -> Unit) {
        val uid = auth.currentUser?.uid ?: return
        val db = firestore

        val docRef = db.collection("users")
            .document(uid)
            .collection(where)
            .document(titleId)

        docRef.get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val rating = snapshot.getLong("userRating")?.toInt()
                    onResult(rating)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("RATING", "Failed to load userRating", e)
                onResult(null)
            }
    }

    override fun changeUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit) {
        val currentUser = auth.currentUser ?: return
        val uid = currentUser.uid


        if (image != null && image.scheme != "https") {
            uploadImageToStorage(image) { downloadUrl ->
                applyProfileChanges(currentUser, nickname, downloadUrl, description, uid,onSuccess )
            }
        } else {

            val existingPhotoUrl = currentUser.photoUrl?.toString()
                ?: "https://..."

            applyProfileChanges(currentUser, nickname, existingPhotoUrl, description, uid, onSuccess)
        }
    }

    private fun applyProfileChanges(
        currentUser: FirebaseUser,
        nickname: String,
        imageUrl: String,
        description: String,
        uid: String,
        onSuccess: () -> Unit
    ) {
        val profileUpdates = userProfileChangeRequest {
            displayName = nickname
            photoUri = Uri.parse(imageUrl)
        }

        currentUser.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = hashMapOf(
                        "nickname" to nickname,
                        "description" to description,
                        "image" to imageUrl
                    )

                    firestore.collection("users").document(uid).set(user)
                        .addOnSuccessListener {
                            Log.d("TEKKEN", "User data updated")
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            Log.w("TEKKEN", "Error updating user document", e)
                        }
                } else {
                    Log.e("TEKKEN", "Failed to update profile", task.exception)
                }
            }
    }

    fun uploadImageToStorage(imageUri: Uri, onUploaded: (String) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val storageRef = storage.reference
            .child("user_images/$uid.jpg")

        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    onUploaded(uri.toString())
                }
            }
            .addOnFailureListener { e ->
                Log.e("TEKKEN", "Failed to upload image", e)
            }
    }


}