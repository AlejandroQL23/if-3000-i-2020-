package com.mycompany.tarea2progra;

import java.util.Scanner;

public class customer extends company {

    invoiceLogic iL = new invoiceLogic();
    private int age;

    Scanner sc = new Scanner(System.in);
    String nombreIngresado;
    String edadIngresado;
    String cedulaIngresado;
    String direccionIngresado;
    String teleIngresado;

    public customer(int age, String name, int ID, String address, int telephone) {
        super(name, ID, address, telephone);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
//En este metodo se hace la solicitud de los datos al cliente
    public void infoCustomer() {

        System.out.println("ingrese  su nombre");
        nombreIngresado = sc.nextLine();
        System.out.println("ingrese su edad");
        edadIngresado = sc.nextLine();
        System.out.println("ingrese su cedula");
        cedulaIngresado = sc.nextLine();
        System.out.println("ingrese su direccion");
        direccionIngresado = sc.nextLine();
        System.out.println("ingrese su telefono");
        teleIngresado = sc.nextLine();

    }// end public void infoCustomer()

//En este metodo se muestra la informacion que el cliente proporciono en el metodo anterior    
    public void ShowInfoCusto() {

        setName(nombreIngresado);
        setAge(Integer.parseInt(edadIngresado));
        setAddress(direccionIngresado);
        setID(Integer.parseInt(cedulaIngresado));
        setTelephone(Integer.parseInt(teleIngresado));

        System.out.println("Informacion para la emision de factura."+ "\n"
                + "Nombre y apellidos: " + getName() + "\n"
                + "Edad: " + getAge() + "\n"
                + "Cedula de identidad: " + getID() + "\n"
                + "Direccion de residencia: " + getAddress() + "\n"
                + "Numero de celular/telefono: " + getTelephone());

    }//end public void ShowInfoCusto()

}//end public class customer extends company
