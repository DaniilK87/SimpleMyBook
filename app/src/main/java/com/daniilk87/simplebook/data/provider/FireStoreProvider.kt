package com.daniilk87.simplebook.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.error.NoAuthException
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class FireStoreProvider: RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val TAG = "${FireStoreProvider::class.java.simpleName}:"

    private val db by lazy {FirebaseFirestore.getInstance()}
    private val notesReference
        get() = currentUser?.let {
            db.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
        } ?: throw NoAuthException()

    private val currentUser
        get () = FirebaseAuth.getInstance().currentUser


    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser?.let { User(it.displayName ?: "", it.email ?: "")
        }
    }

    override fun subscribeToAllNotes(): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            notesReference.addSnapshotListener { snapshot, e ->
                e?.let {

                } ?: snapshot?.let {
                    val notes = snapshot.documents.mapNotNull { it.toObject(Note::class.java) }
                    value = NoteResult.Success(notes)
                }
            }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

    override fun saveNote(note: Note): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            notesReference.document(note.id).set(note)
                .addOnSuccessListener { snapshot ->
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

    override fun getNoteById(id: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            notesReference.document(id).get()
                .addOnSuccessListener { snapshot ->
                    val note = snapshot.toObject(Note::class.java)
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

}