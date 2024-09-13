public class CustomerNotFoundException extends Exception {

    //Constructor which sets what the exception should say
    public CustomerNotFoundException() throws Exception {
        throw new Exception("Customer not found");
    }
    //test harness
    public static void main(String[] args) throws Exception {
        throw new CustomerNotFoundException();
    }

}
