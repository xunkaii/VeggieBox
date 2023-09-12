package springweb.a05_mvcexp.a02_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.a05_mvcexp.a03_dao.A01_MemberDao;
import springweb.a05_mvcexp.z01_vo.Dept;
import springweb.a05_mvcexp.z01_vo.Emp;
import springweb.a05_mvcexp.z01_vo.Employee;
import springweb.a05_mvcexp.z01_vo.Job;
import springweb.a05_mvcexp.z01_vo.Location;
import springweb.a05_mvcexp.z01_vo.Member;
import springweb.a05_mvcexp.z01_vo.Student;

@Service
public class A01_MemberService {
	@Autowired
	private A01_MemberDao dao; // 인터페이스 = new 실제 객체()
	
	public List<Member> memberList(Member sch){ 
		if(sch.getId()==null) sch.setId("");
		if(sch.getName()==null) sch.setName("");
		return dao.memberList(sch);
	}
	
	public void daoexp() {
		System.out.println("# Dao 연습 #");
		System.out.println("첫 번째 연습:"+dao.exp01());
		System.out.println("두 번째 연습:"+dao.exp02());
		System.out.println("세 번째 연습:"+dao.exp03());
		System.out.println("연습04: "+dao.exp04("KING"));
		System.out.println("연습05: "+dao.exp05(7844));
		System.out.println("연습06: "+dao.exp06("CLARK"));
		System.out.println("연습07: "+dao.exp07(7844));
		/*
		 * System.out.println("연습08 입력"); dao.exp08Ins(new Dept(12,"재무","대전"));
		 */
		
		
			
	
	}
	
	public void daoexp02() {
		System.out.println("연습09 입력");
		dao.exp09Ins(new Student(25,"홍길동",90,80,100));
	}
	
	public void daoexp03() {
		System.out.println("location 입력");
		dao.exp10Ins(new Location(1000,"강남구 역삼동","2433","서울","대한민국","KR"));
	}
	
	public void empList() {
		System.out.println("Emp 리스트 조회");
		System.out.println("사원번호\t사원명\t직책명\t급여\t관리자번호\t부서번호");
		for(Emp e : dao.elist(new Emp("A",1000,3000))) {
			System.out.print(e.getEmpno()+"\t");
			System.out.print(e.getEname()+"\t");
			System.out.print(e.getJob()+"\t");
			System.out.print(e.getSal()+"\t");
			System.out.print(e.getMgr()+"\t");
			System.out.println(e.getDeptno());
		}
		System.out.println("연습12 emp 조회");
		Map<String,String> sch = new HashMap<String,String>();
		sch.put("ename_sch","A");
		sch.put("job_sch","ER");
		
		for(Emp e:dao.exp12EmpList(sch)){
			System.out.print(e.getEname()+"\t");
			System.out.print(e.getJob()+"\n");
		}
		
		System.out.println("연습13 emp 리스트 조회");
		Map<String,String> sch1 = new HashMap<String,String>();
		sch1.put("job01", "CLERK");
		sch1.put("job02", "MANAGER");
		
		for(Emp e:dao.exp13EmpList(sch1)) {
			System.out.print(e.getEmpno()+"\t");
			System.out.print(e.getEname()+"\t");
			System.out.print(e.getJob()+"\t");
			System.out.print(e.getSal()+"\n");
		}
		System.out.println("연습14 dept01 입력");
		dao.exp14DeptInsert(22,"교육","부산");	
		
		System.out.println("연습15 사원입사일로 검색");
		for(Emp emp:dao.exp15EmpList("1981-01-01","1981-12-31")){
			System.out.print(emp.getEname()+"\t");
			System.out.print(emp.getHiredate()+"\n");
		}
		
		System.out.println("연습16 학생 평균 검색");
		for(Student stu : dao.exp16StuList("오", 50, 100)) {
			System.out.print(stu.getStuno()+"\t");
			System.out.print(stu.getStuname()+"\t");
			System.out.print(stu.getKor()+"\t");
			System.out.print(stu.getEng()+"\t");
			System.out.print(stu.getMath()+"\n");
		}
		
		Employee emp = dao.getEmployee(100);
		System.out.print(emp.getEmployee_id()+"\t");
		System.out.print(emp.getFirst_name()+"\t");
		System.out.print(emp.getEmail()+"\n");
		
		Job j = dao.getJob("AD_PRES");
		System.out.print(j.getJob_id()+"\t");
		System.out.print(j.getJob_title()+"\t");
		System.out.print(j.getMin_salary()+"\t");
		System.out.print(j.getMax_salary()+"\n");
		
		
		for(String name : dao.exp19getEnames(1000,3000)){
			System.out.println(name);
		}
		
		System.out.println("연습20 country_id 확인");
		for(String country : dao.exp20getLocations("US")) {
			System.out.println(country);
		}
		System.out.println("연습21 resultMap");
		for(Emp e : dao.getEmpResultExp()) {
			System.out.print(e.getEmpno()+"\t");
			System.out.print(e.getEname()+"\t");
			System.out.print(e.getSal()+"\n");
		}
		
		System.out.println("연습22 resultMap Dept");
		for(Dept d : dao.getDeptResultExp()) {
			System.out.print(d.getDeptno()+"\t");
			System.out.print(d.getDname()+"\t");
			System.out.print(d.getLoc()+"\n");
		}
		
	}
}
