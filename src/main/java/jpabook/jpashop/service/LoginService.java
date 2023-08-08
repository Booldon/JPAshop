package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.LoginFailException;
import jpabook.jpashop.form.LoginForm;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;


    public Member login(LoginForm loginForm) {
        Optional<Member> findMember = memberRepository.findByMemberId(loginForm.getUserId());
        if(!findMember.orElseThrow(()->new LoginFailException("해당 아이디가 존재하지 않음")).checkPassword(loginForm.getPassword())) {
            throw new LoginFailException("아이디와 비밀번호가 일치하지 않음");
        }
        return findMember.get();
    }


}
