package jpabook.jpashop.service;

import jpabook.jpashop.domain.Cart;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.CartRepository;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기 전용 최적화 보장
@RequiredArgsConstructor // final 필드만 생성자 인젝션 자동 생성
public class MemberService {

    private final MemberRepository memberRepository; //컴파일시 생성자가 필요하다는 것을 알 수 있기 때문에 final 객체 권장
    private final CartRepository cartRepository;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    } -> setter 인젝션 : setter로 인해 변경이 일어날 수 있는 치명적 단점 존재

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    } ->생성자 인젝션 : setter 인젝션의 단점 보완

    /**
     * 회원가입
     */
    @Transactional //기본적으로 데이터 변경 시에 트랜잭션 안에서 행해야한다.
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
//        Cart cart = Cart.createCart(member);
//        cartRepository.save(cart);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        } // 이렇게 하면 다른 두 클라이언트가 동시에 접근했을 경우 예외처리에 실패한다.
          // name에 유니크 속성 넣어주는 것을 권장
    }

    //회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //개별 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
