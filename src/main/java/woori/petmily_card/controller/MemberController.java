package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/petmily/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("member/register");
    }

    // 회원가입 처리
    @PostMapping("/register")
    public ModelAndView register(MemberDto memberDto) {
        memberService.register(memberDto);
        return new ModelAndView("redirect:/petmily/members/login");
    }

    // 로그인 페이지
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("member/login");
    }

    // 로그인 처리
    @PostMapping("/login")
    public ModelAndView login(MemberDto memberDto, HttpSession session) {
        Member member = memberService.login(memberDto);
        session.setAttribute("member", member);
        return new ModelAndView("redirect:/petmily/main");
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/petmily/members/login");
    }
}
