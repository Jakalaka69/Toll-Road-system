import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

public class TollRoadMain {
    public static TollRoad initialiseTollRoadFromFile() throws IOException {
        //Creates the new tollroad
        TollRoad tollRoad1 = new TollRoad();
        //Creates new directory
        String dir = System.getProperty("user.dir");
        //Creates new input stream based on the desired directory
        InputStream fis = new FileInputStream(dir +"/customerData.txt");
        //Creates a reader based of the input stream
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        //Creates a bufferedreader to increase efficiency
        BufferedReader br = new BufferedReader(isr);
        //Creates a new string which is a long line of the text from the entire file
        String allCustomerInfo = br.readLine();
        //Creates an array of strings each split by a #, seperating each customer.
        String[] tempSplitCustomerInfo = allCustomerInfo.split("#");
        //Converts this string array into an arryalist so that data can be manipulated more easily
        ArrayList<String> splitCustomerInfo = new ArrayList<String>(Arrays.asList(tempSplitCustomerInfo));

        for(int i = 0; i < splitCustomerInfo.size();i++){
            //Repeats the last process done but this time splitting up each element of the customer details instead
            String[] tempmainDetails = splitCustomerInfo.get(i).split(",");
            ArrayList<String> mainDetails = new ArrayList<String>(Arrays.asList(tempmainDetails));
            //Attribute definition based on each element of the customers details
            String firstName = mainDetails.get(2);
            String lastName = mainDetails.get(3);
            String reg = mainDetails.get(1);
            String manufacturer = mainDetails.get(4);
            int vehicleInfo = Integer.parseInt(mainDetails.get(5));
            int accountBalance = Integer.parseInt(mainDetails.get(6));
            CustomerAccount cusToAdd;
            //Finding which vehicle the customer has so that the program can process the vehicleinformation
            if(mainDetails.get(0).equals("Van")){
                cusToAdd = new CustomerAccount(firstName,lastName,accountBalance,(new Van(reg,manufacturer,vehicleInfo)));
            }
            else if(mainDetails.get(0).equals("Truck")){
                cusToAdd = new CustomerAccount(firstName,lastName,accountBalance,(new Truck(reg,manufacturer,vehicleInfo)));
            }
            else if(mainDetails.get(0).equals("Car")){
                cusToAdd = new CustomerAccount(firstName,lastName,accountBalance,(new Car(reg,manufacturer,vehicleInfo)));

            }
            else{
                cusToAdd = null;
            }
            //Reads the string to find which discount the customer should get
            if(mainDetails.get(7).equals("FRIENDS_AND_FAMILY") ){
                cusToAdd.activateFriendsAndFamilyDiscount();
            }
            else if(mainDetails.get(7).equals("STAFF")){
                cusToAdd.activateStaffDiscount();
            }
            //if all goes well customer is added to the accounts array
            tollRoad1.addCustomer(cusToAdd);

        }
        //Returns the finished toll road
        return tollRoad1;


    }

    public static void simulateFromFile(TollRoad road) throws Exception{
        //Same process as when splitting the customer file but this time with the transactions
        String dir2 = System.getProperty("user.dir");
        InputStream fis2 = new FileInputStream(dir2 + "/transactions.txt");
        InputStreamReader isr2 = new InputStreamReader(fis2, Charset.forName("UTF-8"));
        BufferedReader br2 = new BufferedReader(isr2);
        String allTransactions = br2.readLine();
        String[] tempSplitTransactions = allTransactions.split(("\\$"));
        ArrayList<String> splitTransactions = new ArrayList<String>(Arrays.asList(tempSplitTransactions));
        for (int i = 0; i < splitTransactions.size(); i++) {
            //Splits up each element in the transaction
            String[] tempMainTransactions = splitTransactions.get(i).split(",");
            ArrayList<String> mainTransactions = new ArrayList<String>(Arrays.asList(tempMainTransactions));
            //Finding which procedure should be carried out
            if(mainTransactions.get(0).equals("addFunds")){
                boolean found = false;
                //Searches the accounts arraylist,catches the exception if the account does not exist, adds the funds using the addfunds() funciton if it is found
                try {
                    for (int j = 0; j < road.accounts.size() + 1; j++) {
                        CustomerAccount customerToCompare = road.accounts.get(j);

                        if (mainTransactions.get(1).equals(customerToCompare.vehicle.registration)) {
                            customerToCompare.addFunds(Integer.parseInt(mainTransactions.get(2)));
                            System.out.println(customerToCompare.vehicle.registration + ": " + mainTransactions.get(2) + " added successfully");
                            found = true;
                        }


                    }
                }
                catch(Exception e){
                    if(found == false) {
                        System.out.println(mainTransactions.get(1) + ": addFunds failed. CustomerAccount does not exist");
                    }
                    }

            }

            else if(mainTransactions.get(0).equals("makeTrip")){
                boolean found = false;

                        //finds the customer
                        for (int j = 0; j < road.accounts.size(); j++) {
                            CustomerAccount customerToCompare = road.accounts.get(j);

                            //compares each customer to the desired reg number form the transaction file
                            if (mainTransactions.get(1).equals(customerToCompare.vehicle.registration)) {
                                //Tries to charge the customer, if they do not have enough money, it will throw and catch an insufficient funds exception
                                //and then mvoe onto the next transation in the file
                                try {
                                    road.chargeCustomer(customerToCompare.vehicle.registration);
                                } catch (Exception | InsufficientAccountBalanceException e) {
                                    System.out.println(customerToCompare.vehicle.registration + ": makeTrip failed. Insufficient funds");
                                    found = true;
                                    continue;
                                }
                                System.out.println(customerToCompare.vehicle.registration + ": Trip completed successfully");
                                found = true;
                            }


                        }
                        //safety harness for if the customer is not found
                try {
                        if(found == false){
                            throw new CustomerNotFoundException();
                        }
                }
                catch(Exception e){
                        System.out.println(mainTransactions.get(1) + ": makeTrip failed. CustomerAccount does not exist");
                }

            }

        }
    }

    //Main function
    public static void main(String[] args) throws Exception{
        TollRoad road = initialiseTollRoadFromFile();
        simulateFromFile(road);
        System.out.println("Money made: " + road.moneyMade);
    }
}
