<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8" />
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="stylesheet" href="${path}/vgbox_front/store/css/store.css">
<link rel="stylesheet" href="${path}/vgbox_front/store/css/reset.css">
<link rel="stylesheet" href="${path}/vgbox_front/store/css/global.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
<meta charset="EUC-KR">
<title>VGBOX</title>
</head>
<body>
		<%@include file="header.jsp" %>
		<div class="tab">
			<div class="menu">
				<div class="menuL">
					<ul>
						<li><a href="">구매</a></li>
						<li><a href="">예약</a></li>
					</ul>
				</div>
			</div>
			<div class="menu" style="border-top: solid 1px rgb(219, 217, 217);">
				<div class="menuL">
					<ul>
						<li><a href="">전체보기</a></li>
						<li><a href="">긴급구출&인기상품</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="inner">
				<!-- Section-->
				<section class="py-2">
					<div class="container px-4 px-lg-5 mt-5">
						<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

					<c:forEach var="product" items="${prdList}">
						<div class="col mb-5">
							<div class="card h-100">
								<div class="badge bg-dark text-white position-absolute"
									style="top: 0.5rem; right: 0.5rem">구매가능</div>
								<!-- 상품 이미지-->
								<img class="card-img-top"
									src="${path}/vgbox_front/store/img/product-img/${product.img}"
									style="height: 270px;" alt="..." />
								<!-- 상품 정보 -->
								<div class="card-body p-4">
									<div class="text-center">
										<!-- 상품 이름-->
										<h5 class="fw-bolder">${product.product_name}</h5>
										<!-- 상품 가격 천단위 ,-->
										<fmt:formatNumber value="${product.price}" />
										원
										<p class="text-black-50 p-1">#유기농</p>
										<!-- 상품 평점 -->
										<p class="text-black-50">☆ 4.7(106)</p>
									</div>
								</div>
								<!-- Product actions-->
								<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto"
											onclick="buyprd(${product.product_num})"> 구매하기</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
					</div>
				</section>
		</div>
	</div>
	
	
	<script type="text/javascript">
		function buyprd(product_num) {
			location.href = "${path}/prdDetail.do?product_num=" + product_num;
		}
	</script>
</body>
</html>