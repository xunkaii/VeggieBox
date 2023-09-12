<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>VGBOX</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="${path}/vgbox_front/store/css/core-style.css">
<link rel="stylesheet" href="${path}/vgbox_front/store/css/style.css">
<link rel="stylesheet" href="${path}/vgbox_front/store/css/reset.css">
<link rel="stylesheet" href="${path}/vgbox_front/store/css/global.css">
<meta charset="EUC-KR">
</head>
<script type="text/javascript">
		var proc = "${proc}"
		if(proc == "del") {
			alert("삭제완료")
		}
		var msg = "${msg}"
		if (msg == "구매성공") {
			alert(msg)
			location.href="${path}/prdList.do"
		}
</script>
<body>
    <!-- Search Wrapper Area End -->
	<%@include file="header.jsp" %>
    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">
		<c:set var="mem" value="${session}"/>
        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="amado-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo">
                <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav" style = "margin-top:-130px">
                <ul>
                    <li><a href="${path}/emerList.do">홈</a></li>
                    <li><a href="${path}/prdList.do">전체보기</a></li>
                    <li><a href="${path}/prdList.do">구매</a></li>
                    <li><a href="#">예약</a></li>
                    <li class="active"><a href="${path}/cartList.do?id=${mem.id}">결제</a></li>
                </ul>
            </nav>
            <!-- Button Group -->
            <div class="amado-btn-group mt-30 mb-100">
                <a href="#" class="btn amado-btn mb-15">긴급구출</a>
                <a href="#" class="btn amado-btn active">예약상품</a>
            </div>
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
                <a href="cart.html" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(0)</span></a>
                <a href="#" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> Favourite</a>
                <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>
        <!-- Header Area End -->
        <div class="cart-table-area section-padding-100">
            <div class="container-fluid" style = "margin-top: -100px;">
                <div class="row" >
                    <div class="col-12 col-lg-8">
                        <div class="cart-title mt-50" >
                            <h2>Shopping Cart</h2>
                        </div>

                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>상품명</th>
                                        <th>상품금액</th>
                                        <th>수량</th>
                                    </tr>
                                </thead>
								<tbody>
									<c:set var ="totalPayment" value = "0" />
									<c:forEach var="cart" items="${cartList}">
										<tr>
											<td class="cart_product_img"><a href="#"><img
													src="${path}/vgbox_front/store/img/product-img/${cart.img}"
													alt="..."></a></td>
											<td class="cart_product_desc">
												<h5 style="font-weight: bold;">${cart.product_name}</h5>
											</td>
											<td class="price"><span style="font-weight: bold;"><fmt:formatNumber
														value="${cart.price}" />원</span></td>
											<td class="qty">
												<div class="quantity" style = "float: left; margin-right: 50px;" >
													<span style="font-weight: bold;">${cart.count}개</span>
												</div>
												<button type = "button" onclick = "delCart('${cart.cart_num}')" class="delete-button" 
												style = "background-color: white; border:1px solid black; border-radius: 6px; width: 25px; 
												height:25px; font-weight: bold;">X</button>
											</td>
										</tr>
										 <c:set var="totalPayment" value="${totalPayment + (cart.price * cart.count)}" />
									</c:forEach>

								</tbody>
							</table>
                        </div>
                    </div>
                    
                    <div class="col-12 col-lg-4" >
                        <div class="cart-summary" >
                            <h5>배송정보</h5>
                            <ul class="summary-table">
                                <li><span>받는 사람:</span> <span>${mem.name}</span></li>
                                <li><span>휴대폰 번호:</span> <span>${mem.contact}</span></li>
                                <li><span>주소:</span> <span>${mem.address}</span></li>
                                <h5>결제 정보</h5>
                                <ul class="summary-table">
                                    <li><span>가격:</span> <span id="totPrice" class="prd-price"><fmt:formatNumber value="${totalPayment}"/>원</span></li>
                                    <li><span>배송비:</span> <span class="delivery">3,000원</span></li>
                                    <li><span>총 결제 금액:</span> <span class="total-pay"><fmt:formatNumber value = "${totalPayment + 3000}"/>원</span></li>
                                </ul>
                                <div class="payment-method">
                                    <!-- 결제 수단 -->
                                    <div class="custom-control custom-checkbox mr-sm-2">
                                        <input type="checkbox" class="custom-control-input" id="cod">
                                        <label class="custom-control-label" for="cod"
                                            style="font-weight: bold;">계좌이체</label>
                                    </div>
                                    <div class="custom-control custom-checkbox mr-sm-2">
                                        <input type="checkbox" class="custom-control-input" id="paypal">
                                        <label class="custom-control-label" for="paypal" style="font-weight: bold;">간편결제
                                            <img class="ml-15" src="img/core-img/paypal.png" alt=""></label>
                                    </div>
                                </div>
                                <div class="cart-btn mt-100">
                                    <div class="custom-control custom-checkbox d-block mb-2">
                                        <input type="checkbox" class="custom-control-input" id="customCheck2">
                                        <label class="custom-control-label" for="customCheck2"
                                            style="font-weight: bold;">[필수] 개인정보 수집 및 동의</label>
                                    </div>
                                    <a onclick="buyProd()" href="#" class="btn amado-btn w-100" style="font-weight: bold;">바로 구매하기</a>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Main Content Wrapper End ##### -->

    <!-- ##### Newsletter Area Start ##### -->
    <section class="newsletter-area section-padding-100-0">
        <div class="container">
            <div class="row align-items-center">
                <!-- Newsletter Text -->
                <div class="col-12 col-lg-6 col-xl-7">
                    <div class="newsletter-text mb-100">
                        <h2>Subscribe for a <span>25% Discount</span></h2>
                        <p>Nulla ac convallis lorem, eget euismod nisl. Donec in libero sit amet mi vulputate
                            consectetur. Donec auctor interdum purus, ac finibus massa bibendum nec.</p>
                    </div>
                </div>
                <!-- Newsletter Form -->
                <div class="col-12 col-lg-6 col-xl-5">
                    <div class="newsletter-form mb-100">
                        <form action="#" method="post">
                            <input type="email" name="email" class="nl-email" placeholder="Your E-mail">
                            <input type="submit" value="Subscribe">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Newsletter Area End ##### -->

    <!-- ##### Footer Area Start ##### -->
    <footer class="footer_area clearfix">
        <div class="container">
            <div class="row align-items-center">
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-4">
                    <div class="single_widget_area">
                        <!-- Logo -->
                        <div class="footer-logo mr-50">
                            <a href="index.html"><img src="img/core-img/logo2.png" alt=""></a>
                        </div>
                        <!-- Copywrite Text -->
                        <p class="copywrite">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            <script>document.write(new Date().getFullYear());</script> All rights reserved | This
                            template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
                                href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->&
                            Re-distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a>
                        </p>
                    </div>
                </div>
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-8">
                    <div class="single_widget_area">
                        <!-- Footer Menu -->
                        <div class="footer_menu">
                            <nav class="navbar navbar-expand-lg justify-content-end">
                                <button class="navbar-toggler" type="button" data-toggle="collapse"
                                    data-target="#footerNavContent" aria-controls="footerNavContent"
                                    aria-expanded="false" aria-label="Toggle navigation"><i
                                        class="fa fa-bars"></i></button>
                                <div class="collapse navbar-collapse" id="footerNavContent">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item active">
                                            <a class="nav-link" href="index.html">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="shop.html">Shop</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="product-details.html">Product</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="cart.html">Cart</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="checkout.html">Checkout</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>
<script type="text/javascript">
/* 	function delCart(cart_num) {
		if(confirm("상품을 삭제하시겠습니까?")) {
			alert("삭제완료")
			location.href = "${path}/cartDelete.do?cart_num=" + cart_num
		}
	} */
	  function delCart(cart_num) {
	    if (confirm("상품을 삭제하시겠습니까?")) {
	      $.ajax({
	        type: "POST",
	        url: "${path}/cartDelete.do?cart_num=" + cart_num,
	        success: function(data) {
	          // 삭제 버튼 클릭시, 페이지의 표기된 장바구니 목록에서 항목을 제거
	          $("#cart_" + cart_num).remove();
	          alert("삭제완료"); 
	          location.reload();
	        },
	        error: function() {
	          alert("상품이 삭제되지 않았습니다");
	        }
	      });
	    }
	  }
	
 	function buyProd(){
		var cartNum = "${cartList[0].cart_num}"
		var totPrice = ${totalPayment + 3000}
		
		if(confirm("구매하시겠습니까?")){
			location.href="${path}/orderinfoins.do?cart_num="+cartNum+"&order_price="+totPrice
		}
	} 
</script>


    <!-- ##### Footer Area End ##### -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
    <!-- Addition js -->
<!--     <script src="js/kcommas.js"></script> -->
    <!-- Agreeterms js -->
    <script src="js/agreeterm.js"></script>