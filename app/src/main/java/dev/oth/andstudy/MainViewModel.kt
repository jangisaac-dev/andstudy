package dev.oth.andstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var isTextDisplaying = MutableLiveData<Boolean>(true)
    var tvText = MutableLiveData<String>("MainActivity")

    fun reverseTextDisplaying() {
        isTextDisplaying.value = !(isTextDisplaying.value ?: true)
    }
}