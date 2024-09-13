public class InsufficientAccountBalanceException extends Throwable {

    //Constructor which sets what the exception should say
    public InsufficientAccountBalanceException() throws Exception {
        throw new Exception("Insufficient account balance");
    }

    //test harness
    public static void main(String[] args) throws InsufficientAccountBalanceException, Exception {
        throw new InsufficientAccountBalanceException();
    }
}