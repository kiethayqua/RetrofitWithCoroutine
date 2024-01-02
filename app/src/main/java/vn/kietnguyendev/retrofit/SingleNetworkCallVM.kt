package vn.kietnguyendev.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ViewState(
    val users: List<ApiUser> = emptyList()
)

class SingleNetworkCallVM(private val apiHelper: ApiHelper): ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ViewState())
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = apiHelper.getUsers()
                _state.update { it.copy(users = users) }
                println("KIET_DEBUG_data_from SingleNetworkCallVM::fetchUsers: $users")
            } catch (e: Exception) {
                println("KIET_DEBUG_SingleNetworkCallVM::fetchUsers was failed with Exception: ${e.message}")
            }
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val apiHelper = ApiHelperImpl(RetrofitBuilder.apiService)
                return SingleNetworkCallVM(apiHelper = apiHelper) as T
            }
        }
    }
}