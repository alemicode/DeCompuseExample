package com.plcoding.myapplication

data class MainAction(
    val changeName: (name: String) -> Unit = {},
    val getData: () -> Unit = {},
    val plus : () -> Unit = {}
)
