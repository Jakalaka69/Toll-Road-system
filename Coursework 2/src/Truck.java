//Creates a new class that is a subclass of vehicle
public class Truck extends Vehicle {

    //Declaring variables

    public int numTrailers;

    //Constructor method
    public Truck (String registration, String manufacturer, int numTrailers){
        super(registration, manufacturer);
        this.numTrailers = numTrailers;
    }
    //Overrides the abstract method in the vehicle class in order to
    //calculate the trip cost based on how many trailers the truck can hold
    @Override
    public int calculateBasicTripCost() {
        if(numTrailers == 1 ){
            return 1250;
        }
        else{
            return 1500;
        }
    }

    //Accessor method for the new attribute
    public int getNumTrailers(){
        return this.numTrailers;
    }

    //Converts all the information about the Truck into a readable string
    public String toString(){
        return String.format("Vehicle registration = " + registration + ", Vehicle manufacturer = " + manufacturer + ", Number of trailers = " + numTrailers);
    }

    //test harness
    public static void main(String[] args) {
        Truck truck1 = new Truck("LR19 GH4", "Nissan", 1);
        System.out.println(truck1);
        System.out.println(truck1.calculateBasicTripCost());
    }
}
