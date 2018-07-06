package exception;

public class SuperClassNotFoundException extends Exception{
    private String errorCode;
    public SuperClassNotFoundException(){ }

    public  SuperClassNotFoundException(String message){
        super(message);
    }

    public  SuperClassNotFoundException(String errorCode,String message){
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
