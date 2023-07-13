package jpabook.jpashop.exception;

public class CartDuplicationException extends RuntimeException{
    public CartDuplicationException() {
    }

    public CartDuplicationException(String message) {
        super(message);
    }

    public CartDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartDuplicationException(Throwable cause) {
        super(cause);
    }
}
