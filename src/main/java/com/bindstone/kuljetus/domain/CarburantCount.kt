package com.bindstone.kuljetus.domain

import java.util.*

class CarburantCount {
    var count: Long? = null
    var libelleCarburant: String? = null

    override fun toString(): String {
        return StringJoiner(",", "[", "]")
                .add("libelleCarburant='$libelleCarburant'")
                .add("count='$count'")
                .toString() + "\n"
    }
}
