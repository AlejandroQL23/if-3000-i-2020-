package com.mycompany.tarea2progra;


public class company {
    
private String name;
private int ID;
private String address;
private int telephone;

    public company(String name, int ID, String address, int telephone) {
        this.name = name;
        this.ID = ID;
        this.address = address;
        this.telephone = telephone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    
    
//En este meodo se mosstrara la informacionde de la empresa
  public void ShowInfoCompany(){
      
      setName("TRIPLE7 MARQUET");
      setAddress("GUADALUPE CARTAGO 400M suroeste de la plaza de deportes");
      setID(1166004200);
      setTelephone(21016576);
    
         System.out.println("Nombre de la empresa: " + getName() + "\n"
                + "Cedula juridica: " + getID()+ "\n"
                + "Direccion de la mepresa : " + getAddress() + "\n"
                + "Telefono de la empresa: " + getTelephone());
         
}//end public void ShowInfoCompany()
    
}// end public class company
