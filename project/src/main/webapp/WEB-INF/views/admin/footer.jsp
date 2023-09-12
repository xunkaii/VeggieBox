<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<fmt:requestEncoding value="utf-8"/>
<c:set var="path" 
   value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2023</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">로그아웃을 하고 로그인 페이지로 이동합니다.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" href="${path}/logout.do">로그아웃</a>
                </div>
            </div>
        </div>
    </div>
    
	<script type="text/javascript">
		function goMemDetail(id){
			location.href="${path}/ADmemDetail.do?id="+id
		}
		function goSellerDetail(id){
			location.href="${path}/ADsellDetail.do?id="+id
		} 
		
		
	</script>
    
    <!-- Bootstrap core JavaScript-->
    <%-- <script src="${path}/vgbox_front/admin/js/jquery.min.js"></script> --%>
    <script src="${path}/vgbox_front/admin/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/vgbox_front/admin/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/vgbox_front/admin/js/sb-admin-2.min.js"></script>
            <!-- Page level plugins -->
    <script src="${path}/vgbox_front/admin/js/Chart.min.js"></script>

    <!-- Page level custom scripts 
    <script src="${path}/vgbox_front/admin/js/demo/chart-area-demo.js"></script>
    <script src="${path}/vgbox_front/admin/js/demo/chart-pie-demo.js"></script>
	-->    


