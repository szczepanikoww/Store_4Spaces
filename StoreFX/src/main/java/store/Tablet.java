package store;

public class Tablet extends Product{
    private String TabletOS;
    private String TabletProcessor;
    private int TabletRAM;
    private int TabletStorage;
    private String TabletBattery;
    private double TabletScreenSize;

    //Constructor

    public Tablet(){
        this.setTabletCategory("Tablet");

    }
    public Tablet(String productName, double productPrice, int productQuantity, String productBrand, String tabletOS, String tabletProcessor, int tabletRAM, int tabletStorage, String tabletBattery, double tabletScreenSize) {
        super(productName, productPrice, productQuantity, productBrand);
        this.TabletOS = tabletOS;
        this.TabletProcessor = tabletProcessor;
        this.TabletRAM = tabletRAM;
        this.TabletStorage = tabletStorage;
        this.TabletBattery = tabletBattery;
        this.TabletScreenSize = tabletScreenSize;
        this.setTabletCategory("Tablet");
    }

    //setters for product
    public void setTabletName(String tabletName) {
        super.setProductName(tabletName);
    }
    public void setTabletPrice(double tabletPrice) {
        super.setProductPrice(tabletPrice);
    }
    public void setTabletQuantity(int tabletQuantity) {
        super.setProductQuantity(tabletQuantity);
    }
    public void setTabletBrand(String tabletBrand) {
        super.setProductBrand(tabletBrand);
    }
    public void setTabletDescription(String tabletDescription) {
        super.setProductDescription(tabletDescription);
    }
    public void setTabletCategory(String tabletCategory) {
        super.setProductCategory(tabletCategory);
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

    public void setTabletScreenSize(double tabletScreenSize) {
        this.TabletScreenSize = tabletScreenSize;
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
        return super.getDetails() + "\nTablet OS: " + TabletOS + "\nTablet Processor: " + TabletProcessor + "\nTablet RAM: " + TabletRAM + "\nTablet Storage: " + TabletStorage + "\nTablet Battery: " + TabletBattery;
    }




}
