package ir.example.newstest.util

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ir.example.newstest.base.BaseViewModel

/**
 * A class to implement command pattern for navigation process
 *
 * To() with the param directions (NavDirections) devs using this call *FragmentDirections action
 *
 */
sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}

fun LifecycleOwner.addNavigatorOn(
    viewModel: BaseViewModel,
    navController: NavController
) {
    viewModel.navigationCommand.observeEvent(this) { command ->
        when (command) {
            is NavigationCommand.To ->
                navController.navigate(command.directions)
        }
    }
}