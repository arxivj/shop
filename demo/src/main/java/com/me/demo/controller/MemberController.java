package com.me.demo.controller;

import com.me.demo.dto.MemberFormDto;
import com.me.demo.entity.Member;
import com.me.demo.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMassage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }


    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";

    }

}


    // 아래 memberFor과 memberForm2는 동일한 경로 및 동일한 요청 방식을 처리하고, 같은 결과를 반환한다
    // 하지만 두번째가 더 직접적인 방식으로 데이터를 받아오고 'memberFormDto' 객체를 생성하는데 비해
    // 첫번째 코드는 스프링 MVC가 자동으로 데이터를 바인딩하고 객체를 생성한다

    /*@GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(MemberFormDto memberFormDto, Model model){
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping(value = "/new")
    public String memberForm2(){
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm2(@RequestParam String email, @RequestParam String password, Model model){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        model.addAttribute("memberFormDto",memberFormDto);
        memberService.saveMember(member);
        return "redirect:/";
    }*/
