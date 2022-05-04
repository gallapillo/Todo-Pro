package com.gallapillo.todopro.common

sealed class Screens(val route: String) {
    object Start: Screens("start_screen")
    object Main: Screens("main_screen")
    object Add: Screens("add_screen")
    object Note: Screens("note_screen")
}
