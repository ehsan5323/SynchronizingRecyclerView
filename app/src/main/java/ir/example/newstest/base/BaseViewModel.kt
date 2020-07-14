package ir.example.newstest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ir.example.newstest.util.Event
import ir.example.newstest.util.NavigationCommand

abstract class BaseViewModel() : ViewModel() {

    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>>()
    val navigationCommand: LiveData<Event<NavigationCommand>>
        get() = _navigationCommand

    fun navigateTo(directions: NavDirections) {
        _navigationCommand.postValue(Event(NavigationCommand.To(directions)))
    }

}