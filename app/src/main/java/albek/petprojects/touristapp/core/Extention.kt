package albek.petprojects.touristapp.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun <STATE : Any, EFFECT : Any, ACTION : Any> BaseViewModel<STATE, EFFECT, ACTION>.collectErrorFlow(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (ErrorModel) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(errorFlow, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            errorFlow.collect { errorModel -> collector(errorModel) }
        }
    }
}