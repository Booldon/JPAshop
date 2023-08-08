package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter @Setter
@Slf4j
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "cart_line", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "map_key")
    private Map<Long, CartLine> cart = new HashMap<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;




    // == 연관관계 메서드 == //


//    public void setOrder(Order order) {
//        this.order = order;
//        order.setCart(this);
//    }


    // == 생성 메서드 == //
    protected Cart() {
        // 기본생성자
    }
    public Cart(Member member) {
        this.member = member;
        member.setCart(this);
    }

    // == 비즈니스 모델 == //
    public void addItemToCart(CartLine cartLine) {
        Long itemId = cartLine.getItemId();

        if (cart.containsKey(itemId)) {
            CartLine existCartLine = cart.get(cartLine.getItemId());
            int newOrderCount = existCartLine.getOrderCount() + cartLine.getOrderCount();
            cart.replace(itemId, new CartLine(cartLine.getItemId(), newOrderCount));

        } else {
            cart.put(itemId, cartLine);
        }
        log.info("cartItem : " + cart.get(itemId));
    }

    // 장바구니 아이템 수량 수정
    public void modifyOrderCount(CartLine newCartLine) {
        this.cart.replace(newCartLine.getItemId(), newCartLine);
    }

    // 장바구니 아이템 제거
    public void removeCartLine(Long cartItemId) {
        this.cart.remove(cartItemId);

    }
}
