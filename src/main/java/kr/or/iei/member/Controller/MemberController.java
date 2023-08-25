package kr.or.iei.member.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/header")
	public String header() {
		
		return "member/header_copy";
	}
	@PostMapping(value = "/signin")
	public String logIn(String signId, String signPw, Model model, HttpSession session) {
		
		Member m = memberService.selectOneMember(signId, signPw);
		if (m != null) {
			session.setAttribute("m", m);

			model.addAttribute("title", "로그인 성공");
			model.addAttribute("msg", "로그인에 성공하셨습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");
			
		} else {
			
			model.addAttribute("title", "로그인 실패");
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/");
		}

		return "common/msg";

	}
	/*
	 * @GetMapping(value = "/logout") public String logout(HttpSession session) { //
	 * 현재 세션에 저장되어있는 정보 파기 session.invalidate(); return "redirect:/"; }
	 */

	@GetMapping(value = "/yeojeong_signupFrm")
	public String signupFrm() {
		
		return "member/yeojeong_signupFrm";
	}
	@PostMapping(value="/signup")
	public String signup(Member member, Model model) {
		System.out.println(member.getMemberGender() + member.getBirthDate());
		int result = memberService.insertMember(member);
		if (result > 0) {
			model.addAttribute("title", "회원가입 성공");
			model.addAttribute("msg", "신규 회원가입을 축하합니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");

		} else {

			model.addAttribute("title", "회원가입 실패");
			model.addAttribute("msg", "정보입력 제대로 해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/");
		}
		return "common/msg";
	}
	
	@PostMapping(value = "/checkId")
	public String checkId(String checkId, Model model) {
		Member member = memberService.selectOneMember(checkId);
		model.addAttribute("checkId", checkId);
		if(member == null) {
			model.addAttribute("result", 0);
		}else {
			model.addAttribute("result", 1);
		}
		return "member/checkId";
	}
	
	
}

