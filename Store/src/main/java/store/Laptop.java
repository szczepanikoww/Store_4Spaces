package store;

public class Laptop extends Product{
    private String LaptopOS;
    private String LaptopProcessor;
    private int LaptopRAM;
    private int LaptopStorage;
    private String LaptopGraphics;
    private String LaptopDisplay;
    private String LaptopBattery;

    //Constructor
    public Laptop(int productID, String productName, double productPrice, int productQuantity, String productBrand, String laptopOS, String laptopProcessor, int laptopRAM, int laptopStorage, String laptopGraphics, String laptopDisplay, String laptopBattery) {
        super(productID, productName, productPrice, productQuantity, productBrand);
        this.LaptopOS = laptopOS;
        this.LaptopProcessor = laptopProcessor;
        this.LaptopRAM = laptopRAM;
        this.LaptopStorage = laptopStorage;
        this.LaptopGraphics = laptopGraphics;
        this.LaptopDisplay = laptopDisplay;
        this.LaptopBattery = laptopBattery;
    }

    //setters
//    public void getModel() {
//        return super.getProductName();
//    }
    public void setLaptopOS(String laptopOS) {
        this.LaptopOS = laptopOS;
    }

    public void setLaptopProcessor(String laptopProcessor) {
        this.LaptopProcessor = laptopProcessor;
    }

    public void setLaptopRAM(int laptopRAM) {
        this.LaptopRAM = laptopRAM;
    }

    public void setLaptopStorage(int laptopStorage) {
        this.LaptopStorage = laptopStorage;
    }

    public void setLaptopGraphics(String laptopGraphics) {
        this.LaptopGraphics = laptopGraphics;
    }

    public void setLaptopDisplay(String laptopDisplay) {
        this.LaptopDisplay = laptopDisplay;
    }

    public void setLaptopBattery(String laptopBattery) {
        this.LaptopBattery = laptopBattery;
    }

    //getters

    public String getLaptopOS() {
        return LaptopOS;
    }

    public String getLaptopProcessor() {
        return LaptopProcessor;
    }

    public int getLaptopRAM() {
        return LaptopRAM;
    }

    public int getLaptopStorage() {
        return LaptopStorage;
    }

    public String getLaptopGraphics() {
        return LaptopGraphics;
    }

    public String getLaptopDisplay() {
        return LaptopDisplay;
    }

    public String getLaptopBattery() {
        return LaptopBattery;
    }

    public String getDetails() {
        return super.getDetails() +  "\nLaptop OS: " + LaptopOS + "\nLaptop Processor: " + LaptopProcessor + "\nLaptop RAM: " + LaptopRAM + "\nLaptop Storage: " + LaptopStorage + "\nLaptop Graphics: " + LaptopGraphics + "\nLaptop Display: " + LaptopDisplay + "\nLaptop Battery: " + LaptopBattery;
    }

}
