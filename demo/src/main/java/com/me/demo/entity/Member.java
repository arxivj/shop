package com.me.demo.entity;


import com.me.demo.constant.Role;
import com.me.demo.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 방법 1. service에서 비지니스 로직을 가지는 것
    // 방법 2. Entity 자체에서 비지니스 로직을 가지는 것
    // 두 방법 중 Entity 자체에 필드를 관리하는 메서드를 두는 것이 응집력이 좋음
    // 이러한 방식을 '도메인 모델 패턴' 이라고 한다 (service계층은 단순히 요청을 위임하는 역할만 한다)
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;

    }

}
