package com.example.packyourtrip.ui.checklist.things

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.packyourtrip.data.repository.things.ThingsRepository
import javax.inject.Inject

class ThingsCheckListViewModel @Inject constructor(
    private val repository: ThingsRepository
) {

    private val _signIn = MutableLiveData<Intent>()
    val signIn: LiveData<Intent> get() = _signIn
}