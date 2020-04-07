package com.bindstone.kuljetus.service;

import com.bindstone.kuljetus.domain.Transport;
import com.bindstone.kuljetus.repository.TransportRepository;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {

    private final TransportRepository transportRepository;

    public ImportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public void importData(File file) throws FileNotFoundException, XMLStreamException {
        System.out.println("Start import data:");
        Instant start = Instant.now();
        importDataExec(file);
        Instant end = Instant.now();

        Duration interval = Duration.between(start, end);
        System.out.println("Execution time in seconds: " + interval.getSeconds());
    }

    private void importDataExec(File file) throws FileNotFoundException, XMLStreamException {

        FileInputStream fi = new FileInputStream(file);

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(fi);

        reader.nextEvent(); // Skip Title
        reader.nextEvent(); // Skip Header

        List list = new ArrayList<Transport>();
        String key = null;
        String data = null;
        Transport transport = new Transport();

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {
                StartElement elem = nextEvent.asStartElement();
                if (!"VEHICLE".equals(elem.getName().toString())) {
                    key = elem.getName().toString();
                }
            }

            if (nextEvent.isCharacters()) {
                data = nextEvent.asCharacters().getData();
            }

            if (nextEvent.isEndElement()) {
                EndElement elem = nextEvent.asEndElement();
                if ("VEHICLE".equals(elem.getName().toString())) {

                    list.add(transport);
                    transport = new Transport();

                    if (list.size() > 1000) {
                        transportRepository.saveAll(list).blockLast();
                        list.clear();
                        System.out.print(".");
                    }
                } else {
                    if (key != null && data != null) {
                        switch (key) {
                            case "PVRNUM":
                                transport.setNumeroPVR(data);
                                break;
                            case "OPE":
                                transport.setCodeOperation(data);
                                break;
                            case "CATSTC":
                                transport.setCodeCategorieStatec(data);
                                break;
                            case "CODCAR":
                                transport.setCodeCarrosserieEuropeen(data);
                                break;
                            case "LIBCAR":
                                transport.setLibelleCarrosserie(data);
                                break;
                            case "CATEU":
                                transport.setCodeCategorieEuropeenne(data);
                                break;
                            case "COUL":
                                transport.setCouleur(data);
                                break;
                            case "INDUTI":
                                transport.setIndicationUtilisation(data);
                                break;
                            case "PAYPVN":
                                transport.setCodeDuPaysDeProvenance(data);
                                break;
                            case "CODMRQ":
                                transport.setCodeMarque(data);
                                break;
                            case "LIBMRQ":
                                transport.setLibelleMarque(data);
                                break;
                            case "TYPUSI":
                                transport.setTypeUsine(data);
                                break;
                            case "TYPCOM":
                                transport.setDesignationCommerciale(data);
                                break;
                            case "PVRVAR":
                                transport.setVariantePVR(data);
                                break;
                            case "PVRVER":
                                transport.setVersionPVR(data);
                                break;
                            case "DATCIRPRM":
                                transport.setDatePremiereMiseEncirculation(data);
                                break;
                            case "DATCIR_GD":
                                transport.setDatePremiereMiseEnCirculationLuxembourg(data);
                                break;
                            case "DATCIR":
                                transport.setdateMiseEnCirculationParProprietaire(data);
                                break;
                            case "DATHORCIR":
                                transport.setDateMiseHorsCirculation(data);
                                break;
                            case "MVID":
                                transport.setMasseVide(data);
                                break;
                            case "MMA":
                                transport.setMasseMaximaleAutorisee(data);
                                break;
                            case "MMAENS":
                                transport.setMmaEnsemble(data);
                                break;
                            case "MMAATT":
                                transport.setMmaPointAttelage(data);
                                break;
                            case "MMARSF":
                                transport.setMasseRemorquableSansFreinage(data);
                                break;
                            case "MMARAF":
                                transport.setMasseRemorquableAvecFreinage(data);
                                break;
                            case "I4X4":
                                transport.setIndicateur4x4(data);
                                break;
                            case "ABS":
                                transport.setIndicateurABS(data);
                                break;
                            case "ASR":
                                transport.setIndicateurASR(data);
                                break;
                            case "PLAAVA":
                                transport.setNombrePlacesAvant(data);
                                break;
                            case "PLAARR":
                                transport.setNombrePlacesArriere(data);
                                break;
                            case "PLASAV":
                                transport.setNombreSpecifiqueAvant(data);
                                break;
                            case "PLASAR":
                                transport.setNombreSpecifiqueArriere(data);
                                break;
                            case "PLADEB":
                                transport.setPlacesDebout(data);
                                break;
                            case "PLAASS":
                                transport.setPlacesAssises(data);
                                break;
                            case "LON":
                                transport.setLongueur(data);
                                break;
                            case "LAR":
                                transport.setLargeur(data);
                                break;
                            case "HAU":
                                transport.setHauteur(data);
                                break;
                            case "ESSIM":
                                transport.setNombreEssieuxSimples(data);
                                break;
                            case "ESTAN":
                                transport.setNombreEssieuxTandem(data);
                                break;
                            case "ESTRI":
                                transport.setNombreEssieuxTridem(data);
                                break;
                            case "EMPMAX":
                                transport.setEmpattementMaximal(data);
                                break;
                            case "LARES1":
                                transport.setLargeurVoieEssieu1(data);
                                break;
                            case "LARES2":
                                transport.setLargeurVoieEssieu2(data);
                                break;
                            case "TYPMOT":
                                transport.setTypeMoteur(data);
                                break;
                            case "CODCRB":
                                transport.setCodeCarburant(data);
                                break;
                            case "LIBCRB":
                                transport.setLibelleCarburant(data);
                                break;
                            case "NBRCYL":
                                transport.setNombreCylindres(data);
                                break;
                            case "PKW":
                                transport.setPuissance(data);
                                break;
                            case "CYD":
                                transport.setCylindree(data);
                                break;
                            case "INFOUTI":
                                transport.setInfoUtilisateur(data);
                                break;
                            case "INFCO2":
                                transport.setEmissionsCO2_g_km(data);
                                break;
                            case "L100KM":
                                transport.setConsommation_l_100km(data);
                                break;
                            case "INFPARTICULE":
                                transport.setEmissionParticules_g_km(data);
                                break;
                            case "INFNOX":
                                transport.setEmissionsNox_g_km(data);
                                break;
                            case "EUNORM":
                                transport.setEuronorme(data);
                                break;
                            case "mWLTP":
                                transport.setMasseWLTP(data);
                                break;
                            case "CO2WLTP":
                                transport.setEmissionsCO2_g_km_WLTP(data);
                                break;
                            case "eWLTP":
                                transport.setEmissionsCO2_EcoInno_g_km_WLTP(data);
                                break;
                            case "CONSELEC":
                                transport.setConsommationEnergieElectrique(data);
                                break;
                            case "AUTOELEC":
                                transport.setAutonomieModeElectrique(data);
                                break;
                        }
                    }
                    key = null;
                    data = null;
                }
            }
        }
        transportRepository.saveAll(list).blockLast();
    }
}
