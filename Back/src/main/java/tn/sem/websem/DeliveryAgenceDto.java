package tn.sem.websem;

public class DeliveryAgenceDto {

    private String name;

    private String address;

    private String phoneNumber;

    private String image;

    private String openingHours;

    private String closingHours;
    private String agence; //uri

    public DeliveryAgenceDto(String agenceUri, String name, String address, String phoneNumber, String image, String openingHours, String closingHours) {
    }

    public DeliveryAgenceDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }
    
}
