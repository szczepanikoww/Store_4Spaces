package store;

import java.util.Date;

public class Invoice {
    private int InvoiceID;
    private Order order;
    private Date dateOfInvoice;

    //Constructor
    public Invoice(int invoiceID, Order order, Date dateOfInvoice) {
        InvoiceID = invoiceID;
        this.order = order;
        this.dateOfInvoice = dateOfInvoice;
    }

    //setters
    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDateOfInvoice(Date dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    //getters
    public int getInvoiceID() {
        return InvoiceID;
    }

    public Order getOrder() {
        return order;
    }

    public Date getDateOfInvoice() {
        return dateOfInvoice;
    }

    // other methods
    public String generateInvoice() {
        return "Invoice ID: " + InvoiceID + "\nOrder ID: " + order.getOrderID() + "\nDate of Invoice: " + dateOfInvoice + "\n" + order.getDetails();
    }
}
