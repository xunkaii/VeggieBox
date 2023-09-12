package vgbox.vgboxController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// springweb.vgboxController.AdminController
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vgbox.vgboxDB.VO.FileVo;
import vgbox.vgboxDB.VO.Membership;
import vgbox.vgboxDB.VO.Product;
import vgbox.vgboxDB.VO.Seller;
import vgbox.vgboxService.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService service;
	
	// 회원 관리
	@RequestMapping("ADmemList.do")
	public String ADmemList(Model d) {
		d.addAttribute("memList", service.ADmemList());
		return "WEB-INF\\views\\admin\\memManage.jsp";
	}
	@RequestMapping("ADmemDetail.do")
	public String ADmemDetail(@RequestParam("id") String id,Model d) {
		d.addAttribute("mem", service.ADmemDetail(id));
		return "WEB-INF\\views\\admin\\memDetail.jsp";
	}
	@RequestMapping("ADdeleteMem.do")
	public String ADdeleteMem(@RequestParam("id")String id,Model d) {
		d.addAttribute("proc", "fail");
		service.ADdeleteMem(id);
		// 삭제 후, 해당 아이디 검색 시 정보가 없다면 proc = del 로 삭제완료.
		if(service.ADmemDetail(id)==null) d.addAttribute("proc", "del");		
		return "WEB-INF\\views\\admin\\memDetail.jsp";
	}
	
	
	// 판매자 관리
	@RequestMapping("ADsellerList.do")
	public String ADsellerList(Model d) {
		d.addAttribute("sellerList", service.ADsellerList());
		return "WEB-INF\\views\\admin\\sellerManage.jsp";
	}
	@RequestMapping("ADsellDetail.do")
	public String ADsellDetail(@RequestParam("id")String id,Model d) {
		d.addAttribute("seller", service.ADsellDetail(id));
		return "WEB-INF\\views\\admin\\sellerDetail.jsp";
	}
	@RequestMapping("ADsellerUpdate.do")
	public String ADsellerUpdate(Seller s,Model d) {
		service.ADupdateSeller(s);
		d.addAttribute("proc", "upt");
		return "WEB-INF\\views\\admin\\sellerDetail.jsp";
	}
	@RequestMapping("ADsellerRegist.do")
	public String ADsellerRegist(Seller s,Model d) {
		if(s.getId()!=null&&!s.getId().equals("")) {
			service.ADinsertSeller(s);
			d.addAttribute("proc", "ins");
		}
		return "WEB-INF\\views\\admin\\sellerRegist.jsp";
	}
	@RequestMapping("ADsellerDelete.do")
	public String ADsellerDelete(@RequestParam("id")String id, Model d) {
		service.ADsellerDelete(id);
		d.addAttribute("proc", "del");
		return "WEB-INF\\views\\admin\\sellerDetail.jsp";
	}
	
	// 멤버쉽 관리
	@RequestMapping("ADmemship.do")
	public String ADmemship() {
		return "WEB-INF\\views\\admin\\membership.jsp";
	}
	@RequestMapping("ADmemshipList.do")
	public ResponseEntity<List<Membership>> ADmemshipList() {
		return ResponseEntity.ok(service.getMemshipList());
	}
	@ResponseBody
	@RequestMapping("ADuptMemship.do")
	public ResponseEntity<String> ADuptMemship(Membership ms) {
		return ResponseEntity.ok(service.ADuptMemship(ms));
	}
	// ADuptMemship.do?product_num=1010&mems_begin_date=2018-08-01&mems_end_date=2019-08-01
	
	// 상품등록 페이지
	@RequestMapping("ADprodRegist.do")
	public String prodRegist(Model d) {
		d.addAttribute("sellerId", service.sellerList());
		return "WEB-INF\\views\\admin\\prodRegist.jsp";
	}
	
	// ADinsertProd.do?id=nhw1234&product_name=나주배&price=18900&for_sale=1
	// 상품 등록
	@RequestMapping("ADinsertProd.do")
	public String ADinsertProd(Product prod,Model d) {
		d.addAttribute("ins",service.ADinsertProd(prod));
		return "WEB-INF\\views\\admin\\prodRegist.jsp";
	}
	@RequestMapping("ADuploadImg.do")
	public String ADuploadImg(Product prod,FileVo vo,Model d) {
		if(prod.getId()!=null&&!prod.getId().equals("")) {
			int prdNo = service.ADinsertProd(prod);
			if(prdNo>0) {
				d.addAttribute("ins",service.uploadPrdImg(vo,prdNo));
			} else { 
				d.addAttribute("ins", "등록실패");
			}
			
		}
		
		return "WEB-INF\\views\\admin\\prodRegist.jsp";
	}
	
	/*
	@RequestMapping("insertImg.do")
	public String uploadPrdImg(FileVo vo,Model d){
		d.addAttribute("msg", service.uploadBlob(vo));
		return "WEB-INF\\views\\admin\\prodRegist.jsp";
	}
	*/
	
	
	// 메인 페이지
	@RequestMapping("ADmain.do")
	public String ADmain(Model msSeller,Model seller,Model member,Model monthly,
						Model cmb, Model cyb, Model scr) {
		msSeller.addAttribute("msProduct", service.msProduct());
		seller.addAttribute("normProduct", (service.allProduct()-service.msProduct()));
		member.addAttribute("member", service.allMember());
		monthly.addAttribute("monthly", service.monthlyBenefit());
		cmb.addAttribute("cMonthBenefit",service.currentMonthBenefit());
		cyb.addAttribute("cYearBenefit",service.currentYearBenefit());
		scr.addAttribute("scr", service.shipCountRatio());
		
		return "WEB-INF\\views\\admin\\ADmain.jsp";
	}
}
 