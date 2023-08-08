package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository //스프링 빈으로 자동 등록
@RequiredArgsConstructor //스프링 부트에서 자동으로 @Autowired를 @PersistenceContext로 자동 등록
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
    public Optional<Member> findByMemberId(String userId){
        return em.createQuery("select m from Member m where m.userId =: userId",Member.class)
                .setParameter("userId",userId)
                .getResultList().stream().findAny();
    }

}
