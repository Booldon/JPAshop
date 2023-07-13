package jpabook.jpashop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CartLineDto {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private int orderCount;
    private int stockQuantity;

    private  int totalPrice;

    public CartLineDto(Long itemId, String itemName, int itemPrice, int orderCount, int stockQuantity, int totalPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderCount = orderCount;
        this.stockQuantity = stockQuantity;
        this.totalPrice = totalPrice;
    }


}
