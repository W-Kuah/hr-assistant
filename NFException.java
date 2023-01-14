public class NFException extends Exception {

    public NFException() {
        super("NumberFormatException");
    }

    public void jobError(String row) {
        System.out.println("WARNING: invalid number format in jobs file in line " + row);
    }
    
    public void applicationError(String row) {
        System.out.println("WARNING: invalid number format in applications file in line " + row);
    }


}
