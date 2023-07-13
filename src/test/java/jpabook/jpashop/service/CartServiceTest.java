package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.CartRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired EntityManager em;
    @Autowired CartService cartService;
    @Autowired CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 카트생성() {
        //given
        Member member = new Member();
        Cart cart = new Cart(member);
        cart.setMember(member);

        //when

        //then
    }

    @Test
    public void 아이템추가() {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        Cart cart = new Cart(member);

        CartLine cartLine = new CartLine(item.getId(), 5);
        //when
        cartService.addItemToCart(member.getId(),item.getId(),5);

        //then
        assertEquals("cartLine 생성되었나?",cart.getCart().getClass(),cartLine);

    }
    @Test(expected = IllegalArgumentException.class)
    public void 중복_카트_예외() throws Exception {
        //given
        Member member = createMember();

        //when
        cartService.Cart(member.getId());
        cartService.Cart(member.getId());

        //then
        fail("예외가 발생해야 합니다.");
    }


    @Test
    public void 카트아이템생성() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        Cart cart = new Cart(member);
        cartService.Cart(member.getId());

        //when
        cartService.addItemToCart(cart.getId(),item.getId(),5);

        //then
        assertEquals("cartItem",member.getCart().getCart(),member.getCart().getCart());

    }

    @Test
    public void 카트라인조회() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        Cart cart = createCart(member);

        //when
        cartService.showCart(member.getId());

        //then


    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Cart createCart(Member member) {
        Cart cart = new Cart(member);
        em.persist(cart);
        return cart;
    }



}