package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register");
    }

    // 회원가입 처리
    @PostMapping("/register")
    public ModelAndView register(MemberDto memberDto) {
        try {
            memberService.register(memberDto);
            return new ModelAndView("redirect:/login");
        } catch (IllegalStateException e) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("error", e.getMessage());
            return mav;  // 중복 아이디가 있을 경우 에러 메시지 전달
        }
    }

    // 로그인 페이지
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    // 로그인 처리
    @PostMapping("/login")
    public ModelAndView login(MemberDto memberDto, HttpSession session) {
        try {
            Member member = memberService.login(memberDto);
            session.setAttribute("member", member);
            return new ModelAndView("redirect:/transaction");
        } catch (PetMilyException e) {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("error", e.getErrorCode().getMessage()); // 에러 메시지 전달
            return mav;
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }
}