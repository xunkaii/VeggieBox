package vgbox.vgboxController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.java_sdk.exceptions.CoolsmsException;
import vgbox.vgboxDB.VO.Member;
import vgbox.vgboxDB.VO.Seller;
import vgbox.vgboxService.MemberService;

@Controller
public class MemberController {
	@Autowired
	public MemberService service;
	
	// 아이디 중복 체크
	@RequestMapping("checkId.do")
	public ResponseEntity<Boolean> checkId(@RequestParam(value = "choice", defaultValue = "") String choice,
	                       @RequestParam(value = "id", defaultValue = "") String id) {
	    
	    Boolean checkid = false;
	        
	    if (choice.equals("user")) {
	        checkid = service.checkID("user", id);
	    } else if (choice.equals("seller")) {
	        checkid = service.checkID("seller", id);
	    }
	    
	    return ResponseEntity.ok(checkid);
	}
	    
	 // http://localhost:5020/project/memReg.do
	 // 회원가입
	    @RequestMapping("memReg.do")
	    public String memReg(@RequestParam(value = "choice", defaultValue = "") String choice, Member m, Seller s, Model d) {
	        
	        d.addAttribute("regYN", service.join(choice, m, s));
     
	       return "WEB-INF\\views\\login_join\\register.jsp";
	    }
	    
	    
	    // http://localhost:5020/project/login.do
	    @GetMapping("login.do")
	    public String loginPage() {
	    	return "WEB-INF\\views\\login_join\\login.jsp";
	    }
	    
	    @RequestMapping("modal.do")
	    public String modal() {
	    	return "WEB-INF\\views\\login_join\\modal.jsp";
	    }
	    
	    
	    // 로그인 기능
	    @ResponseBody
	    @PostMapping("login.do")
	    public String memLogin(HttpServletRequest request, 
	    		@RequestParam(value = "choice", defaultValue = "") 
	    		String choice, Member m, Seller s, Model model) {
	        
	        HttpSession session = request.getSession();
	        
	        if(choice.equals("user")) {
	            Member mvo = service.login(m.getId(), m.getPassword());
	            if(mvo != null) {
	            	if(mvo.getAuth().equals("회원")) {
	            		session.setAttribute("session", mvo);
	            		session.setAttribute("auth", "회원");
			            session.setMaxInactiveInterval(1800);
			            return "success";
	            	}
	            	if(mvo.getAuth().equals("관리자")) {
	            		session.setAttribute("session", mvo);
	            		session.setAttribute("auth", "관리자");
	            		session.setMaxInactiveInterval(1800);
	            		return "success";
	            	}
	            	
	            }
	        } else if(choice.equals("seller")) {
	            Seller svo = service.loginS(s.getId(), s.getPassword());
	            if(svo != null) {
	            	if(svo.getAuth().equals("회원")) {
	            		session.setAttribute("session", svo);
	            		session.setAttribute("auth", "회원");
			            session.setMaxInactiveInterval(1800);
			            return "success";
	            	}
	            	if(svo.getAuth().equals("판매자")) {
	            		session.setAttribute("session", svo);
	            		session.setAttribute("auth", "판매자");
	            		session.setMaxInactiveInterval(1800);
	            		return "success";
	            	}
	            }
	        }
	        return "fail";
	    }
	    
	    // 로그아웃
	    @RequestMapping("logout.do")
	    public String logout(HttpSession session) {
	    	
	    	session.invalidate();
	    	
	    	return "redirect:/login.do";
	    }
	    
	    @PostMapping("checkSession.do")
	    public ResponseEntity<String> checkSession(HttpServletRequest request) {
	        HttpSession session = request.getSession();
	        String auth = (String) session.getAttribute("auth");
	        return ResponseEntity.ok(auth);
	    }
	    
	    // 아이디 찾기
	    @RequestMapping("forgotID.do")
	    public String forgotID(@RequestParam(value="choice",defaultValue="") String choice,
	    						Member m, Seller s, Model d) { 	
	    	d.addAttribute("id", service.findID(choice, m, s)); 	
	    	return "WEB-INF\\views\\login_join\\login.jsp";
	    }    
	    // 비밀번호 찾기
	    @RequestMapping("forgotPWD.do")
	    public String forgotPWD(@RequestParam(value="choice",defaultValue="") String choice,
	    						Member m, Seller s, Model d) {    	   	
	    	d.addAttribute("pass", service.findPwd(choice, m, s));    	
	    	return "WEB-INF\\views\\login_join\\login.jsp";
	    }
	    
	    // 휴대폰 인증
	    @PostMapping("phoneCheck.do")
	    @ResponseBody
	    public String phoneCheck(@RequestParam(value="to") String to) throws CoolsmsException {
	    		
	    	return service.PhoneNumberCheck(to);
	    }
	    
}

	


		
