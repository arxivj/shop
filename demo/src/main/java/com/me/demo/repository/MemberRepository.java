package com.me.demo.repository;

import com.me.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository는 2개의 제너릭 타입을 사용
// 첫번째에는 '엔티티 타입 클래스', '두번째는 기본키 타입'을 넣어준다
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

}
