package com.daniilk87.simplebook.ui.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteViewModelTest{

    private lateinit var viewModel: NoteViewModel

    private val mockRepository = mockk<Repository>()
    private val noteLiveData = MutableLiveData<NoteResult>()
    private val testNote = Note("1")

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        every { mockRepository.getNoteById(testNote.id) } returns (noteLiveData)
        every { mockRepository.deleteNotes(testNote.id) } returns (noteLiveData)
        viewModel = NoteViewModel(mockRepository)
    }


    @Test
    fun `loadNote should return Note`() {
        var result: NoteData.Data? = null
        var testData = NoteData.Data(false,testNote)
        viewModel.getViewState().observeForever {
            result = it?.data
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Success(testNote)
        assertEquals(testData, result)
    }

    @Test
    fun `loadNote should return Error`() {
        var result: Throwable? = null
        var testData = Throwable("error")
        viewModel.getViewState().observeForever {
            result = it?.error
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Error(error = testData)
        assertEquals(testData,result)
    }

    @Test
    fun `delete should return NoteData with isDeleted`() {
        var result: NoteData.Data? = null
        val testData = NoteData.Data(true, null)
        viewModel.getViewState().observeForever {
            result = it?.data
        }

        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Success(null)
        assertEquals(testData, result)
    }

    @Test
    fun `delete should return error`() {
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever {
            result = it?.error
        }
        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Error(error = testData)
        assertEquals(testData, result)
    }

}