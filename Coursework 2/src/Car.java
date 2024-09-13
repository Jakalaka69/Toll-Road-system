//Creates a new class that is a subclass of vehicle
public class Car extends Vehicle{

    //Declaring variables

    private int numberOfSeats;

    //Constructor method
    public Car (String registration, String manufacturer, int numberOfSeats){
        super(registration, manufacturer);
        this.numberOfSeats = numberOfSeats;
    }

    //Overrides the abstract method in the vehicle class in order to
    //calculate the trip cost based on how many seats the car has
    @Override
    public int calculateBasicTripCost() {
        if(this.numberOfSeats < 6){
            return 500;
        }
        else {
            return 600;
        }
    }
    //Creating the accessor methods for the new attribute
    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }

    //Converts all the information about the car into a readable string
    public String toString(){
        return String.format("Vehicle registration = " + registration + ", Vehicle manufacturer = " + manufacturer + ", Number of seats = " + numberOfSeats );
    }


    //test harness
    public static void main(String[] args) {
        Car car1 = new Car("ln13 56","ford" , 4);
        System.out.println(car1);
        System.out.println(car1.calculateBasicTripCost());
    }
}
