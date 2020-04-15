package com.bindstone.kuljetus.domain

import com.bindstone.kuljetus.domain.enumeration.Categorie
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class Transport {
    @Id
    var numeroPVR: String? = null
    var codeOperation: String? = null
    var categorieStatec: Categorie? = null
    var codeCarrosserieEuropeen: String? = null
    var libelleCarrosserie: String? = null
    var codeCategorieEuropeenne: String? = null
    var couleur: String? = null
    var indicationUtilisation: String? = null
    var codeDuPaysDeProvenance: String? = null
    var codeMarque: String? = null
    var libelleMarque: String? = null
    var typeUsine: String? = null
    var designationCommerciale: String? = null
    var variantePVR: String? = null
    var versionPVR: String? = null
    var datePremiereMiseEncirculation: String? = null
    var datePremiereMiseEnCirculationLuxembourg: String? = null
    var dateMiseEnCirculationParProprietaire: String? = null
    var dateMiseHorsCirculation: String? = null
    var masseVide: String? = null
    var masseMaximaleAutorisee: String? = null
    var mmaEnsemble: String? = null
    var mmaPointAttelage: String? = null
    var masseRemorquableSansFreinage: String? = null
    var masseRemorquableAvecFreinage: String? = null
    var indicateur4x4: String? = null
    var indicateurABS: String? = null
    var indicateurASR: String? = null
    var nombrePlacesAvant: String? = null
    var nombrePlacesArriere: String? = null
    var nombreSpecifiqueAvant: String? = null
    var nombreSpecifiqueArriere: String? = null
    var placesDebout: String? = null
    var placesAssises: String? = null
    var longueur: String? = null
    var largeur: String? = null
    var hauteur: String? = null
    var nombreEssieuxSimples: String? = null
    var nombreEssieuxTandem: String? = null
    var nombreEssieuxTridem: String? = null
    var empattementMaximal: String? = null
    var largeurVoieEssieu1: String? = null
    var largeurVoieEssieu2: String? = null
    var typeMoteur: String? = null
    var codeCarburant: String? = null
    var libelleCarburant: String? = null
    var nombreCylindres: String? = null
    var puissance: String? = null
    var cylindree: String? = null
    var infoUtilisateur: String? = null
    var emissionsCO2_g_km: String? = null
    var consommation_l_100km: String? = null
    var emissionParticules_g_km: String? = null
    var emissionsNox_g_km: String? = null
    var euronorme: String? = null
    var masseWLTP: String? = null
    var emissionsCO2_g_km_WLTP: String? = null
    var emissionsCO2_EcoInno_g_km_WLTP: String? = null
    var consommationEnergieElectrique: String? = null
    var autonomieModeElectrique: String? = null

    override fun toString(): String {
        return StringJoiner(",", "[", "]")
                .add("codeOperation='$codeOperation'")
                .add("codeCategorieStatec='" + categorieStatec!!.code + "'")
                .add("codeCarrosserieEuropeen='$codeCarrosserieEuropeen'")
                .add("libelleCarrosserie='$libelleCarrosserie'")
                .add("codeCategorieEuropeenne='$codeCategorieEuropeenne'")
                .add("couleur='$couleur'")
                .add("indicationUtilisation='$indicationUtilisation'")
                .add("codeDuPaysDeProvenance='$codeDuPaysDeProvenance'")
                .add("codeMarque='$codeMarque'")
                .add("libelleMarque='$libelleMarque'")
                .add("typeUsine='$typeUsine'")
                .add("designationCommerciale='$designationCommerciale'")
                .add("numeroPVR='$numeroPVR'")
                .add("variantePVR='$variantePVR'")
                .add("versionPVR='$versionPVR'")
                .add("datePremiereMiseEncirculation='$datePremiereMiseEncirculation'")
                .add("datePremiereMiseEnCirculationLuxembourg='$datePremiereMiseEnCirculationLuxembourg'")
                .add("dateMiseEnCirculationParProprietaire='$dateMiseEnCirculationParProprietaire'")
                .add("dateMiseHorsCirculation='$dateMiseHorsCirculation'")
                .add("masseVide='$masseVide'")
                .add("masseMaximaleAutorisee='$masseMaximaleAutorisee'")
                .add("mmaEnsemble='$mmaEnsemble'")
                .add("mmaPointAttelage='$mmaPointAttelage'")
                .add("masseRemorquableSansFreinage='$masseRemorquableSansFreinage'")
                .add("masseRemorquableAvecFreinage='$masseRemorquableAvecFreinage'")
                .add("indicateur4x4='$indicateur4x4'")
                .add("indicateurABS='$indicateurABS'")
                .add("indicateurASR='$indicateurASR'")
                .add("nombrePlacesAvant='$nombrePlacesAvant'")
                .add("nombrePlacesArriere='$nombrePlacesArriere'")
                .add("nombreSpecifiqueAvant='$nombreSpecifiqueAvant'")
                .add("nombreSpecifiqueArriere='$nombreSpecifiqueArriere'")
                .add("placesDebout='$placesDebout'")
                .add("placesAssises='$placesAssises'")
                .add("longueur='$longueur'")
                .add("largeur='$largeur'")
                .add("hauteur='$hauteur'")
                .add("nombreEssieuxSimples='$nombreEssieuxSimples'")
                .add("nombreEssieuxTandem='$nombreEssieuxTandem'")
                .add("nombreEssieuxTridem='$nombreEssieuxTridem'")
                .add("empattementMaximal='$empattementMaximal'")
                .add("largeurVoieEssieu1='$largeurVoieEssieu1'")
                .add("largeurVoieEssieu2='$largeurVoieEssieu2'")
                .add("typeMoteur='$typeMoteur'")
                .add("codeCarburant='$codeCarburant'")
                .add("libelleCarburant='$libelleCarburant'")
                .add("nombreCylindres='$nombreCylindres'")
                .add("puissance='$puissance'")
                .add("cylindree='$cylindree'")
                .add("infoUtilisateur='$infoUtilisateur'")
                .add("emissionsCO2_g_km='$emissionsCO2_g_km'")
                .add("consommation_l_100km='$consommation_l_100km'")
                .add("emissionParticules_g_km='$emissionParticules_g_km'")
                .add("emissionsNox_g_km='$emissionsNox_g_km'")
                .add("Euronorme='" + euronorme + "'")
                .add("masseWLTP='$masseWLTP'")
                .add("emissionsCO2_g_km_WLTP='$emissionsCO2_g_km_WLTP'")
                .add("emissionsCO2_EcoInno_g_km_WLTP='$emissionsCO2_EcoInno_g_km_WLTP'")
                .add("consommationEnergieElectrique='$consommationEnergieElectrique'")
                .add("autonomieModeElectrique='$autonomieModeElectrique'")
                .toString() + "\n"
    }
}
