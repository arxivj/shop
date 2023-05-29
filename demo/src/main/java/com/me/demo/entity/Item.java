package com.me.demo.entity;

import com.me.demo.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity // 클래스를 엔티티로 선언
@Table(name="item") // 엔티티와 매핑할 테이블을 지정
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

        @Id // 테이블의 기본키에 사용할 속성을 지정
        @Column(name="item_id")
        @GeneratedValue(strategy = GenerationType.AUTO) // 키 값을 생성하는 전략 명시
        private Long id;

        @Column(nullable = false, length = 50)
        private String itemNm;

        @Column(name="price", nullable = false)
        private int price;

        @Column(nullable = false)
        private int stockNumber;

        @Lob //@Lob은 BLOB, CLOB 타입 매핑
        @Column(nullable = false)
        private String itemDetail;

        @Enumerated(EnumType.STRING) //enum 타입 매핑
        private ItemSellStatus itemSellStatus;

}
