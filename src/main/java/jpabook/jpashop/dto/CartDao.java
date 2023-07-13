package jpabook.jpashop.dto;

import java.util.List;

public interface CartDao {
    List<CartLineDto> getCartLineListInCartPage(Long memberId);
}
