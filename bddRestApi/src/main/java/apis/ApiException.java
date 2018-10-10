package apis;

public class ApiException extends RuntimeException {
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
