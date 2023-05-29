package com.me.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem extends BaseEntity{

    // 어떤 테이블에 컬럼이 추가되는지 햇갈릴 수 있는데,
    // @JoinColumn 어노테이션을 사용하는 엔티티에 컬럼이 추가된다고 생각하면 된다
    
    @Id
    @GeneratedValue
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private int count;

}
