package jpabook.jpashop.domain;

import jpabook.jpashop.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //일대다, mappedBy : member에 의해 매핑되는 거울에 해당
    private List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static Member createMember(String userId, String password, String name, Address address) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(password);
        member.setName(name);
        member.setAddress(address);

        return member;
    }
}
