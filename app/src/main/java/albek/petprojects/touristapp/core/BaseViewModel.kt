package albek.petprojects.touristapp.core

import albek.petprojects.touristapp.R
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<STATE : Any, EFFECT : Any, ACTION : Any>
    : ViewModel(), ContainerHost<STATE, EFFECT> {
    private val mutableErrorFlow = MutableSharedFlow<ErrorModel>()
    val errorFlow get() = mutableErrorFlow.asSharedFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            Log.d("ErrorHandler", throwable.stackTrace.joinToString("\n"))
            mutableErrorFlow.emit(
                when(throwable) {
                    is IOException -> ErrorModel(
                        messageId = R.string.connection_error,
                        image = R.drawable.connection_error
                    )
                    is UnsuccessfulException -> ErrorModel(
                        messageId = R.string.unsuccessful_error,
                        image = R.drawable.unsucessful_error_icon
                    )
                    else -> ErrorModel(
                        messageId = R.string.unknown_error,
                        image = R.drawable.error_icon
                    )
                }
            )
        }
    }

    abstract fun doAction(action: ACTION)

    private val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + errorHandler)
    private val uiScope =
        CoroutineScope(SupervisorJob() + viewModelScope.coroutineContext + errorHandler)
    private val defaultScope = CoroutineScope(SupervisorJob() + Dispatchers.Default + errorHandler)

    fun runOnUi(action: suspend () -> Unit) = uiScope.launch { action() }
    fun runOnIo(action: suspend () -> Unit) = ioScope.launch { action() }
    fun runOnDefault(action: suspend () -> Unit) = defaultScope.launch { action() }
    fun runBlockingOnIo(action: suspend () -> Unit) =
        runBlocking(SupervisorJob() + Dispatchers.IO + errorHandler) {
            action()
        }
}