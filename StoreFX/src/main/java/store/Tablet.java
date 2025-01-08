package store;

public class Tablet extends Product{
    private String TabletOS;
    private String TabletProcessor;
    private int TabletRAM;
    private int TabletStorage;
    private String TabletBattery;

    //Constructor
    public Tablet(int productID, String productName, double productPrice, int productQuantity, String productBrand, String tabletOS, String tabletProcessor, int tabletRAM, int tabletStorage, String tabletBattery) {
        super(productID, productName, productPrice, productQuantity, productBrand);
        this.TabletOS = tabletOS;
        this.TabletProcessor = tabletProcessor;
        this.TabletRAM = tabletRAM;
        this.TabletStorage = tabletStorage;
        this.TabletBattery = tabletBattery;
    }

    //setters
    public void setTabletOS(String tabletOS) {
        this.TabletOS = tabletOS;
    }

    public void setTabletProcessor(String tabletProcessor) {
        this.TabletProcessor = tabletProcessor;
    }

    public void setTabletRAM(int tabletRAM) {
        this.TabletRAM = tabletRAM;
    }

    public void setTabletStorage(int tabletStorage) {
        this.TabletStorage = tabletStorage;
    }

    public void setTabletBattery(String tabletBattery) {
        this.TabletBattery = tabletBattery;
    }

    //getters
    public String getTabletOS() {
        return TabletOS;
    }

    public String getTabletProcessor() {
        return TabletProcessor;
    }

    public int getTabletRAM() {
        return TabletRAM;
    }

    public int getTabletStorage() {
        return TabletStorage;
    }

    public String getTabletBattery() {
        return TabletBattery;
    }

    public String getDetails() {
        return "Product ID: " + getProductID() + "\nProduct Name: " + getProductName() + "\nProduct Price: " + getPrice() + "\nProduct Quantity: " + getQuantity() + "\nProduct Brand: " + getBrand() + "\nTablet OS: " + TabletOS + "\nTablet Processor: " + TabletProcessor + "\nTablet RAM: " + TabletRAM + "\nTablet Storage: " + TabletStorage + "\nTablet Battery: " + TabletBattery;
    }


}
