package com.bindstone.kuljetus.domain

import com.bindstone.kuljetus.domain.enumeration.Categorie
import java.util.*

class CategorieCount {
    var count: Long? = null
    var categorieStatec: Categorie? = null

    override fun toString(): String {
        return StringJoiner(",", "[", "]")
                .add("categorieStatec='$categorieStatec'")
                .add("count='$count'")
                .toString() + "\n"
    }
}
