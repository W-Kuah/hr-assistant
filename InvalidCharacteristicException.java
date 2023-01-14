public class InvalidCharacteristicException extends Exception {

    public InvalidCharacteristicException() {
        super("InvalidCharacteristicException");
    }

    public void jobError(String row) {
        System.out.println("WARNING: invalid characteristic in jobs file in line " + row);
    }
    
    public void applicationError(String row) {
        System.out.println("WARNING: invalid characteristic in applications file in line " + row);
    }



}
