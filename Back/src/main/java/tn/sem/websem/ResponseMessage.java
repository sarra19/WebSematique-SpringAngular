package tn.sem.websem;
public class ResponseMessage {
    private String message;
    private String eventUri;



    // Constructeurs, getters et setters
    public ResponseMessage(String message, String eventUri ) {
        this.message = message;
        this.eventUri = eventUri;


    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEventUri() {
        return eventUri;
    }

    public void setEventUri(String eventUri) {
        this.eventUri = eventUri;
    }


}