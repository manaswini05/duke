package ca.demo.terminal;


/*
    MyException extends the Java Exception class and throws a message.
    @return message the error to be thrown
 */

public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
