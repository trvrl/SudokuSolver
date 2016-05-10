package model.Exceptions;

/**
 * Created by Trevor on 5/4/2016.
 */
public class UnsolveableException extends Exception {

    public UnsolveableException() {
        super("This board cannot be solved!");
    }

    public UnsolveableException(String msg) {
        super(msg);
    }

}
