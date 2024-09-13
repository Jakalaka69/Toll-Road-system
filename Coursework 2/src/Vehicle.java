
// Vehicle will be a class that will create the foundation of every vehicle made
public abstract class Vehicle {

    //declaring variables
    public String registration, manufacturer;

    //constructor method
    public Vehicle(String registration, String manufacturer){
        this.registration = registration;
        this.manufacturer = manufacturer;
    }
    //Creates the abstract method that will be used in the other vehicles
    public abstract int calculateBasicTripCost();

    //Creating the accessor methods for the two attributes
    public String getRegistration(){
        return this.registration;
    };
    public String getManufacturer(){
        return this.manufacturer;
    };
    public String toString(){
        return String.format("Vehicle registration = " + registration + ", Vehicle manufacturer = " + manufacturer);
    }





}
