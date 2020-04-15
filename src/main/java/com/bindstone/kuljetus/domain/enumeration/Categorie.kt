package com.bindstone.kuljetus.domain.enumeration;

public enum Categorie {
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

    private String code;
    private String label;

    Categorie(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Categorie byCode(String code) {
        if (code == null) {
            return null;
        }

        switch (code) {
            case "0":
            case "00":
                return SANS_CODE;
            case "1":
            case "01":
                return CYCLOMOTEUR;
            case "2":
            case "02":
                return MOTOCYCLE;
            case "5":
            case "05":
                return VOITURE_PARTICULIERE;
            case "6":
            case "06":
                return VOITURE_USAGE_MIXTE;
            case "7":
            case "07":
                return VEHICULE_UTILITAIRE;
            case "9":
            case "09":
                return AUTOBUS_AUTOCAR;
            case "11":
                return CAMIONNETTE;
            case "12":
                return CAMION;
            case "21":
                return TRACTEUR_ROUTIER;
            case "29":
                return VEHICULE_SPECIAL;
            case "31":
                return TRACTEUR_AGRICOLE;
            case "32":
                return MACHINE_AGRICOLE;
            case "39":
                return AUTO_VEHICULE_AUTOMOTEUR;
            case "41":
                return REMORQUE_MARCHANDISE;
            case "42":
                return SEMI_REMORQUE;
            case "51":
                return TRICYCLE;
            case "52":
                return QUADRICYCLE;
            case "53":
                return QUADRICYCLE_LEGER;
            case "59":
                return AUTRE_REMORQUE;
            default:
                throw new RuntimeException("Code [" + code + "] does not exists");
        }
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

}
