import javax.swing.*;

public class CustomerAccount implements Comparable<CustomerAccount>{
    //Attribute definitions
    private String firstName, lastName;
    public  int accountBalance;
    public Vehicle vehicle;
    private double discount;

    //Constructor
    public CustomerAccount(String firstName, String lastName, int accountBalance, Vehicle vehicle){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.vehicle = vehicle;
        this.discount = 1; // Used as a multiplier which will be multiplied by the price
    }


    public String toString(){
        return String.format("\nFirst name = " + firstName + ", Last name = " + lastName + ", Account balance = " + accountBalance +", "+vehicle.toString() + ", Discount multiplier = " + discount);
    }

    public void activateStaffDiscount(){
        this.discount = 0.5;
    }

    public void activateFriendsAndFamilyDiscount(){
        this.discount = 0.9;
    }

    public void deactivateDiscount(){this.discount = 1;}

    public void addFunds(int amount){
        this.accountBalance = this.accountBalance + amount;
    }

    public int makeTrip() throws InsufficientAccountBalanceException, Exception {
        //creates unrounded total based on vehicle information
        int baseTotal = vehicle.calculateBasicTripCost();
        //applies the discount the customer has and rounds the basetotal
        double unroundedFinalTotal = baseTotal * discount;
        int finalTotal = (int) Math.round(unroundedFinalTotal);

            if (accountBalance - finalTotal >= 0) {
                //takes away the cost from their account balance
                accountBalance = accountBalance - finalTotal;
                return finalTotal;
            }
            //if they do not have enough, an exception is thrown
            throw new InsufficientAccountBalanceException();




    }
    @Override
    public int compareTo(CustomerAccount o) {
        int value = this.vehicle.registration.compareTo(o.vehicle.registration);
        if(value > 0 ){
            return 1;
        } else if (value == 0) {
            return 0;
        }
        else{
            return -1;
        }

    }
    //accessor statements
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getAccountBalance(){
        return this.accountBalance;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public double getDiscount(){
        return this.discount;
    }

    //Test harness
    public static void main(String[] args) throws Exception, InsufficientAccountBalanceException {
        Van van1 = new Van("HQ09WIJ","Ford",700);
        CustomerAccount acc1 = new CustomerAccount("Jose","Phelps", 6,new Van("HQ09WIJ","Ford",700));
        //Checking that accounts are made properly and that the main trip methods functions as it should.
        System.out.println("final cost: " + acc1.makeTrip());
        Van van2 = new Van("BQ58VCQ","Ford",450);
        CustomerAccount acc2 = new CustomerAccount("Alden", "Fraga",2,van2);
        //Making sure the compareTo method works
        System.out.println(acc1.toString());
    }

}
