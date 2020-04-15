package com.bindstone.kuljetus.domain.enumeration

enum class Categorie(val code: String, val label: String) {
    SANS_CODE("00", "Sans code"),
    CYCLOMOTEUR("01", "Cyclomoteur"),
    MOTOCYCLE("02", "Motocycle"),
    VOITURE_PARTICULIERE("05", "Voiture particulière"),
    VOITURE_USAGE_MIXTE("06", "Voiture à usage mixte"),
    VEHICULE_UTILITAIRE("07", "Véhicule utilitaire"),
    AUTOBUS_AUTOCAR("09", "Autobus - Autocar"),
    CAMIONNETTE("11", "Camionnette"),
    CAMION("12", "Camion"),
    TRACTEUR_ROUTIER("21", "Tracteur routier"),
    VEHICULE_SPECIAL("29", "Véhicule spécial"),
    TRACTEUR_AGRICOLE("31", "Tracteur agricole"),
    MACHINE_AGRICOLE("32", "Machine agricole"),
    AUTO_VEHICULE_AUTOMOTEUR("39", "Auto véhicule automoteur"),
    REMORQUE_MARCHANDISE("41", "Remorque (marchandises)"),
    SEMI_REMORQUE("42", "Semi-remorque"),
    TRICYCLE("51", "Tricycle"),
    QUADRICYCLE("52", "Quadricycle"),
    QUADRICYCLE_LEGER("53", "Quadricycle léger"),
    AUTRE_REMORQUE("59", "Autre remorque");

    companion object {
        @kotlin.jvm.JvmStatic
        fun byCode(code: String): Categorie {
            return when (code) {
                "0", "00" -> SANS_CODE
                "1", "01" -> CYCLOMOTEUR
                "2", "02" -> MOTOCYCLE
                "5", "05" -> VOITURE_PARTICULIERE
                "6", "06" -> VOITURE_USAGE_MIXTE
                "7", "07" -> VEHICULE_UTILITAIRE
                "9", "09" -> AUTOBUS_AUTOCAR
                "11" -> CAMIONNETTE
                "12" -> CAMION
                "21" -> TRACTEUR_ROUTIER
                "29" -> VEHICULE_SPECIAL
                "31" -> TRACTEUR_AGRICOLE
                "32" -> MACHINE_AGRICOLE
                "39" -> AUTO_VEHICULE_AUTOMOTEUR
                "41" -> REMORQUE_MARCHANDISE
                "42" -> SEMI_REMORQUE
                "51" -> TRICYCLE
                "52" -> QUADRICYCLE
                "53" -> QUADRICYCLE_LEGER
                "59" -> AUTRE_REMORQUE
                else -> throw RuntimeException("Code [$code] does not exists")
            }
        }
    }
}
