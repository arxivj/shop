package com.me.demo.repository;


import com.me.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository는 2개의 제너릭 타입을 사용
// 첫번째에는 '엔티티 타입 클래스', '두번째는 기본키 타입'을 넣어준다
public interface ItemRepository extends JpaRepository<Item, Long> {

    // item 이름으로 조회 (select) 키워드 변수명
    List<Item> findByItemNm(String itemNm);

    // 키워드 변수명 키워드 변수명
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 키워드 변수명 키워드
    List<Item> findByPriceLessThan(Integer price);

    // 키워드 변수명 키워드를  orderby 속성명 desc으로 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


}
