public class InvalidDataFormatException extends ArrayIndexOutOfBoundsException {

    public InvalidDataFormatException() {
        super("InvalidDataFormatException");
    }

    public void jobError(String row) {
        System.out.println("WARNING: invalid data format in jobs file in line " + row);
    }
    
    public void applicationError(String row) {
        System.out.println("WARNING: invalid data format in applications file in line " + row);
    }




}
