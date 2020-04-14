package com.mycompany.tarea2progra;

import java.util.ArrayList;
import java.util.Scanner;

public class product {

// Se definen globalmente las variables que seran necesarias    
    String keep = "";
    String lista = "";
    int i = 0;
    int x = 0;
    int totalsin = 0;
    int canTot = 0;
    double total;
    Scanner sc = new Scanner(System.in);
    double t;
    ArrayList<product> avaiableProducts = new ArrayList<product>();
    private String code;
    private String expitarionDate;
    private double price;
    private String brand;

    public product(String code, String expitarionDate, double price, String brand) {
        this.code = code;
        this.expitarionDate = expitarionDate;
        this.price = price;
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpitarionDate() {
        return expitarionDate;
    }

    public void setExpitarionDate(String expitarionDate) {
        this.expitarionDate = expitarionDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "code " + this.code + " vence " + this.expitarionDate + " precio " + this.price + " marca " + this.brand;
    }

//En este metodo se estabalecen los productos que se ofreceran, con distintos datos ya definidos.    
    public void Producto() {

        product arroz = new product("001A", "02/01/2021", 2300, "arroz91%Entero");
        product frijoles = new product("002B", "15/05/2020", 2500, "frijolesRojos");
        product helado = new product("003C", "31/08/2020", 5500, "heladoFresa-Vainilla");
        product cafe = new product("004D", "25/01/2020", 980, "cafeRey");
        product azucar = new product("005E", "09/08/2020", 2000, "azucarMaria");

        avaiableProducts.add(arroz);
        avaiableProducts.add(frijoles);
        avaiableProducts.add(helado);
        avaiableProducts.add(cafe);
        avaiableProducts.add(azucar);

        System.out.println("Productos disponibles: \n  " + " Producto:" + arroz.getBrand() + "; Código: " + arroz.getCode() + "; Expira el: " + arroz.getExpitarionDate() + " ; Precio: " + arroz.getPrice()
                + "\n " + "  Producto: " + frijoles.getBrand() + "; Código: " + frijoles.getCode() + " ; Expira el: " + frijoles.getExpitarionDate() + " ; Precio: " + frijoles.getPrice()
                + "\n " + "  Producto: " + helado.getBrand() + " ; Código: " + helado.getCode() + " ; Expira el: " + helado.getExpitarionDate() + " ; Precio: " + helado.getPrice()
                + "\n" + "   Producto: " + cafe.getBrand() + " ; Código: " + cafe.getCode() + " ; Expira el: " + cafe.getExpitarionDate() + " ; Precio: " + cafe.getPrice()
                + "\n" + "   Producto: " + azucar.getBrand() + " ; Código: " + azucar.getCode() + " ; Expira el: " + azucar.getExpitarionDate() + " ; Precio: " + azucar.getPrice());

    }// end  public void Producto()

// en estos 2 metodos se define parte de lo que sera el menu    
    public void factura() {

        System.out.println(" COMPRAS: \n" + keep + "\n Total sin impuestos: " + totalsin + "\n Cantidad total de productos: " + canTot + " \n TOTAL A PAGAR: " + total);

    }

    public void compra() {

        String list = "";

        System.out.println("Dijite el código que corresponde al producto que desea");
        String codigo = sc.nextLine();
        for (i = 0; i < avaiableProducts.size(); i++) {
            if (codigo.equalsIgnoreCase(avaiableProducts.get(i).getCode())) {
                x = i;
                list = avaiableProducts.get(i) + "";

            }

        }

        if (list.equals("")) {
            System.out.println("ERROR: El código ingresado no existe en nuestra oferta de productos");
            compra();
        }

        System.out.println("Ingrese la cantidad de unidades que desea de este producto");
        int cant = Integer.parseInt(sc.nextLine());
        canTot += cant;
        lista = cant + "       :        " + list;
        keep += lista + cant * avaiableProducts.get(x).getPrice() + "\n                ";
        totalsin += cant * avaiableProducts.get(x).getPrice();

        System.out.println("Para comprar otro producto dijite 1 \nSi desea ver la lista de productos nuevamente dijite 2 \nPara aplicar los impuestos a su compra dijite 3\nSi desea finaizar su compra djite 4");
        int comp = Integer.parseInt(sc.nextLine());

        switch (comp) {
            case 1:
                compra();
                break;
            case 2:
                Producto();
                compra();
                break;
            case 3:
                System.out.println("\nDijite 1 para aplicar 13% de impuesto por IVA\nDijite 2 para aplicar 10% de impuesto por servicio\nDijite 3 para aplicar ambos impuestos\nDijite 4 si el producto es excento de impuestos ");
                int imp = Integer.parseInt(sc.nextLine());
                switch (imp) {
                    case 1:
                        total = iva13(totalsin);
                        break;
                    case 2:
                        total = iva10(totalsin);
                        break;
                    case 3:
                        total = bothInv(totalsin);
                        break;
                    default:
                        total += totalsin;
                        break;
                }// end  switch (imp)

                break;

        }
    } //end public void compra() 

//En los siguientes 3 metodos se define todo lo relacionado con impuestos.
    public double iva13(double numTot) {

        double IVA13 = 0.13;

        double result = numTot + (numTot * IVA13);

        return result;
    }// end public double iva13(double numTot) 

    public double iva10(double numTot) {

        double IVA10 = 0.10;

        double result = numTot + (numTot * IVA10);

        return result;

    }// end ´ public double iva10(double numTot)

    public double bothInv(double numTot) {

        double IVA23 = 0.23;

        double result = numTot + (numTot * IVA23);

        return result;
    }// end public double bothInv(double numTot)

}// end public class product 
