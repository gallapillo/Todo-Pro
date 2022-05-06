package com.gallapillo.todopro.domain.use_case.firebase

data class FirebaseUseCase (
    val firebaseSignOut: FirebaseSignOutUseCase,
    val firebaseConnect: FirebaseConnectUseCase
)