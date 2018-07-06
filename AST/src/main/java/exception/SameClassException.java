package exception;

public class SameClassException extends Exception {
    private String errorCode;
    public SameClassException(){ }

    public  SameClassException(String message){
        super(message);
    }

    public  SameClassException(String errorCode,String message){
        super(message);
        this.errorCode=errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
