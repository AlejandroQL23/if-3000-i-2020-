package com.mycompany.tarea2progra;

import java.util.Calendar;

public class invoice extends product {

    product p = new product("", "", 0, "");

    private int noInvoice;
    private String dateAndTime;
    private int totToPay;
    private int productQuantity;

    public invoice(int noInvoice, String dateAndTime, int totToPay, int productQuantity, String code, String expitarionDate, double price, String brand) {
        super(code, expitarionDate, price, brand);
        this.noInvoice = noInvoice;

        this.dateAndTime = dateAndTime;
        this.totToPay = totToPay;
        this.productQuantity = productQuantity;
    }

    public int getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(int noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getTotToPay() {
        return totToPay;
    }

    public void setTotToPay(int totToPay) {
        this.totToPay = totToPay;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    
//en este metodo se muestran cieros detalles de la factura    
    public void ShowInvoice() {

        Calendar cal = Calendar.getInstance();
        String fecha = cal.get(cal.DATE) + "/" + cal.get(cal.MONTH) + "/" + cal.get(cal.YEAR);
        String hora = cal.get(cal.HOUR_OF_DAY) + ":" + cal.get(cal.MINUTE) + ":" + cal.get(cal.SECOND);
        int numFac = (int) (Math.random() * 1000 + 1);// el numero de factura se maneja con un random

        setNoInvoice(numFac);
        setDateAndTime(fecha + " " + hora);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println(
                "Fecha y hora de la compra: " + getDateAndTime() + "\n"
                + "NO de factura: " + getNoInvoice() );
              

    }// end  public void ShowInvoice()

}// end public class invoice extends product
