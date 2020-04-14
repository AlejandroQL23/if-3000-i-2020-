package com.mycompany.tarea2progra;

import java.util.Scanner;

public class invoiceLogic {

    public void logic() {

        //instancias
        product p = new product("", "", 0, "");
        invoice i = new invoice(0, "", 0, 0, "", "", 0, "");
        company c = new company("", 0, "", 0);
        customer k = new customer(0, "", 0, "", 0);
        Scanner sc = new Scanner(System.in);

        //Se define un try y catch para evitar ciertas excepciones
        try {
            System.out.println("¡BIENVENIDO al supermercado TRIPLE 7!\nDijite 1 para ingresar sus datos personales al sistema");
            int dat = Integer.parseInt(sc.nextLine());

//el menu se realiza a partir de la estrucututa switch anidada
            switch (dat) {

                case 1:
                    k.infoCustomer();

                    System.out.println("\nDijite 2 para ver los productos que ofrecemos.");
                    int dat2 = Integer.parseInt(sc.nextLine());
                    switch (dat2) {
                        case 2:
                            p.Producto();
                            System.out.println("\nDijite 3 si desea realizar la compra de alguno de nuestros productos.");
                            int dat3 = Integer.parseInt(sc.nextLine());
                            switch (dat3) {
                                case 3:
                                    p.compra();
                                    System.out.println("Dijite 4 si desea generar su factura");
                                    int dat4 = Integer.parseInt(sc.nextLine());

                                    switch (dat4) {
                                        case 4:
                                            i.ShowInvoice();
                                            System.out.println("\n\n");
                                            c.ShowInfoCompany();
                                            System.out.println("\n\n");
                                            p.factura();
                                            System.out.println("\n\n");
                                            k.ShowInfoCusto();
                                            System.out.println("\n\n");
                                            break;
                                    }// end switch (dat4)
                                    break;
                            }// end  switch (dat3)
                            break;
                    }//end switch (dat2)
                default:
                    System.out.println("\nGracias por su visita, regrese pronto a su supermecado favorito");
                    break;
            }//end switch (dat)

        }// end try
        catch (NumberFormatException nfe) {
            System.out.println("error: " + nfe);
        }//end catch
        catch (NullPointerException npe) {
            System.out.println("error: " + npe);
        }//end catch

    }//end public void logic()

}// end public class invoiceLogic
