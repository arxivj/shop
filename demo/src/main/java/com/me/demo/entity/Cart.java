package com.me.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity{

    @Id // 테이블의 기본키에 사용할 속성을 지정
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 키 값을 생성하는 전략 명시
    private Long id;

//    @OneToOne(fetch = FetchType.EAGER) // @OneToOne에 따로 옵션을 주지 않으면 기본으로 설정되는 타입
    @OneToOne(fetch = FetchType.LAZY) // @OneToOne 어노테이션을 이용해 회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "member_id") // @JoinColumn 어노테이션을 이용해 매핑할 외래키를 지정
    private Member member;

}
