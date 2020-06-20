package ir.example.newstest.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar
import ir.example.newstest.util.ActionCommand
import ir.example.newstest.util.Event
import ir.example.newstest.util.NavigationCommand

abstract class BaseViewModel() : ViewModel() {

    /** Used in situations when we need to navigate to another fragment/activity
     */
    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>>()
    val navigationCommand: LiveData<Event<NavigationCommand>>
        get() = _navigationCommand


    /**
     *      getting directions from the navigation's built actions
     */
    fun navigateTo(directions: NavDirections) {
        _navigationCommand.postValue(Event(NavigationCommand.To(directions)))
    }

    /**
     * using [NavController][androidx.navigation.NavController]'s navigateUp()
     */
    fun navigateBack() {
        _navigationCommand.postValue(Event(NavigationCommand.Back))
    }

    /**
     * using [NavController][androidx.navigation.NavController]'s popBackStack()
     */
    fun navigateBackTo(destinationId: Int, inclusive: Boolean) {
        _navigationCommand.postValue(Event(NavigationCommand.BackTo(destinationId, inclusive)))
    }

    private val _actionCommand = MutableLiveData<Event<ActionCommand>>()
    val actionCommand: LiveData<Event<ActionCommand>>
        get() = _actionCommand

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        _actionCommand.postValue(Event(ActionCommand.ShowToast(message, duration)))
    }

    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        _actionCommand.postValue(Event(ActionCommand.ShowToastRes(message, duration)))
    }

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        _actionCommand.postValue(Event(ActionCommand.ShowSnackBar(message, duration)))
    }

    fun showSnackBar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        _actionCommand.postValue(Event(ActionCommand.ShowSnackBarRes(message, duration)))
    }

}