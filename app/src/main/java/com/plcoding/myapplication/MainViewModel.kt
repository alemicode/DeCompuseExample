package com.plcoding.myapplication

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<MainState>(MainState.Init())
    val state: StateFlow<MainState> = _state.asStateFlow()

    var x = 0


    fun getData() {
        val data = generateFakeData()
        _state.value = MainState.Init(data = data, "mohamad")
    }


    fun plus() {
        x++
        Log.d("TAG", "plus: increas $x")
    }

    fun changeName(name: String) {

        _state.value = (state.value as MainState.Init).copy(
            name = "Soheil"
        )

    }
}


sealed class MainState() {
    data class Init(
        val data: List<MainModel> = listOf(),
        val name: String = "",
        val loading: State<Boolean> = mutableStateOf(true)
    ) : MainState()
}


data class MainModel(
    @DrawableRes val profile: Int,
    val name: String
)


fun generateFakeData(): List<MainModel> {
    val data = listOf<MainModel>(
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),
        MainModel(R.drawable.pecky, "tomas shleby"),


        )

    return data

}