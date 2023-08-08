package jpabook.jpashop.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String userId;
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
}
