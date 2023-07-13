package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Cart;
import jpabook.jpashop.domain.CartLine;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }

    public Cart findOne(Long id) {
        return em.find(Cart.class, id);
    }

    public List<Cart> findById(Long memberId) {
        return em.createQuery("select c from Cart c where c.member.id = :memberId", Cart.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

    public List<Cart> findAll() {
        return em.createQuery("select c from Cart c", Cart.class)
                .getResultList();
    }

    public List<CartLine> findAllCartLine(Long cartId) {
        return em.createQuery("select cI from Cart c join c.cart cI where c.id  = :cartId",CartLine.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }
}
