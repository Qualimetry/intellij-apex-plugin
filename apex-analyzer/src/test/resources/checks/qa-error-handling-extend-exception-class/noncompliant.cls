public class CustomExceptions {
    public class DataException extends BaseError {
        public DataException(String msg) { }
    }

    public class ApiException extends RuntimeError {
        public ApiException(String msg) { }
    }

    public class ValidException extends Exception {
        public ValidException(String msg) { }
    }
}
