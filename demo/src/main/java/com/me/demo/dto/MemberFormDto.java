package com.me.demo.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    //@NotBlank : Null 체크 및 문자열의 경우 길이 0 및 빈 문자열(" ") 검사
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    //@NotEmpty : Null 체크 및 문자열의 경우 길이 0인지 검사
    //@Email : 이메일 형식인지 검사
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    //@Length(min=, max=) : 최소, 최대 길이 검사
    @NotEmpty(message = "")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

}
