package jpabook.jpashop.controller;

import jpabook.jpashop.exception.CartDuplicationException;
import jpabook.jpashop.exception.ItemDeleteException;
import jpabook.jpashop.exception.LoginFailException;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ItemDeleteException.class)
    public String handleItemDeleteException(ItemDeleteException exception, Model model) {
        model.addAttribute("exception",exception);

        return "exception/itemDeleteError";

    }

    @ExceptionHandler(NotEnoughStockException.class)
    public String handleNotEnoughStockException(NotEnoughStockException exception, Model model) {
        model.addAttribute("exception",exception);

        return "exception/NotEnoughStockError";
    }

    @ExceptionHandler(CartDuplicationException.class)
    public String handleCartDuplicationException(CartDuplicationException exception, Model model) {
        model.addAttribute("exception",exception);

        return "exception/CartDuplicationError";
    }

    @ExceptionHandler(LoginFailException.class)
    public String handleLoginFailException(LoginFailException exception, Model model) {
        model.addAttribute("exception",exception);

        return "exception/LoginFailError";
    }


}
