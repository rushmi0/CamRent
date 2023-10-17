package org.camrent.database.service.test

data class SimpleJSON(
    val active: Boolean,
    val formed: Int,
    val homeTown: String,
    val members: List<Member>,
    val secretBase: String,
    val squadName: String
)