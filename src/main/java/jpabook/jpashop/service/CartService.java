package jpabook.jpashop.service;

import jpabook.jpashop.domain.Cart;
import jpabook.jpashop.domain.CartLine;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.CartLineDto;
import jpabook.jpashop.dto.JpaCartDao;
import jpabook.jpashop.exception.CartDuplicationException;
import jpabook.jpashop.repository.CartRepository;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private final OrderService orderService;
    private final JpaCartDao jpaCartDao;
    private CartLineDto cartLineDto;


    public void cart(Member member) {
        validateDuplicateCart(member.getId());
        Cart cart = new Cart(member);
        cartRepository.save(cart);
    }

    private void validateDuplicateCart(Long memberId) {
        //EXCEPTION
        List<Cart> findCarts = cartRepository.findById(memberId);
        if (!findCarts.isEmpty()) {
            throw new CartDuplicationException("해당 Member의 Cart가 이미 존재함");
            }

        }

    public void addItemToCart(Long memberId, Long orderItemId, Integer orderCount) {

        Cart cart = findCart(memberId);

        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for member ID: " + memberId);
        }
        CartLine cartLine = new CartLine(orderItemId, orderCount);

        cart.addItemToCart(cartLine);
    }

    public void modifyOrderCount(Long memberId, Long orderItemId, Integer orderCount) {
        Cart cart = findCart(memberId);

        CartLine cartLine = new CartLine(orderItemId, orderCount);
        cart.modifyOrderCount(cartLine);
    }

    public void removeCartLine(Long memberId, Long itemId) {
        Cart cart = findCart(memberId);
        cart.removeCartLine(itemId);
    }

    public List<CartLine> showCart(Long memberId) {
        Cart cart = findCart(memberId);
        return cartRepository.findAllCartLine(cart.getId());

    }

    public void cartOrder(Long memberId) {
        Cart cart = findCart(memberId);
        Map<Long, CartLine> cartLines = cart.getCart();

        Iterator<CartLine> iterator = cartLines.values().iterator();
        while (iterator.hasNext()) { //일괄 선택
            CartLine cartLine = iterator.next();
            Long itemId = cartLine.getItemId();
            Integer orderCount = cartLine.getOrderCount();

            orderService.order(memberId, itemId, orderCount); //일괄 주문
            iterator.remove();
        }
    }

    public List<CartLineDto> getCartInCartPage (Long memberId) {

        List<CartLineDto> cartList = jpaCartDao.getCartLineListInCartPage(memberId);

        return cartList;
    }

    public Cart findCart(Long memberId){
        List<Cart> cartList = cartRepository.findById(memberId);
        Cart cart = null;
        if (!cartList.isEmpty()) {
            cart = cartList.get(0);
        }
        return cart;
    }

}

