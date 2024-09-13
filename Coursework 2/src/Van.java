//Creates a new class that is a subclass of vehicle
public class Van extends Vehicle{

    //Declaring variables

    private int payload;
    public Van (String registration, String manufacturer, int payload){
        super(registration, manufacturer);

        this.payload = payload;
    }

    //Overrides the abstract method in the vehicle class in order to
    //calculate the trip cost based on the weight of payload the truck has
    @Override
    public int calculateBasicTripCost(){
        if(payload <= 600){
            return 500;
        }
        else if(payload > 600 && payload <= 800 )  {
            return 750;
        }
        else{
            return 1000;
        }
    }

    //Accessor method for the new attribute
    public int getPayload(){
        return this.payload;
    }

    //Converts all the information about the van into a readable string
    public String toString(){
        return String.format("Vehicle registration = " + registration + ", Vehicle manufacturer = " + manufacturer + ", Payload = " + payload );
    }

    //test harness
    public static void main(String[] args) {
        Van van1 = new Van("AM56 P5L", "BMW", 700);
        System.out.println(van1);
        System.out.println(van1.calculateBasicTripCost());
    }
}
