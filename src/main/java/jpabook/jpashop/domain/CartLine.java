package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
@Getter @Setter
public class CartLine {
    private Long itemId;
    private Integer orderCount;

    protected CartLine() {
        // 기본 생성자 For Hibernate
    }

    public CartLine(Long itemId, Integer orderCount) {
        this.itemId = itemId;
        this.orderCount = orderCount;
    }
}
