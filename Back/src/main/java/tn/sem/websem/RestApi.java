package tn.sem.websem;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.vocabulary.RDF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import java.net.URLDecoder;

@RestController
public class RestApi {

    Model model = JenaEngine.readModel("data/ecodev.owl");
    @GetMapping("/getEventByFeedback")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherEvent() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_Event.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCenterByType")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherCenterByType() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_center.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAgenceByNameSrv")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherAgenceByNameSrv() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_DeliveryAgence.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPubByContentMsg")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherPubByContentMsg() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_publication.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProdByPerc")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherProdByPerc() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_recycledProd.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWasteByCategory")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> afficherWasteByCategory() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_waste.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

///***********//
@GetMapping("/getAllEvents")
@CrossOrigin(origins = "http://localhost:4200")
public ResponseEntity<List<EventDto>> getAllEvents() {
    List<EventDto> events = new ArrayList<>();

    if (model != null) {
        try {
            // Define the URI for Event type
            Resource eventType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event");

            // Define properties for event attributes
            Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
            Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
            Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
            Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
            Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
            Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
            Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

            // First, iterate over all NamedIndividuals
            StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
            while (namedIndividuals.hasNext()) {
                Statement statement = namedIndividuals.nextStatement();
                Resource eventResource = statement.getSubject();

                // Check if it's an event based on URI or rdf:type
                if (eventResource.getURI() != null && eventResource.getURI().contains("Event")) {
                    // Add the event to the list (both with and without rdf:type)
                    addEventToList(eventResource, titleProperty, descriptionProperty, dateProperty, locationProperty,
                            organizerProperty, maxParticipantsProperty, imageProperty, events);
                }
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving events: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } else {
        System.err.println("Model is null - Error when reading model from ontology");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Helper method to add event to the list
    private void addEventToList(Resource eventResource, Property titleProperty, Property descriptionProperty, Property dateProperty,
                                Property locationProperty, Property organizerProperty, Property maxParticipantsProperty,
                                Property imageProperty, List<EventDto> events) {
        String title = getLiteralValue(eventResource, titleProperty);
        String description = getLiteralValue(eventResource, descriptionProperty);
        String dateString = getLiteralValue(eventResource, dateProperty);
        String location = getLiteralValue(eventResource, locationProperty);
        String organizer = getLiteralValue(eventResource, organizerProperty);
        String maxParticipants = getLiteralValue(eventResource, maxParticipantsProperty);
        String image = getLiteralValue(eventResource, imageProperty);

        // Create an EventDto instance and set its properties
        EventDto eventDto = new EventDto();
        eventDto.setEvent(eventResource.getURI());
        eventDto.setTitle(title);
        eventDto.setDescription(description);

        // Parse the date safely to LocalDateTime
        if (dateString != null) {
            try {
                eventDto.setDate(LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + dateString);
                eventDto.setDate(null);  // Set to null if the date cannot be parsed
            }
        } else {
            eventDto.setDate(null);
        }

        eventDto.setLocation(location);
        eventDto.setOrganizer(organizer);

        // Parse maxParticipants safely
        try {
            eventDto.setMax_participants(maxParticipants != null ? Integer.parseInt(maxParticipants) : 0);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing maxParticipants: " + maxParticipants);
            eventDto.setMax_participants(0);
        }

        eventDto.setImage(image);

        events.add(eventDto);
    }

    // Helper method to retrieve a literal value of a property
    private String getLiteralValue(Resource resource, Property property) {
        Statement statement = resource.getProperty(property);
        return statement != null ? statement.getObject().toString() : null;
    }


    @PostMapping("/addEvent")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Générer une URI unique pour l'événement
                String eventUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event" + UUID.randomUUID().toString();

                // Créer la ressource Event avec l'URI générée
                Resource eventResource = model.createResource(eventUri);

                // Définir les propriétés pour l'entité Event
                Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
                Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
                Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
                Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Ajouter le type RDF pour la ressource Event
                model.add(eventResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event"));

                // Ajouter les propriétés à la ressource
                model.add(eventResource, titleProperty, eventDto.getTitle());
                model.add(eventResource, descriptionProperty, eventDto.getDescription());
                model.add(eventResource, dateProperty, eventDto.getDate().toString());
                model.add(eventResource, locationProperty, eventDto.getLocation());
                model.add(eventResource, organizerProperty, eventDto.getOrganizer());
                model.add(eventResource, maxParticipantsProperty, Integer.toString(eventDto.getMax_participants()));
                model.add(eventResource, imageProperty, eventDto.getImage());

                // Sauvegarder le modèle
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Retourner un message structuré en JSON
                ResponseMessage responseMessage = new ResponseMessage("Event added successfully", eventUri);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage("Error adding Event: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error when reading model from ontology", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/updateEvent")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Récupérer la ressource Event en utilisant l'URI
                Resource eventResource = model.getResource(eventDto.getEvent()); // URI unique de l'événement à mettre à jour

                if (eventResource != null && eventResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event"))) {
                    // Supprimer les anciennes valeurs
                    Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                    Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                    Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
                    Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
                    Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
                    Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
                    Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                    // Supprimer les anciennes propriétés (si présentes)
                    model.removeAll(eventResource, titleProperty, null);
                    model.removeAll(eventResource, descriptionProperty, null);
                    model.removeAll(eventResource, dateProperty, null);
                    model.removeAll(eventResource, locationProperty, null);
                    model.removeAll(eventResource, organizerProperty, null);
                    model.removeAll(eventResource, maxParticipantsProperty, null);
                    model.removeAll(eventResource, imageProperty, null);

                    // Ajouter les nouvelles valeurs
                    model.add(eventResource, titleProperty, eventDto.getTitle());
                    model.add(eventResource, descriptionProperty, eventDto.getDescription());
                    if (eventDto.getDate() != null) {
                        model.add(eventResource, dateProperty, eventDto.getDate().toString());
                    } else {
                        model.add(eventResource, dateProperty, "Date not provided");
                    }
                    model.add(eventResource, locationProperty, eventDto.getLocation());
                    model.add(eventResource, organizerProperty, eventDto.getOrganizer());
                    model.add(eventResource, maxParticipantsProperty, Integer.toString(eventDto.getMax_participants()));
                    model.add(eventResource, imageProperty, eventDto.getImage());

                    // Sauvegarder le modèle
                    JenaEngine.saveModel(model, "data/ecodev.owl");

                    return new ResponseEntity<>("Event updated successfully: " + eventDto.getEvent(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error updating Event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteEvent")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Retrieve the URI of the event to delete from DTO
                String eventUri = eventDto.getEvent();  // URI passed in the DTO

                // Check that the event exists in the model
                Resource eventResource = model.getResource(eventUri);

                if (eventResource != null) {
                    // If the event has rdf:type Event or is just a NamedIndividual, proceed with removal
                    if (eventResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event")) ||
                            eventResource.getURI() != null && eventResource.getURI().contains("Event")) {

                        // Remove all properties associated with the event
                        model.removeAll(eventResource, null, null);  // Removes all properties of this resource

                        // Save the model after deletion
                        JenaEngine.saveModel(model, "data/ecodev.owl");

                        return new ResponseEntity<>("Event deleted successfully: " + eventUri, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Event not found or invalid type", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error deleting Event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


/*
@GetMapping("/getAllEvents")
@CrossOrigin(origins = "http://localhost:4200")
public ResponseEntity<List<EventDto>> getAllEvents() {
    List<EventDto> events = new ArrayList<>();

    if (model != null) {
        try {
            // Créer la ressource Event (type RDF)
            Resource eventType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event");

            // Rechercher toutes les ressources de type "Event"
            StmtIterator eventIterator = model.listStatements(null, RDF.type, eventType);

            // Parcourir toutes les ressources de type Event
            while (eventIterator.hasNext()) {
                Statement stmt = eventIterator.nextStatement();
                Resource eventResource = stmt.getSubject();

                // Extraire les propriétés de l'événement
                String title = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title")).getString();
                String description = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description")).getString();
                String date = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date")).getString();
                String location = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location")).getString();
                String organizer = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer")).getString();
                String maxParticipants = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants")).getString();
                String image = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image")).getString();

                // Créer un EventDto pour chaque événement
                EventDto eventDto = new EventDto();
                eventDto.setTitle(title);
                eventDto.setDescription(description);
                eventDto.setDate(date); // Assurez-vous de formater la date correctement si nécessaire
                eventDto.setLocation(location);
                eventDto.setOrganizer(organizer);
                eventDto.setMax_participants(Integer.parseInt(maxParticipants));
                eventDto.setImage(image);

                // Ajouter l'événement à la liste
                events.add(eventDto);
            }
            return new ResponseEntity<>(events, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
 */




    // Inventory CRUD Operations

    //HEDIL
    /*crud delivery*/
    @GetMapping("/getAllDeliveryAgencies")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<DeliveryAgenceDto>> getAllDeliveryAgencies() {
        List<DeliveryAgenceDto> deliveryAgencies = new ArrayList<>();

        if (model != null) {
            try {
                // Define the URI for DeliveryAgence type
                Resource agencyType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#DeliveryAgence");

                // Define properties for agency attributes
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property addressProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#address");
                Property phoneProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#phoneNumber");

                Property openingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#openingHours");
                Property closingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#closingHours");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Iterate over all NamedIndividuals
                StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
                while (namedIndividuals.hasNext()) {
                    Statement statement = namedIndividuals.nextStatement();
                    Resource agencyResource = statement.getSubject();

                    // Check if it's a delivery agency based on URI or rdf:type
                    if (agencyResource.getURI() != null && agencyResource.getURI().contains("DeliveryAgence")) {
                        // Add the delivery agency to the list
                        addDeliveryAgenceToList(agencyResource, nameProperty, addressProperty, phoneProperty,  openingProperty, closingProperty, imageProperty, deliveryAgencies);
                    }
                }

                return new ResponseEntity<>(deliveryAgencies, HttpStatus.OK);
            } catch (Exception e) {
                System.err.println("Error retrieving delivery agencies: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            System.err.println("Model is null - Error when reading model from ontology");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addDeliveryAgenceToList(Resource centerResource, Property nameProperty, Property addressProperty,
                                         Property phoneProperty,
                                         Property openingProperty, Property closingProperty, Property imageProperty,
                                         List<DeliveryAgenceDto> deliveryAgencies) {
        String name = getLiteralValue(centerResource, nameProperty);
        String address = getLiteralValue(centerResource, addressProperty);
        String phoneNumber = getLiteralValue(centerResource, phoneProperty);

        String openingHours = getLiteralValue(centerResource, openingProperty);
        String closingHours = getLiteralValue(centerResource, closingProperty);
        String image = getLiteralValue(centerResource, imageProperty);

        // Create a DeliveryAgenceDto instance and set its properties
        DeliveryAgenceDto agencyDto = new DeliveryAgenceDto();
        agencyDto.setAgence(centerResource.getURI().toString());
        agencyDto.setName(name);
        agencyDto.setAddress(address);
        agencyDto.setPhoneNumber(phoneNumber);
        agencyDto.setImage(image);
        agencyDto.setOpeningHours(openingHours);
        agencyDto.setClosingHours(closingHours);

        deliveryAgencies.add(agencyDto);
    }





    @PostMapping("/addDeliveryAgence")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addDeliveryAgence(@RequestBody DeliveryAgenceDto agencyDto) {
        if (model != null) {
            try {
                // Generate a unique URI for the delivery agency
                String agencyUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#DeliveryAgence" + UUID.randomUUID().toString();

                // Create the Delivery Agency resource with the generated URI
                Resource agencyResource = model.createResource(agencyUri);

                // Define properties for the Delivery Agency entity
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property addressProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#address");
                Property phoneProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#phoneNumber");
                Property emailProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#email");
                Property managerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#managerName");
                Property openingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#openingHours");
                Property closingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#closingHours");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Add RDF type for the Delivery Agency resource
                model.add(agencyResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#DeliveryAgence"));

                // Add properties to the resource
                model.add(agencyResource, nameProperty, agencyDto.getName());
                model.add(agencyResource, addressProperty, agencyDto.getAddress());
                model.add(agencyResource, phoneProperty, agencyDto.getPhoneNumber());
                model.add(agencyResource, openingProperty, agencyDto.getOpeningHours());
                model.add(agencyResource, closingProperty, agencyDto.getClosingHours());
                model.add(agencyResource, imageProperty, agencyDto.getImage());

                // Save the model
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Return a structured JSON message
                ResponseMessage responseMessage = new ResponseMessage("Delivery Agency added successfully", agencyUri);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage("Error adding Delivery Agency: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error when reading model from ontology", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateDeliveryAgence")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateDeliveryAgence(@RequestBody DeliveryAgenceDto deliveryAgenceDto) {
        if (model != null) {
            try {
                // Récupérer la ressource DeliveryAgence en utilisant l'URI
                Resource deliveryAgenceResource = model.getResource(deliveryAgenceDto.getAgence()); // URI de l'agence à mettre à jour

                if (deliveryAgenceResource != null && deliveryAgenceResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#DeliveryAgence"))) {
                    // Supprimer les anciennes valeurs
                    Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                    Property addressProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#address");
                    Property phoneNumberProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#phoneNumber");
                    Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");
                    Property openingHoursProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#openingHours");
                    Property closingHoursProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#closingHours");

                    // Supprimer les anciennes propriétés (si présentes)
                    model.removeAll(deliveryAgenceResource, nameProperty, null);
                    model.removeAll(deliveryAgenceResource, addressProperty, null);
                    model.removeAll(deliveryAgenceResource, phoneNumberProperty, null);
                    model.removeAll(deliveryAgenceResource, imageProperty, null);
                    model.removeAll(deliveryAgenceResource, openingHoursProperty, null);
                    model.removeAll(deliveryAgenceResource, closingHoursProperty, null);

                    // Ajouter les nouvelles valeurs
                    model.add(deliveryAgenceResource, nameProperty, deliveryAgenceDto.getName());
                    model.add(deliveryAgenceResource, addressProperty, deliveryAgenceDto.getAddress());
                    model.add(deliveryAgenceResource, phoneNumberProperty, deliveryAgenceDto.getPhoneNumber());
                    model.add(deliveryAgenceResource, imageProperty, deliveryAgenceDto.getImage());
                    model.add(deliveryAgenceResource, openingHoursProperty, deliveryAgenceDto.getOpeningHours());
                    model.add(deliveryAgenceResource, closingHoursProperty, deliveryAgenceDto.getClosingHours());

                    // Sauvegarder le modèle
                    JenaEngine.saveModel(model, "data/ecodev.owl");

                    return new ResponseEntity<>("Delivery agency updated successfully: " + deliveryAgenceDto.getAgence(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Delivery agency not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error updating Delivery Agency: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteDeliveryAgence")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteDeliveryAgence(@RequestBody DeliveryAgenceDto deliveryAgenceDto) {
        if (model != null) {
            try {
                // Récupérer l'URI de l'agence à supprimer
                String agenceUri = deliveryAgenceDto.getAgence();  // URI passée dans le DTO

                // Vérifier que l'agence existe dans le modèle
                Resource deliveryAgenceResource = model.getResource(agenceUri);

                if (deliveryAgenceResource != null) {
                    // Vérifier que la ressource a le type DeliveryAgence
                    if (deliveryAgenceResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#DeliveryAgence")) ||
                            (deliveryAgenceResource.getURI() != null && deliveryAgenceResource.getURI().contains("DeliveryAgence"))) {

                        // Supprimer toutes les propriétés associées à l'agence
                        model.removeAll(deliveryAgenceResource, null, null);  // Supprime toutes les propriétés de cette ressource

                        // Sauvegarder le modèle après la suppression
                        JenaEngine.saveModel(model, "data/ecodev.owl");

                        return new ResponseEntity<>("Delivery agency deleted successfully: " + agenceUri, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Delivery agency not found or invalid type", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Delivery agency not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error deleting Delivery Agency: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // SIRINE
    //Crud Waste
// CRUD Operations
    @GetMapping("/getAllWaste")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<WasteDto>> getAllWaste() {
        List<WasteDto> wastes = new ArrayList<>();

        if (model != null) {
            try {
                Resource wasteType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Waste");

                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");
                Property quantityProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#quantity");
                Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_date");
                Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_location");

                StmtIterator wasteIterator = model.listStatements(null, RDF.type, wasteType);
                while (wasteIterator.hasNext()) {
                    Statement stmt = wasteIterator.nextStatement();
                    Resource wasteResource = stmt.getSubject();

                    // Extract Waste properties and add to list
                    WasteDto wasteDto = new WasteDto();
                    wasteDto.setWasteUri(wasteResource.getURI()); // Set the URI correctly
                    wasteDto.setImage(wasteResource.getProperty(imageProperty).getString());
                    wasteDto.setQuantity(Integer.parseInt(wasteResource.getProperty(quantityProperty).getString()));
                    wasteDto.setCollection_date(LocalDateTime.parse(wasteResource.getProperty(dateProperty).getString()));
                    wasteDto.setCollection_location(wasteResource.getProperty(locationProperty).getString());

                    wastes.add(wasteDto);
                    addWasteToList(wasteResource, imageProperty, quantityProperty, dateProperty, locationProperty, wastes);
                }

                return new ResponseEntity<>(wastes, HttpStatus.OK);
            } catch (Exception e) {
                System.err.println("Error retrieving wastes: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addWasteToList(Resource wasteResource, Property imageProperty, Property quantityProperty,
                                Property collectionDateProperty, Property collectionLocationProperty,
                                List<WasteDto> wastes) {

        // Extract values for waste properties safely
        String image = getLiteralValue(wasteResource, imageProperty);
        String quantityString = getLiteralValue(wasteResource, quantityProperty);
        String collectionDateString = getLiteralValue(wasteResource, collectionDateProperty);
        String collectionLocation = getLiteralValue(wasteResource, collectionLocationProperty);

        // Create a WasteDto instance and set its properties
        WasteDto wasteDto = new WasteDto();
        wasteDto.setWasteUri(wasteResource.getURI());

        wasteDto.setImage(image);

        // Parse quantity safely
        try {
            wasteDto.setQuantity(quantityString != null ? Integer.parseInt(quantityString) : 0);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing quantity: " + quantityString);
            wasteDto.setQuantity(0);  // Set to 0 if the quantity cannot be parsed
        }

        // Parse collection date safely
        if (collectionDateString != null) {
            try {
                wasteDto.setCollection_date(LocalDateTime.parse(collectionDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (Exception e) {
                System.err.println("Error parsing collection date: " + collectionDateString);
                wasteDto.setCollection_date(null);  // Set to null if the date cannot be parsed
            }
        } else {
            wasteDto.setCollection_date(null);
        }

        wasteDto.setCollection_location(collectionLocation);

        // Add the WasteDto to the list
        wastes.add(wasteDto);
    }

    @PostMapping("/addWaste")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addWaste(@RequestBody WasteDto wasteDto) {
        if (model != null) {
            try {
                // Generate a unique URI for the waste
                String wasteUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Waste" + UUID.randomUUID().toString();
                Resource wasteResource = model.createResource(wasteUri);

                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");
                Property quantityProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#quantity");
                Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_date");
                Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_location");

                // Add the waste data to the model
                model.add(wasteResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Waste"));
                model.add(wasteResource, imageProperty, wasteDto.getImage());
                model.add(wasteResource, quantityProperty, Integer.toString(wasteDto.getQuantity()));
                model.add(wasteResource, dateProperty, wasteDto.getCollection_date().toString());
                model.add(wasteResource, locationProperty, wasteDto.getCollection_location());

                // Save the model to the file
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Set the waste URI in the response DTO
                wasteDto.setWasteUri(wasteUri);

                // Return a successful response with the waste URI
                ResponseMessage responseMessage = new ResponseMessage("Waste added successfully", wasteDto.getWasteUri());
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);

            } catch (Exception e) {
                // Return an error response if something goes wrong
                ResponseMessage responseMessage = new ResponseMessage("Error adding Waste: " + e.getMessage(), null);
                return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Return an error response if the model is null
            ResponseMessage responseMessage = new ResponseMessage("Error when reading model from ontology", null);
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateWaste")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateWaste(@RequestBody WasteDto wasteDto) {
        if (model != null) {
            try {
                // Check if the waste resource exists
                Resource wasteResource = model.getResource(wasteDto.getWasteUri());
                if (wasteResource == null) {
                    return new ResponseEntity<>("Waste resource not found", HttpStatus.NOT_FOUND);
                }

                // Update waste properties
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");
                Property quantityProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#quantity");
                Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_date");
                Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#collection_location");

                // Update properties
                wasteResource.removeAll(imageProperty);
                wasteResource.addProperty(imageProperty, wasteDto.getImage());
                wasteResource.removeAll(quantityProperty);
                wasteResource.addProperty(quantityProperty, Integer.toString(wasteDto.getQuantity()));
                wasteResource.removeAll(dateProperty);
                wasteResource.addProperty(dateProperty, wasteDto.getCollection_date().toString());
                wasteResource.removeAll(locationProperty);
                wasteResource.addProperty(locationProperty, wasteDto.getCollection_location());

                // Save changes to the model
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Return success response
                return new ResponseEntity<>("Waste updated successfully", HttpStatus.OK);

            } catch (Exception e) {
                // Handle errors
                return new ResponseEntity<>("Error updating waste: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Model is null", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteWaste")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteWaste(@RequestBody WasteDto wasteDto) {
        if (model != null) {
            try {
                String wasteUri = wasteDto.getWasteUri();

                Resource wasteResource = model.getResource(wasteUri);

                if (wasteResource != null && wasteResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Waste"))) {
                    model.removeAll(wasteResource, null, null);
                    JenaEngine.saveModel(model, "data/ecodev.owl");

                    return new ResponseEntity<>("Waste deleted successfully: " + wasteUri, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Waste not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error deleting Waste: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //FARAH//

    @GetMapping("/getAllCenters")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<CentersDto>> getAllCenters() {
        List<CentersDto> centers = new ArrayList<>();

        if (model != null) {
            try {
                // Define the URI for Center type
                Resource centerType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Center");

                // Define properties for center attributes
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property addressProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#address");
                Property phoneProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#phoneNumber");
                Property emailProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#email");
                Property managerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#managerName");
                Property openingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#openingHours");
                Property closingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#closingHours");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Iterate over all NamedIndividuals
                StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
                while (namedIndividuals.hasNext()) {
                    Statement statement = namedIndividuals.nextStatement();
                    Resource centerResource = statement.getSubject();

                    // Check if it's a center based on URI or rdf:type
                    if (centerResource.getURI() != null && centerResource.getURI().contains("Center")) {
                        // Add the center to the list
                        addCenterToList(centerResource, nameProperty, addressProperty, phoneProperty, emailProperty,
                                managerProperty, openingProperty, closingProperty, imageProperty, centers);
                    }
                }

                return new ResponseEntity<>(centers, HttpStatus.OK);
            } catch (Exception e) {
                System.err.println("Error retrieving centers: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            System.err.println("Model is null - Error when reading model from ontology");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addCenterToList(Resource centerResource, Property nameProperty, Property addressProperty,
                                 Property phoneProperty, Property emailProperty, Property managerProperty,
                                 Property openingProperty, Property closingProperty, Property imageProperty,
                                 List<CentersDto> centers) {
        String name = getLiteralValue(centerResource, nameProperty);
        String address = getLiteralValue(centerResource, addressProperty);
        String phoneNumber = getLiteralValue(centerResource, phoneProperty);
        String email = getLiteralValue(centerResource, emailProperty);
        String managerName = getLiteralValue(centerResource, managerProperty);
        String openingHours = getLiteralValue(centerResource, openingProperty);
        String closingHours = getLiteralValue(centerResource, closingProperty);
        String image = getLiteralValue(centerResource, imageProperty);

        // Create a CentersDto instance and set its properties
        CentersDto centerDto = new CentersDto();
        centerDto.setCenterUri(centerResource.getURI());
        centerDto.setName(name);
        centerDto.setAddress(address);
        centerDto.setPhoneNumber(phoneNumber);
        centerDto.setEmail(email);
        centerDto.setManagerName(managerName);
        centerDto.setOpeningHours(openingHours);
        centerDto.setClosingHours(closingHours);
        centerDto.setImage(image);

        centers.add(centerDto);
    }

    @PostMapping("/addCenter")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addCenter(@RequestBody CentersDto centerDto) {
        if (model != null) {
            try {
                // Generate a unique URI for the center
                String centerUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Center" + UUID.randomUUID().toString();

                // Create the Center resource with the generated URI
                Resource centerResource = model.createResource(centerUri);

                // Define properties for the Center entity
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property addressProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#address");
                Property phoneProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#phoneNumber");
                Property emailProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#email");
                Property managerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#managerName");
                Property openingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#openingHours");
                Property closingProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#closingHours");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Add RDF type for the Center resource
                model.add(centerResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Center"));

                // Add properties to the resource
                model.add(centerResource, nameProperty, centerDto.getName());
                model.add(centerResource, addressProperty, centerDto.getAddress());
                model.add(centerResource, phoneProperty, centerDto.getPhoneNumber());
                model.add(centerResource, emailProperty, centerDto.getEmail());
                model.add(centerResource, managerProperty, centerDto.getManagerName());
                model.add(centerResource, openingProperty, centerDto.getOpeningHours());
                model.add(centerResource, closingProperty, centerDto.getClosingHours());
                model.add(centerResource, imageProperty, centerDto.getImage());

                // Save the model
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Return a structured JSON message
                ResponseMessage responseMessage = new ResponseMessage("Center added successfully", centerUri);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage("Error adding Center: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error when reading model from ontology", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    ////SARRA
    @GetMapping("/getAllRecycledProducts")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<RecycledProductDto>> getAllRecycledProducts() {
        List<RecycledProductDto> recycledProducts = new ArrayList<>();

        if (model != null) {
            try {
                // Define the URI for RecycledProduct type
                Resource recycledProductType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#RecycledProduct");

                // Define properties for recycled product attributes
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property quantityProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#quantity");
                Property priceProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#price");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Iterate over all NamedIndividuals
                StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
                while (namedIndividuals.hasNext()) {
                    Statement statement = namedIndividuals.nextStatement();
                    Resource productResource = statement.getSubject();

                    // Check if it's a recycled product based on URI or rdf:type
                    if (productResource.getURI() != null && productResource.getURI().contains("RecycledProduct")) {
                        // Add the recycled product to the list
                        addRecycledProductToList(productResource, nameProperty, descriptionProperty, quantityProperty,
                                priceProperty, imageProperty, recycledProducts);
                    }
                }

                return new ResponseEntity<>(recycledProducts, HttpStatus.OK);
            } catch (Exception e) {
                System.err.println("Error retrieving recycled products: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            System.err.println("Model is null - Error when reading model from ontology");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private void addRecycledProductToList(Resource productResource, Property nameProperty, Property descriptionProperty,
                                          Property quantityProperty, Property priceProperty, Property imageProperty,
                                          List<RecycledProductDto> recycledProducts) {
        String name = getLiteralValue(productResource, nameProperty);
        String description = getLiteralValue(productResource, descriptionProperty);
        String quantityString = getLiteralValue(productResource, quantityProperty);
        String priceString = getLiteralValue(productResource, priceProperty);
        String image = getLiteralValue(productResource, imageProperty);

        // Create a RecycledProductDto instance and set its properties
        RecycledProductDto recycledProductDto = new RecycledProductDto();
        recycledProductDto.setProductUri(productResource.getURI());
        recycledProductDto.setName(name);
        recycledProductDto.setDescription(description);

        // Parse quantity safely
        try {
            recycledProductDto.setQuantity(quantityString != null ? Integer.parseInt(quantityString) : 0);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing quantity: " + quantityString);
            recycledProductDto.setQuantity(0);  // Set to 0 if the quantity cannot be parsed
        }

        // Parse price safely
        try {
            recycledProductDto.setPrice(priceString != null ? Float.parseFloat(priceString) : 0.0f);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceString);
            recycledProductDto.setPrice(0.0f);  // Set to 0.0 if the price cannot be parsed
        }

        recycledProductDto.setImage(image);

        recycledProducts.add(recycledProductDto);
    }



    @PostMapping("/addRecycledProduct")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addRecycledProduct(@RequestBody RecycledProductDto recycledProductDto) {
        if (model != null) {
            try {
                // Générer une URI unique pour le produit recyclé
                String productUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#RecycledProduct" + UUID.randomUUID().toString();

                // Créer la ressource RecycledProduct avec l'URI générée
                Resource productResource = model.createResource(productUri);

                // Définir les propriétés pour l'entité RecycledProduct
                Property nameProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#name");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property quantityProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#quantity");
                Property priceProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#price");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Ajouter le type RDF pour la ressource RecycledProduct
                model.add(productResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#RecycledProduct"));

                // Ajouter les propriétés à la ressource
                model.add(productResource, nameProperty, recycledProductDto.getName());
                model.add(productResource, descriptionProperty, recycledProductDto.getDescription());
                model.add(productResource, quantityProperty, Integer.toString(recycledProductDto.getQuantity()));
                model.add(productResource, priceProperty, Float.toString(recycledProductDto.getPrice()));
                model.add(productResource, imageProperty, recycledProductDto.getImage());

                // Sauvegarder le modèle
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Retourner un message structuré en JSON
                ResponseMessage responseMessage = new ResponseMessage("Recycled Product added successfully", productUri);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage("Error adding Recycled Product: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error when reading model from ontology", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    // Publication CRUD Operations
    @PostMapping("/addPublication")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseMessage> addPublication(@RequestBody PublicationDto publicationDto) {
        if (model != null) {
            try {
                // Générer une URI unique pour la publication
                String publicationUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Publication" + UUID.randomUUID().toString();

                // Créer la ressource Publication avec l'URI générée
                Resource publicationResource = model.createResource(publicationUri);

                // Définir les propriétés pour l'entité Publication
                Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property contentProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#content");
                Property categoryProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#category");
                Property tagsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#tags");

                // Ajouter le type RDF pour la ressource Publication
                model.add(publicationResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Publication"));

                // Ajouter les propriétés à la ressource
                model.add(publicationResource, titleProperty, publicationDto.getTitle());
                model.add(publicationResource, descriptionProperty, publicationDto.getDescription());
                model.add(publicationResource, contentProperty, publicationDto.getContent());
                model.add(publicationResource, categoryProperty, publicationDto.getCategory());
                model.add(publicationResource, tagsProperty, publicationDto.getTags());

                // Sauvegarder le modèle
                JenaEngine.saveModel(model, "data/ecodev.owl");

                // Retourner un message structuré en JSON
                ResponseMessage responseMessage = new ResponseMessage("Publication added successfully", publicationUri);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseMessage("Error adding Publication: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error when reading model from ontology", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllPublications")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<PublicationDto>> getAllPublications() {
        List<PublicationDto> publications = new ArrayList<>();

        if (model != null) {
            try {
                // Définir le type Publication
                Resource publicationType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Publication");

                // Définir les propriétés pour l'entité Publication
                Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property contentProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#content");
                Property categoryProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#category");
                Property tagsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#tags");

                // Parcourir les individus nommés
                StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
                while (namedIndividuals.hasNext()) {
                    Statement statement = namedIndividuals.nextStatement();
                    Resource publicationResource = statement.getSubject();

                    // Vérifier si la ressource est une Publication
                    if (publicationResource.getURI() != null && publicationResource.getURI().contains("Publication")) {
                        // Ajouter la publication à la liste
                        addPublicationToList(publicationResource, titleProperty, descriptionProperty, contentProperty,
                                categoryProperty, tagsProperty, publications);
                    }
                }

                return new ResponseEntity<>(publications, HttpStatus.OK);
            } catch (Exception e) {
                System.err.println("Error retrieving publications: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            System.err.println("Model is null - Error when reading model from ontology");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Méthode auxiliaire pour ajouter une publication à la liste
    // Méthode auxiliaire pour ajouter une publication à la liste
    private void addPublicationToList(Resource publicationResource, Property titleProperty, Property descriptionProperty,
                                      Property contentProperty, Property categoryProperty, Property tagsProperty,
                                      List<PublicationDto> publications) {
        String title = getLiteralValuePublication(publicationResource, titleProperty);
        String description = getLiteralValuePublication(publicationResource, descriptionProperty);
        String content = getLiteralValuePublication(publicationResource, contentProperty);
        String category = getLiteralValuePublication(publicationResource, categoryProperty);
        String tags = getLiteralValuePublication(publicationResource, tagsProperty);

        // Créer une instance de PublicationDto et définir ses propriétés
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setPublication(publicationResource.getURI());
        publicationDto.setTitle(title);
        publicationDto.setDescription(description);
        publicationDto.setContent(content);
        publicationDto.setCategory(category);
        publicationDto.setTags(tags);

        publications.add(publicationDto);
    }

    // Méthode auxiliaire pour récupérer la valeur littérale d'une propriété
    private String getLiteralValuePublication(Resource resource, Property property) {
        Statement statement = resource.getProperty(property);
        return statement != null ? statement.getObject().toString() : null;
    }


    @PutMapping("/updatePublication")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updatePublication(@RequestBody PublicationDto publicationDto) {
        if (model != null) {
            try {
                // Récupérer la ressource Publication en utilisant l'URI
                Resource publicationResource = model.getResource(publicationDto.getPublication()); // URI unique de la publication à mettre à jour

                if (publicationResource != null && publicationResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Publication"))) {
                    // Supprimer les anciennes valeurs
                    Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                    Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                    Property contentProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#content");
                    Property categoryProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#category");
                    Property tagsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#tags");

                    // Supprimer les anciennes propriétés (si présentes)
                    model.removeAll(publicationResource, titleProperty, null);
                    model.removeAll(publicationResource, descriptionProperty, null);
                    model.removeAll(publicationResource, contentProperty, null);
                    model.removeAll(publicationResource, categoryProperty, null);
                    model.removeAll(publicationResource, tagsProperty, null);

                    // Ajouter les nouvelles valeurs
                    model.add(publicationResource, titleProperty, publicationDto.getTitle());
                    model.add(publicationResource, descriptionProperty, publicationDto.getDescription());
                    model.add(publicationResource, contentProperty, publicationDto.getContent());
                    model.add(publicationResource, categoryProperty, publicationDto.getCategory());
                    model.add(publicationResource, tagsProperty, publicationDto.getTags());

                    // Sauvegarder le modèle
                    JenaEngine.saveModel(model, "data/ecodev.owl");

                    return new ResponseEntity<>("Publication updated successfully: " + publicationDto.getPublication(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Publication not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error updating Publication: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deletePublication/{publicationUri}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deletePublication(@PathVariable String publicationUri) {
        // Décoder l'URL avant de l'utiliser
        String decodedUri = URLDecoder.decode(publicationUri, StandardCharsets.UTF_8);
        // Utiliser l'URI décodée pour supprimer la publication
        // Récupérer la publication à supprimer avec le URI décodé
        Resource publicationResource = model.getResource(decodedUri);

        if (publicationResource != null) {
            // Procéder à la suppression de la publication
            model.removeAll(publicationResource, null, null);
            JenaEngine.saveModel(model, "data/ecodev.owl");
            return new ResponseEntity<>("Publication deleted successfully: " + decodedUri, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Publication not found", HttpStatus.NOT_FOUND);
        }
    }

}

