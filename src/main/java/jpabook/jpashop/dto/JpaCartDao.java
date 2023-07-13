package jpabook.jpashop.dto;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaCartDao implements CartDao {
    private final EntityManager em;

    public JpaCartDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<CartLineDto> getCartLineListInCartPage(Long memberId) {
        List<CartLineDto> cartLineDtoList = em
                .createQuery("select new jpabook.jpashop.dto.CartLineDto(i.id, i.name, i.price, " +
                        "cl.orderCount, i.stockQuantity, (i.price * cl.orderCount) AS totalPrice) " +
                        "from Cart c " +
                        "join c.cart cl " +
                        "join Item i " +
                        "on cl.itemId = i.id " +
                        "where c.member.id = :memberId", CartLineDto.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return cartLineDtoList;
    }
}
