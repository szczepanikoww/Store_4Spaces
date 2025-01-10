package store;

import javafx.scene.image.Image;

import java.io.File;

public class Phone extends Product {
    private String PhoneOS;
    private String PhoneProcessor;
    private int PhoneRAM;
    private int PhoneStorage;
    private String PhoneCamera;
    private String PhoneDisplay;
    private String PhoneBattery;

    public Phone(){

    }
    public Phone(String productName, double productPrice, int productQuantity, String productBrand,
                 String phoneOS, String phoneProcessor, int phoneRAM, int phoneStorage,
                 String phoneCamera, String phoneDisplay, String phoneBattery) {

        super(productName, productPrice, productQuantity, productBrand);
        this.PhoneOS = phoneOS;
        this.PhoneProcessor = phoneProcessor;
        this.PhoneRAM = phoneRAM;
        this.PhoneStorage = phoneStorage;
        this.PhoneCamera = phoneCamera;
        this.PhoneDisplay = phoneDisplay;
        this.PhoneBattery = phoneBattery;
    }

    //settery do produktu
    public void setPhoneName(String phoneName) {
        super.setProductName(phoneName);
    }
    public void setPhonePrice(double phonePrice) {
        super.setProductPrice(phonePrice);
    }
    public void setPhoneQuantity(int phoneQuantity) {
        super.setProductQuantity(phoneQuantity);
    }
    public void setPhoneBrand(String phoneBrand) {
        super.setProductBrand(phoneBrand);
    }

    public void setPhoneDescription(String phoneDescription) {
        super.setProductDescription(phoneDescription);
    }

    //settery do telefonu
    public void setPhoneOS(String phoneOS) {
        this.PhoneOS = phoneOS;
    }

    public void setPhoneProcessor(String phoneProcessor) {
        this.PhoneProcessor = phoneProcessor;
    }

    public void setPhoneRAM(int phoneRAM) {
        this.PhoneRAM = phoneRAM;
    }

    public void setPhoneStorage(int phoneStorage) {
        this.PhoneStorage = phoneStorage;
    }

    public void setPhoneCamera(String phoneCamera) {
        this.PhoneCamera = phoneCamera;
    }

    public void setPhoneDisplay(String phoneDisplay) {
        this.PhoneDisplay = phoneDisplay;
    }

    public void setPhoneBattery(String phoneBattery) {
        this.PhoneBattery = phoneBattery;
    }

    public void setImage(String imagePath) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                super.setImage(image);
            } else {
                System.out.println("Plik nie istnieje: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPhoneOS() {
        return PhoneOS;
    }

    public String getPhoneProcessor() {
        return PhoneProcessor;
    }

    public int getPhoneRAM() {
        return PhoneRAM;
    }

    public int getPhoneStorage() {
        return PhoneStorage;
    }

    public String getPhoneCamera() {
        return PhoneCamera;
    }

    public String getPhoneDisplay() {
        return PhoneDisplay;
    }

    public String getPhoneBattery() {
        return PhoneBattery;
    }

    public String getDetails() {
        return super.getDetails() + "\nPhone OS: " + PhoneOS + "\nPhone Processor: " + PhoneProcessor + "\nPhone RAM: " + PhoneRAM + "\nPhone Storage: " + PhoneStorage + "\nPhone Camera: " + PhoneCamera + "\nPhone Display: " + PhoneDisplay + "\nPhone Battery: ";
    }
}
