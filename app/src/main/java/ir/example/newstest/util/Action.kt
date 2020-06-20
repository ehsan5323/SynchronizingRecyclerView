package ir.example.newstest.util

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.util.ktx.showSnackBar
import ir.example.newstest.util.ktx.showToast

/**
 * A class to handle most used actions inside viewModel
 *
 */
sealed class ActionCommand {
    data class ShowToast(val message: String, val duration: Int) : ActionCommand()
    data class ShowToastRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
    data class ShowSnackBar(val message: String, val duration: Int) : ActionCommand()
    data class ShowSnackBarRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
}

fun LifecycleOwner.observeActions(
    viewModel: BaseViewModel
) {
    viewModel.actionCommand.observeEvent(this) { command ->
        when (command) {

            is ActionCommand.ShowToast ->
                when (this) {
                    is ComponentActivity -> showToast(command.message, command.duration)
                    is Fragment -> showToast(command.message, command.duration)
                }

            is ActionCommand.ShowToastRes ->
                when (this) {
                    is ComponentActivity -> showToast(command.message, command.duration)
                    is Fragment -> showToast(command.message, command.duration)
                }

            is ActionCommand.ShowSnackBar ->
                when (this) {
                    is Fragment ->
                        showSnackBar(command.message, command.duration)
                    is ComponentActivity ->
                        findViewById<ViewGroup>(android.R.id.content).showSnackBar(command.message, command.duration)
                }

            is ActionCommand.ShowSnackBarRes ->
                when (this) {
                    is Fragment ->
                        showSnackBar(command.message, command.duration)
                    is ComponentActivity ->
                        findViewById<ViewGroup>(android.R.id.content).showSnackBar(command.message, command.duration)
                }

        }
    }
}