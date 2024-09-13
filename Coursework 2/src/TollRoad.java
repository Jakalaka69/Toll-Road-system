import java.util.ArrayList;



public class TollRoad {

    //Attribute definitions
    public int moneyMade;
    public ArrayList<CustomerAccount> accounts;

    public TollRoad(){
        this.moneyMade = 0;
        this.accounts = new ArrayList<>(); // will be used to store all the customer accounts
        // I have chosen to use an arraylist over an array because although in this coursework there is a set amount of customers, in a real life situation
        //The amount of customers and therefore the size of the array would change, however i would use a normal array for a set amount because it is quicker to read
    }

    //used to add each customer in the file to the accounts array list
    public void addCustomer(CustomerAccount acc){
        this.accounts.add(acc);
    }


    //given a reg number, it will find if a custoemr is in the file, if not, it will throw and exception
    public CustomerAccount findCustomer(String regNum) throws Exception {
        for(int i = 0; i < accounts.size()-1;i++ ){
            if(accounts.get(i).getVehicle().registration == regNum){
                return accounts.get(i);
            }

        }
        throw new CustomerNotFoundException();
    }
    //toString method to clearly show the money made
    @Override
    public String toString() {
        return String.format("Money made = " + moneyMade + "\n, Accounts = "+ accounts);
    }

    //
    public void chargeCustomer(String registrationNumber) throws Exception, InsufficientAccountBalanceException {
        //Finds the account and then makes the trip they require.
        CustomerAccount eventCustomer = findCustomer(registrationNumber);
        int cost = eventCustomer.makeTrip();
        moneyMade = moneyMade + cost;
    }


}
