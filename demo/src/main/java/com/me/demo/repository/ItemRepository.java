package com.me.demo.repository;


import com.me.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.persistence.PersistenceContext;
import java.util.List;

// JpaRepository는 2개의 제너릭 타입을 사용
// 첫번째에는 '엔티티 클래스의 타입', '두번째는 기본키 타입'을 넣어준다
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    // item 이름으로 조회 (select) 키워드 변수명
    List<Item> findByItemNm(String itemNm);

    // 키워드 변수명 키워드 변수명
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 키워드 변수명 키워드
    List<Item> findByPriceLessThan(Integer price);

    // 키워드 변수명 키워드를  orderby 속성명 desc으로 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // @Query : 특정 DB에 독립적으로 조회 가능
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // @Query의 NativeQuery : 특정 DB에 종속되는 단점은 존재하지만, 기존 DB에서 사용하던 쿼리를 그대로 사용 가능
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);



}
