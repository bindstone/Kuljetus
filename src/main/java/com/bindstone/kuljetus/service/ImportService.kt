package com.bindstone.kuljetus.service

import com.bindstone.kuljetus.domain.Transport
import com.bindstone.kuljetus.domain.enumeration.Categorie.Companion.byCode
import com.bindstone.kuljetus.repository.primary.TransportPrimaryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamException

@Service
class ImportService(private val transportPrimaryRepository: TransportPrimaryRepository) {
    var logger = LoggerFactory.getLogger(ImportService::class.java)

    @Throws(FileNotFoundException::class, XMLStreamException::class)
    fun importData(file: File) {
        logger.info("Start import data:")
        val start = Instant.now()
        importDataExec(file)
        val end = Instant.now()
        val interval = Duration.between(start, end)
        logger.info("Execution time in seconds: " + interval.seconds)
    }

    @Throws(FileNotFoundException::class, XMLStreamException::class)
    private fun importDataExec(file: File) {
        val xmlInputFactory = XMLInputFactory.newInstance()
        val fi = FileInputStream(file)
        val reader = xmlInputFactory.createXMLEventReader(fi)
        reader.nextEvent() // Skip Title
        reader.nextEvent() // Skip Header
        val list: MutableList<Transport> = ArrayList<Transport>()
        var key: String? = null
        var data: String? = null
        var transport = Transport()
        while (reader.hasNext()) {
            val nextEvent = reader.nextEvent()
            if (nextEvent.isStartElement) {
                val elem = nextEvent.asStartElement()
                if ("VEHICLE" != elem.name.toString()) {
                    key = elem.name.toString()
                }
            }
            if (nextEvent.isCharacters) {
                data = nextEvent.asCharacters().data
            }
            if (nextEvent.isEndElement) {
                val elem = nextEvent.asEndElement()
                if ("VEHICLE" == elem.name.toString()) {
                    list.add(transport)
                    transport = Transport()
                    if (list.size > 1000) {
                        transportPrimaryRepository.saveAll<Transport>(list).blockLast()
                        list.clear()
                        logger.info(".")
                    }
                } else {
                    if (key != null && data != null) {
                        when (key) {
                            "PVRNUM" -> transport.numeroPVR = data
                            "OPE" -> transport.codeOperation = data
                            "CATSTC" -> transport.categorieStatec = byCode(data)
                            "CODCAR" -> transport.codeCarrosserieEuropeen = data
                            "LIBCAR" -> transport.libelleCarrosserie = data
                            "CATEU" -> transport.codeCategorieEuropeenne = data
                            "COUL" -> transport.couleur = data
                            "INDUTI" -> transport.indicationUtilisation = data
                            "PAYPVN" -> transport.codeDuPaysDeProvenance = data
                            "CODMRQ" -> transport.codeMarque = data
                            "LIBMRQ" -> transport.libelleMarque = data
                            "TYPUSI" -> transport.typeUsine = data
                            "TYPCOM" -> transport.designationCommerciale = data
                            "PVRVAR" -> transport.variantePVR = data
                            "PVRVER" -> transport.versionPVR = data
                            "DATCIRPRM" -> transport.datePremiereMiseEncirculation = data
                            "DATCIR_GD" -> transport.datePremiereMiseEnCirculationLuxembourg = data
                            "DATCIR" -> transport.dateMiseEnCirculationParProprietaire = data
                            "DATHORCIR" -> transport.dateMiseHorsCirculation = data
                            "MVID" -> transport.masseVide = data
                            "MMA" -> transport.masseMaximaleAutorisee = data
                            "MMAENS" -> transport.mmaEnsemble = data
                            "MMAATT" -> transport.mmaPointAttelage = data
                            "MMARSF" -> transport.masseRemorquableSansFreinage = data
                            "MMARAF" -> transport.masseRemorquableAvecFreinage = data
                            "I4X4" -> transport.indicateur4x4 = data
                            "ABS" -> transport.indicateurABS = data
                            "ASR" -> transport.indicateurASR = data
                            "PLAAVA" -> transport.nombrePlacesAvant = data
                            "PLAARR" -> transport.nombrePlacesArriere = data
                            "PLASAV" -> transport.nombreSpecifiqueAvant = data
                            "PLASAR" -> transport.nombreSpecifiqueArriere = data
                            "PLADEB" -> transport.placesDebout = data
                            "PLAASS" -> transport.placesAssises = data
                            "LON" -> transport.longueur = data
                            "LAR" -> transport.largeur = data
                            "HAU" -> transport.hauteur = data
                            "ESSIM" -> transport.nombreEssieuxSimples = data
                            "ESTAN" -> transport.nombreEssieuxTandem = data
                            "ESTRI" -> transport.nombreEssieuxTridem = data
                            "EMPMAX" -> transport.empattementMaximal = data
                            "LARES1" -> transport.largeurVoieEssieu1 = data
                            "LARES2" -> transport.largeurVoieEssieu2 = data
                            "TYPMOT" -> transport.typeMoteur = data
                            "CODCRB" -> transport.codeCarburant = data
                            "LIBCRB" -> transport.libelleCarburant = data
                            "NBRCYL" -> transport.nombreCylindres = data
                            "PKW" -> transport.puissance = data
                            "CYD" -> transport.cylindree = data
                            "INFOUTI" -> transport.infoUtilisateur = data
                            "INFCO2" -> transport.emissionsCO2_g_km = data
                            "L100KM" -> transport.consommation_l_100km = data
                            "INFPARTICULE" -> transport.emissionParticules_g_km = data
                            "INFNOX" -> transport.emissionsNox_g_km = data
                            "EUNORM" -> transport.euronorme = data
                            "mWLTP" -> transport.masseWLTP = data
                            "CO2WLTP" -> transport.emissionsCO2_g_km_WLTP = data
                            "eWLTP" -> transport.emissionsCO2_EcoInno_g_km_WLTP = data
                            "CONSELEC" -> transport.consommationEnergieElectrique = data
                            "AUTOELEC" -> transport.autonomieModeElectrique = data
                        }
                    }
                    key = null
                    data = null
                }
            }
        }
        transportPrimaryRepository.saveAll<Transport>(list).blockLast()
    }

}
