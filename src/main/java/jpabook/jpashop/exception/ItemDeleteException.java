package jpabook.jpashop.exception;

public class ItemDeleteException extends RuntimeException{
    public ItemDeleteException() {
        super();
    }

    public ItemDeleteException(String message) {
        super(message);
    }

    public ItemDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemDeleteException(Throwable cause) {
        super(cause);
    }
}
