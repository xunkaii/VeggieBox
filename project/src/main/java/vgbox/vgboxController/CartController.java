package vgbox.vgboxController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vgbox.vgboxDB.VO.Cart;
import vgbox.vgboxService.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	// 장바구니 조회
	@RequestMapping("cartList.do")
	public String cartList(@RequestParam(value = "id", defaultValue = "")String id, Model d) {
		d.addAttribute("cartList", service.cartList(id));
		return "WEB-INF\\views\\store\\checkout.jsp";
	}
	// 장바구니 넣기
	@RequestMapping("cartInsert.do")
	public String cartInsert(Cart ins, Model d) {
		if(ins.getId()==null) ins.setId("");
		service.cartInsert(ins);
		d.addAttribute("proc", "ins");
		return "WEB-INF\\views\\store\\checkout.jsp";
	}
	// 장바구니 삭제
	@RequestMapping("cartDelete.do")
	public String cartDelete(@RequestParam("cart_num") int cart_num, Model d) {
		service.cartDelete(cart_num);
		d.addAttribute("proc", "del");
		return "WEB-INF\\views\\store\\checkout.jsp";
	}
	
	@RequestMapping("cartDetail.do")
	public String cartDetail(@RequestParam("product_num") int prd_num, Model d) {
		d.addAttribute("cartDetail", service.cartDetail(prd_num));
		return "WEB-INF\\views\\store\\checkout.jsp";
	}
	
}
