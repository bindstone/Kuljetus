package com.bindstone.kuljetus.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class TransportList {
    @Id
    var numeroPVR: String? = null
    var libelleMarque: String? = null
    var designationCommerciale: String? = null
    var couleur: String? = null

    override fun toString(): String {
        return StringJoiner(",", "[", "]")
                .add("numeroPVR='$numeroPVR'")
                .add("couleur='$couleur'")
                .add("libelleMarque='$libelleMarque'")
                .add("designationCommerciale='$designationCommerciale'")
                .toString() + "\n"
    }
}
