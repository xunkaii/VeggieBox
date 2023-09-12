package vgbox.vgboxDB.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import vgbox.vgboxDB.VO.FileVo;
import vgbox.vgboxDB.VO.Member;
import vgbox.vgboxDB.VO.Membership;
import vgbox.vgboxDB.VO.Monthly;
import vgbox.vgboxDB.VO.Product;
import vgbox.vgboxDB.VO.Seller;

public interface AdminDao {
	
	// 회원 조회
	@Select("SELECT * FROM MEMBER_INFORMATION")
	public List<Member> ADmemList();

	// 회원 상세 조회
	@Select("SELECT * FROM MEMBER_INFORMATION WHERE id = #{id}")
	public Member ADmemDetail(@Param("id")String id);
	
	// 회원 삭제
	@Select("DELETE FROM MEMBER_INFORMATION mi WHERE id=#{id}")
	public void ADdeleteMem(@Param("id")String id);
	
	// 판매자 조회
	@Select("SELECT * FROM SELLER_REG_INFO")
	public List<Seller> ADsellerList();
	// 판매자 상세 조회
	@Select("SELECT * FROM SELLER_REG_INFO WHERE id=#{id}")
	public Seller ADsellDetail(@Param("id")String id);
	
	// 판매자 삭제
	@Select("DELETE FROM SELLER_REG_INFO WHERE id=#{id}")
	public void ADsellerDelete(@Param("id")String id);
	public void ADupdateSeller(Seller s);
	public void ADinsertSeller(Seller s);
	
	// 메인페이지
	@Select("SELECT count(*) FROM MEMBERSHIP WHERE MEMS_BEGIN_DATE IS NOT NULL")
	public int msProduct();
	@Select("SELECT count(*) FROM MEMBERSHIP")
	public int allProduct();
	@Select("SELECT count(*) FROM MEMBER_INFORMATION")
	public int allMember();	
	@Select("SELECT nvl(sum(ORDER_PRICE),0) FROM ORDER_INFO WHERE EXTRACT (MONTH FROM sysdate) = EXTRACT (MONTH FROM order_date)")
	public int currentMonthBenefit();
	@Select("SELECT nvl(sum(ORDER_PRICE),0) FROM ORDER_INFO WHERE EXTRACT (year FROM sysdate) = EXTRACT (year FROM order_date)")
	public int currentYearBenefit();
	@Select("SELECT count(*) FROM ORDER_INFO WHERE SHIP_STATE = '배송완료'")
	public int shipCompleteCount();
	@Select("SELECT count(*) FROM ORDER_INFO")
	public int allOrderCount();
	
	public List<Membership> getMemshipList();
	
	public int ADuptMemship(Membership ms);
	
	// 월 별 매출
	public Monthly monthlyBenefit();
	
	// 상품등록
	
	// 상품 번호 생성
	@Select("SELECT prod_seq.nextval FROM dual")
	public int getProdSeq();
	// 상품 등록
	@Insert("INSERT INTO PRODUCT VALUES\r\n"
			+ "	(#{product_num},#{id},#{product_name},#{price},#{for_sale})")
	public int insertPrdInfo(Product info);
	
	
	// 상품 이미지 등록
	@Insert("INSERT INTO PRD_IMG VALUES\r\n"
			+ "	(#{product_num},#{img},#{img_1},#{img_2},#{img_3})")
	public int insertPrdImg(Product prd);
	
	
	
	// 멤버쉽 리스트 상품 추가
	@Insert("INSERT INTO MEMBERSHIP (product_num) VALUES (#{product_num})")
	public int insertMemsPrd(@Param("product_num") int prdNum);
	@Select("SELECT DISTINCT ID FROM SELLER_REG_INFO")
	public List<String> sellerList();
	
	
	
	
}
