package com.bindstone.kuljetus.domain

import java.util.*

class LibelleMarqueCount {
    var count: Long? = null
    var libelleMarque: String? = null

    override fun toString(): String {
        return StringJoiner(",", "[", "]")
                .add("libelleMarque='$libelleMarque'")
                .add("count='$count'")
                .toString() + "\n"
    }
}
