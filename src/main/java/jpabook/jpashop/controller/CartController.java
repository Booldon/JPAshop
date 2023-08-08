package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.CartLineDto;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.CartService;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;

    private final OrderService orderService;

//    @GetMapping("/carts")
//    public String createCartForm(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members",members);
//        return "cart/createCartForm";
//    }

//    @PostMapping("/carts")
//    public String createCart(@RequestParam("memberId") Long memberId) {
//        cartService.Cart(memberId);
//        return "redirect:/carts";
//    }

    @GetMapping("/cart")
    public String Cart(@RequestParam("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        List<Item> items = itemService.findItems();
        List<CartLineDto> cartLineDtoList = cartService.getCartInCartPage(memberId);

        model.addAttribute("member", member);
        model.addAttribute("items", items);
        model.addAttribute("cartLines", cartLineDtoList);

        return "cart/cartOrder";
    }

    @PostMapping("/cart")
    public String AddItem(@RequestParam("memberId") Long memberId,
                          @RequestParam("itemId") Long itemId,
                          @RequestParam("orderCount") Integer count) {

        cartService.addItemToCart(memberId,itemId,count);
        return "redirect:/cart?memberId=" + memberId;

    }

    @PostMapping("/cart/{memberId}/delete/{itemId}")
    public String DeleteItem(@PathVariable ("memberId") Long memberId,
                             @PathVariable ("itemId") Long itemId){

        cartService.removeCartLine(memberId,itemId);
        return "redirect:/cart?memberId=" + memberId;

    }

    @PostMapping("/cart/{memberId}/cartOrder")
    public String CartOrder(@PathVariable ("memberId") Long memberId,
                            @ModelAttribute ("orderSearch") OrderSearch orderSearch, Model model) {
        cartService.cartOrder(memberId);
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "redirect:/orders";

    }


}
