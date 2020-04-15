package com.bindstone.kuljetus.domain;

import com.bindstone.kuljetus.domain.enumeration.Categorie;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@Document
public class Transport {

    @Id
    private String numeroPVR;
    private String codeOperation;
    private Categorie categorieStatec;
    private String codeCarrosserieEuropeen;
    private String libelleCarrosserie;
    private String codeCategorieEuropeenne;
    private String couleur;
    private String indicationUtilisation;
    private String codeDuPaysDeProvenance;
    private String codeMarque;
    private String libelleMarque;
    private String typeUsine;
    private String designationCommerciale;
    private String variantePVR;
    private String versionPVR;
    private String datePremiereMiseEncirculation;
    private String datePremiereMiseEnCirculationLuxembourg;
    private String dateMiseEnCirculationParProprietaire;
    private String dateMiseHorsCirculation;
    private String masseVide;
    private String masseMaximaleAutorisee;
    private String mmaEnsemble;
    private String mmaPointAttelage;
    private String masseRemorquableSansFreinage;
    private String masseRemorquableAvecFreinage;
    private String indicateur4x4;
    private String indicateurABS;
    private String indicateurASR;
    private String nombrePlacesAvant;
    private String nombrePlacesArriere;
    private String nombreSpecifiqueAvant;
    private String nombreSpecifiqueArriere;
    private String placesDebout;
    private String placesAssises;
    private String longueur;
    private String largeur;
    private String hauteur;
    private String nombreEssieuxSimples;
    private String nombreEssieuxTandem;
    private String nombreEssieuxTridem;
    private String empattementMaximal;
    private String largeurVoieEssieu1;
    private String largeurVoieEssieu2;
    private String typeMoteur;
    private String codeCarburant;
    private String libelleCarburant;
    private String nombreCylindres;
    private String puissance;
    private String cylindree;
    private String infoUtilisateur;
    private String emissionsCO2_g_km;
    private String consommation_l_100km;
    private String emissionParticules_g_km;
    private String emissionsNox_g_km;
    private String Euronorme;
    private String masseWLTP;
    private String emissionsCO2_g_km_WLTP;
    private String emissionsCO2_EcoInno_g_km_WLTP;
    private String consommationEnergieElectrique;
    private String autonomieModeElectrique;

    public String getNumeroPVR() {
        return numeroPVR;
    }

    public void setNumeroPVR(String numeroPVR) {
        this.numeroPVR = numeroPVR;
    }

    public String getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(String codeOperation) {
        this.codeOperation = codeOperation;
    }

    public Categorie getCategorieStatec() {
        return categorieStatec;
    }

    public void setCategorieStatec(Categorie categorieStatec) {
        this.categorieStatec = categorieStatec;
    }

    public String getCodeCarrosserieEuropeen() {
        return codeCarrosserieEuropeen;
    }

    public void setCodeCarrosserieEuropeen(String codeCarrosserieEuropeen) {
        this.codeCarrosserieEuropeen = codeCarrosserieEuropeen;
    }

    public String getLibelleCarrosserie() {
        return libelleCarrosserie;
    }

    public void setLibelleCarrosserie(String libelleCarrosserie) {
        this.libelleCarrosserie = libelleCarrosserie;
    }

    public String getCodeCategorieEuropeenne() {
        return codeCategorieEuropeenne;
    }

    public void setCodeCategorieEuropeenne(String codeCategorieEuropeenne) {
        this.codeCategorieEuropeenne = codeCategorieEuropeenne;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getIndicationUtilisation() {
        return indicationUtilisation;
    }

    public void setIndicationUtilisation(String indicationUtilisation) {
        this.indicationUtilisation = indicationUtilisation;
    }

    public String getCodeDuPaysDeProvenance() {
        return codeDuPaysDeProvenance;
    }

    public void setCodeDuPaysDeProvenance(String codeDuPaysDeProvenance) {
        this.codeDuPaysDeProvenance = codeDuPaysDeProvenance;
    }

    public String getCodeMarque() {
        return codeMarque;
    }

    public void setCodeMarque(String codeMarque) {
        this.codeMarque = codeMarque;
    }

    public String getLibelleMarque() {
        return libelleMarque;
    }

    public void setLibelleMarque(String libelleMarque) {
        this.libelleMarque = libelleMarque;
    }

    public String getTypeUsine() {
        return typeUsine;
    }

    public void setTypeUsine(String typeUsine) {
        this.typeUsine = typeUsine;
    }

    public String getDesignationCommerciale() {
        return designationCommerciale;
    }

    public void setDesignationCommerciale(String designationCommerciale) {
        this.designationCommerciale = designationCommerciale;
    }

    public String getVariantePVR() {
        return variantePVR;
    }

    public void setVariantePVR(String variantePVR) {
        this.variantePVR = variantePVR;
    }

    public String getVersionPVR() {
        return versionPVR;
    }

    public void setVersionPVR(String versionPVR) {
        this.versionPVR = versionPVR;
    }

    public String getDatePremiereMiseEncirculation() {
        return datePremiereMiseEncirculation;
    }

    public void setDatePremiereMiseEncirculation(String datePremiereMiseEncirculation) {
        this.datePremiereMiseEncirculation = datePremiereMiseEncirculation;
    }

    public String getDatePremiereMiseEnCirculationLuxembourg() {
        return datePremiereMiseEnCirculationLuxembourg;
    }

    public void setDatePremiereMiseEnCirculationLuxembourg(String datePremiereMiseEnCirculationLuxembourg) {
        this.datePremiereMiseEnCirculationLuxembourg = datePremiereMiseEnCirculationLuxembourg;
    }

    public String getDateMiseEnCirculationParProprietaire() {
        return dateMiseEnCirculationParProprietaire;
    }

    public void setDateMiseEnCirculationParProprietaire(String dateMiseEnCirculationParProprietaire) {
        this.dateMiseEnCirculationParProprietaire = dateMiseEnCirculationParProprietaire;
    }

    public String getDateMiseHorsCirculation() {
        return dateMiseHorsCirculation;
    }

    public void setDateMiseHorsCirculation(String dateMiseHorsCirculation) {
        this.dateMiseHorsCirculation = dateMiseHorsCirculation;
    }

    public String getMasseVide() {
        return masseVide;
    }

    public void setMasseVide(String masseVide) {
        this.masseVide = masseVide;
    }

    public String getMasseMaximaleAutorisee() {
        return masseMaximaleAutorisee;
    }

    public void setMasseMaximaleAutorisee(String masseMaximaleAutorisee) {
        this.masseMaximaleAutorisee = masseMaximaleAutorisee;
    }

    public String getMmaEnsemble() {
        return mmaEnsemble;
    }

    public void setMmaEnsemble(String mmaEnsemble) {
        this.mmaEnsemble = mmaEnsemble;
    }

    public String getMmaPointAttelage() {
        return mmaPointAttelage;
    }

    public void setMmaPointAttelage(String mmaPointAttelage) {
        this.mmaPointAttelage = mmaPointAttelage;
    }

    public String getMasseRemorquableSansFreinage() {
        return masseRemorquableSansFreinage;
    }

    public void setMasseRemorquableSansFreinage(String masseRemorquableSansFreinage) {
        this.masseRemorquableSansFreinage = masseRemorquableSansFreinage;
    }

    public String getMasseRemorquableAvecFreinage() {
        return masseRemorquableAvecFreinage;
    }

    public void setMasseRemorquableAvecFreinage(String masseRemorquableAvecFreinage) {
        this.masseRemorquableAvecFreinage = masseRemorquableAvecFreinage;
    }

    public String getIndicateur4x4() {
        return indicateur4x4;
    }

    public void setIndicateur4x4(String indicateur4x4) {
        this.indicateur4x4 = indicateur4x4;
    }

    public String getIndicateurABS() {
        return indicateurABS;
    }

    public void setIndicateurABS(String indicateurABS) {
        this.indicateurABS = indicateurABS;
    }

    public String getIndicateurASR() {
        return indicateurASR;
    }

    public void setIndicateurASR(String indicateurASR) {
        this.indicateurASR = indicateurASR;
    }

    public String getNombrePlacesAvant() {
        return nombrePlacesAvant;
    }

    public void setNombrePlacesAvant(String nombrePlacesAvant) {
        this.nombrePlacesAvant = nombrePlacesAvant;
    }

    public String getNombrePlacesArriere() {
        return nombrePlacesArriere;
    }

    public void setNombrePlacesArriere(String nombrePlacesArriere) {
        this.nombrePlacesArriere = nombrePlacesArriere;
    }

    public String getNombreSpecifiqueAvant() {
        return nombreSpecifiqueAvant;
    }

    public void setNombreSpecifiqueAvant(String nombreSpecifiqueAvant) {
        this.nombreSpecifiqueAvant = nombreSpecifiqueAvant;
    }

    public String getNombreSpecifiqueArriere() {
        return nombreSpecifiqueArriere;
    }

    public void setNombreSpecifiqueArriere(String nombreSpecifiqueArriere) {
        this.nombreSpecifiqueArriere = nombreSpecifiqueArriere;
    }

    public String getPlacesDebout() {
        return placesDebout;
    }

    public void setPlacesDebout(String placesDebout) {
        this.placesDebout = placesDebout;
    }

    public String getPlacesAssises() {
        return placesAssises;
    }

    public void setPlacesAssises(String placesAssises) {
        this.placesAssises = placesAssises;
    }

    public String getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getNombreEssieuxSimples() {
        return nombreEssieuxSimples;
    }

    public void setNombreEssieuxSimples(String nombreEssieuxSimples) {
        this.nombreEssieuxSimples = nombreEssieuxSimples;
    }

    public String getNombreEssieuxTandem() {
        return nombreEssieuxTandem;
    }

    public void setNombreEssieuxTandem(String nombreEssieuxTandem) {
        this.nombreEssieuxTandem = nombreEssieuxTandem;
    }

    public String getNombreEssieuxTridem() {
        return nombreEssieuxTridem;
    }

    public void setNombreEssieuxTridem(String nombreEssieuxTridem) {
        this.nombreEssieuxTridem = nombreEssieuxTridem;
    }

    public String getEmpattementMaximal() {
        return empattementMaximal;
    }

    public void setEmpattementMaximal(String empattementMaximal) {
        this.empattementMaximal = empattementMaximal;
    }

    public String getLargeurVoieEssieu1() {
        return largeurVoieEssieu1;
    }

    public void setLargeurVoieEssieu1(String largeurVoieEssieu1) {
        this.largeurVoieEssieu1 = largeurVoieEssieu1;
    }

    public String getLargeurVoieEssieu2() {
        return largeurVoieEssieu2;
    }

    public void setLargeurVoieEssieu2(String largeurVoieEssieu2) {
        this.largeurVoieEssieu2 = largeurVoieEssieu2;
    }

    public String getTypeMoteur() {
        return typeMoteur;
    }

    public void setTypeMoteur(String typeMoteur) {
        this.typeMoteur = typeMoteur;
    }

    public String getCodeCarburant() {
        return codeCarburant;
    }

    public void setCodeCarburant(String codeCarburant) {
        this.codeCarburant = codeCarburant;
    }

    public String getLibelleCarburant() {
        return libelleCarburant;
    }

    public void setLibelleCarburant(String libelleCarburant) {
        this.libelleCarburant = libelleCarburant;
    }

    public String getNombreCylindres() {
        return nombreCylindres;
    }

    public void setNombreCylindres(String nombreCylindres) {
        this.nombreCylindres = nombreCylindres;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getCylindree() {
        return cylindree;
    }

    public void setCylindree(String cylindree) {
        this.cylindree = cylindree;
    }

    public String getInfoUtilisateur() {
        return infoUtilisateur;
    }

    public void setInfoUtilisateur(String infoUtilisateur) {
        this.infoUtilisateur = infoUtilisateur;
    }

    public String getEmissionsCO2_g_km() {
        return emissionsCO2_g_km;
    }

    public void setEmissionsCO2_g_km(String emissionsCO2_g_km) {
        this.emissionsCO2_g_km = emissionsCO2_g_km;
    }

    public String getConsommation_l_100km() {
        return consommation_l_100km;
    }

    public void setConsommation_l_100km(String consommation_l_100km) {
        this.consommation_l_100km = consommation_l_100km;
    }

    public String getEmissionParticules_g_km() {
        return emissionParticules_g_km;
    }

    public void setEmissionParticules_g_km(String emissionParticules_g_km) {
        this.emissionParticules_g_km = emissionParticules_g_km;
    }

    public String getEmissionsNox_g_km() {
        return emissionsNox_g_km;
    }

    public void setEmissionsNox_g_km(String emissionsNox_g_km) {
        this.emissionsNox_g_km = emissionsNox_g_km;
    }

    public String getEuronorme() {
        return Euronorme;
    }

    public void setEuronorme(String euronorme) {
        Euronorme = euronorme;
    }

    public String getMasseWLTP() {
        return masseWLTP;
    }

    public void setMasseWLTP(String masseWLTP) {
        this.masseWLTP = masseWLTP;
    }

    public String getEmissionsCO2_g_km_WLTP() {
        return emissionsCO2_g_km_WLTP;
    }

    public void setEmissionsCO2_g_km_WLTP(String emissionsCO2_g_km_WLTP) {
        this.emissionsCO2_g_km_WLTP = emissionsCO2_g_km_WLTP;
    }

    public String getEmissionsCO2_EcoInno_g_km_WLTP() {
        return emissionsCO2_EcoInno_g_km_WLTP;
    }

    public void setEmissionsCO2_EcoInno_g_km_WLTP(String emissionsCO2_EcoInno_g_km_WLTP) {
        this.emissionsCO2_EcoInno_g_km_WLTP = emissionsCO2_EcoInno_g_km_WLTP;
    }

    public String getConsommationEnergieElectrique() {
        return consommationEnergieElectrique;
    }

    public void setConsommationEnergieElectrique(String consommationEnergieElectrique) {
        this.consommationEnergieElectrique = consommationEnergieElectrique;
    }

    public String getAutonomieModeElectrique() {
        return autonomieModeElectrique;
    }

    public void setAutonomieModeElectrique(String autonomieModeElectrique) {
        this.autonomieModeElectrique = autonomieModeElectrique;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "[", "]")
                .add("codeOperation='" + codeOperation + "'")
                .add("codeCategorieStatec='" + categorieStatec.getCode() + "'")
                .add("codeCarrosserieEuropeen='" + codeCarrosserieEuropeen + "'")
                .add("libelleCarrosserie='" + libelleCarrosserie + "'")
                .add("codeCategorieEuropeenne='" + codeCategorieEuropeenne + "'")
                .add("couleur='" + couleur + "'")
                .add("indicationUtilisation='" + indicationUtilisation + "'")
                .add("codeDuPaysDeProvenance='" + codeDuPaysDeProvenance + "'")
                .add("codeMarque='" + codeMarque + "'")
                .add("libelleMarque='" + libelleMarque + "'")
                .add("typeUsine='" + typeUsine + "'")
                .add("designationCommerciale='" + designationCommerciale + "'")
                .add("numeroPVR='" + numeroPVR + "'")
                .add("variantePVR='" + variantePVR + "'")
                .add("versionPVR='" + versionPVR + "'")
                .add("datePremiereMiseEncirculation='" + datePremiereMiseEncirculation + "'")
                .add("datePremiereMiseEnCirculationLuxembourg='" + datePremiereMiseEnCirculationLuxembourg + "'")
                .add("dateMiseEnCirculationParProprietaire='" + dateMiseEnCirculationParProprietaire + "'")
                .add("dateMiseHorsCirculation='" + dateMiseHorsCirculation + "'")
                .add("masseVide='" + masseVide + "'")
                .add("masseMaximaleAutorisee='" + masseMaximaleAutorisee + "'")
                .add("mmaEnsemble='" + mmaEnsemble + "'")
                .add("mmaPointAttelage='" + mmaPointAttelage + "'")
                .add("masseRemorquableSansFreinage='" + masseRemorquableSansFreinage + "'")
                .add("masseRemorquableAvecFreinage='" + masseRemorquableAvecFreinage + "'")
                .add("indicateur4x4='" + indicateur4x4 + "'")
                .add("indicateurABS='" + indicateurABS + "'")
                .add("indicateurASR='" + indicateurASR + "'")
                .add("nombrePlacesAvant='" + nombrePlacesAvant + "'")
                .add("nombrePlacesArriere='" + nombrePlacesArriere + "'")
                .add("nombreSpecifiqueAvant='" + nombreSpecifiqueAvant + "'")
                .add("nombreSpecifiqueArriere='" + nombreSpecifiqueArriere + "'")
                .add("placesDebout='" + placesDebout + "'")
                .add("placesAssises='" + placesAssises + "'")
                .add("longueur='" + longueur + "'")
                .add("largeur='" + largeur + "'")
                .add("hauteur='" + hauteur + "'")
                .add("nombreEssieuxSimples='" + nombreEssieuxSimples + "'")
                .add("nombreEssieuxTandem='" + nombreEssieuxTandem + "'")
                .add("nombreEssieuxTridem='" + nombreEssieuxTridem + "'")
                .add("empattementMaximal='" + empattementMaximal + "'")
                .add("largeurVoieEssieu1='" + largeurVoieEssieu1 + "'")
                .add("largeurVoieEssieu2='" + largeurVoieEssieu2 + "'")
                .add("typeMoteur='" + typeMoteur + "'")
                .add("codeCarburant='" + codeCarburant + "'")
                .add("libelleCarburant='" + libelleCarburant + "'")
                .add("nombreCylindres='" + nombreCylindres + "'")
                .add("puissance='" + puissance + "'")
                .add("cylindree='" + cylindree + "'")
                .add("infoUtilisateur='" + infoUtilisateur + "'")
                .add("emissionsCO2_g_km='" + emissionsCO2_g_km + "'")
                .add("consommation_l_100km='" + consommation_l_100km + "'")
                .add("emissionParticules_g_km='" + emissionParticules_g_km + "'")
                .add("emissionsNox_g_km='" + emissionsNox_g_km + "'")
                .add("Euronorme='" + Euronorme + "'")
                .add("masseWLTP='" + masseWLTP + "'")
                .add("emissionsCO2_g_km_WLTP='" + emissionsCO2_g_km_WLTP + "'")
                .add("emissionsCO2_EcoInno_g_km_WLTP='" + emissionsCO2_EcoInno_g_km_WLTP + "'")
                .add("consommationEnergieElectrique='" + consommationEnergieElectrique + "'")
                .add("autonomieModeElectrique='" + autonomieModeElectrique + "'")
                .toString().concat("\n");
    }
}
