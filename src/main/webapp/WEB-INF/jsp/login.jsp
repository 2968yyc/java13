<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/commons/common_js.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="copyright" content="Copyright 2016" />
<meta name="Author" content="大型生产管理ERP系统" />
<meta name="keywords" content="生产管理系统--生产进度,设备管理,工艺监控,物料监控,人员监控,质量监控" />
<meta name="description" content="制造装备物联及生产管理中间件/系统登陆页面" />
<title>制造物联中间件系统登录页面</title>
<link rel="stylesheet" type="text/css" href="css/login/style.css" />
<style type="text/css">
	.download{margin:20px 33px 10px;*margin-bottom:30px;padding:5px;border-radius:3px;-webkit-border-radius:3px;
		-moz-border-radius:3px;background:#e6e6e6;border:1px dashed #df0031;font-size:14px;font-family:Comic Sans MS;
		font-weight:bolder;color:#555}
	.download a{padding-left:5px;font-size:14px;font-weight:normal;color:#555;text-decoration:none;letter-spacing:1px}
	.download a:hover{text-decoration:underline;color:#36F}
	.download span{float:right}
</style>
	<script type="text/javascript" language="JavaScript">
		function checkInCorrect()
		{
			var username = $("#username");
				$.ajax({
					url:'${baseurl}ajaxQuery',
					data:{ username:username.val()},
					type : 'post',
					cache : false,
					dataType : 'json',
					success:function (res) {
						if (res.msg != '001') {

							var isSave =document.getElementById('remember_password').checked;   //保存按键是否选中
							if (isSave) {
								var username = document.getElementById('username').value;
								var password = document.getElementById('password').value;
								if (username != "" && password != "") {
									SetCookie(username, password);
								}
							} else {
								SetCookie("", "");
							}
						}
					}
				})


		}

		function SetCookie(username, password) {
			var Then = new Date();
			Then.setTime(Then.getTime() + 1866240000000);
			document.cookie = "username=" + username + "%%" + password + ";expires=" + Then.toGMTString();
		}

		function GetCookie() {
			var nmpsd;
			var nm;
			var psd;
			var cookieString = new String(document.cookie);
			var cookieHeader = "username=";
			var beginPosition = cookieString.indexOf(cookieHeader);
			cookieString = cookieString.substring(beginPosition);
			var ends = cookieString.indexOf(";");
			if (ends != -1) {
				cookieString = cookieString.substring(0, ends);
			}
			if (beginPosition > -1) {
				nmpsd = cookieString.substring(cookieHeader.length);
				if (nmpsd != "") {
					beginPosition = nmpsd.indexOf("%%");
					nm = nmpsd.substring(0, beginPosition);
					psd = nmpsd.substring(beginPosition + 2);
					document.getElementById('username').value = nm;
					document.getElementById('password').value = psd;
					if (nm != "" && psd != "") {
						// document.forms[0].checkbox.checked = true;
						document.getElementById('remember_password').checked = true;
					}
				}
			}
		}
	</script>

</head>

<body onload="GetCookie()">
	<div class="main">
		<div class="header hide"> 大型生产管理ERP系统  </div>
		<div class="content">
			<div class="title hide">大型生产管理ERP系统 登录</div>
			<form name="login" action="#" method="post">
				<fieldset>
				
					<!--USERNAME -->
					<div>
						<div class="input">
							<input class="input_all name" name="name" id="username" placeholder="用户名" type="text"
								   onFocus="this.className='input_all name_now';"
								   onBlur="this.className='input_all name'" maxLength="24" />
						</div>
						<div id="username_span"style="display:none;padding-bottom:7px;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="userspan"></span>
						</div>
							
					</div>
					
					<!-- PASSWORD -->
					<div>
						<div class="input">
							<input class="input_all password" name="password" id="password" type="password"
								   placeholder="密码" onFocus="this.className='input_all password_now';"
								   onBlur="this.className='input_all password'" maxLength="24" />
						</div>
						
						<div id="password_span"style="display:none;margin:0 0 0 0;padding:0 0 0 0;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="passwordspan"></span>
						</div>
	
						<div style="margin-bottom:12px">
							<div id="randiv" style="display:none;margin-left:98px;">
							
								验证码：<input id="randomcode" name="randomcode" size="8" /> <img
									id="randomcode_img" src="${baseurl}validatecode.jsp" alt=""
									width="56" height="20" align='absMiddle' /> <a
									href=javascript:randomcode_refresh()>刷新</a>
							</div>
							<div style="margin-left:98px;">
								<span id="randomcode_span"></span>
							</div>
						</div>
					</div>
					
					<!-- REMEMBERME -->					
					<div class="checkbox">
						<input type="checkbox" name="remember_password" id="remember_password" value="true"/>
						<label for="remember_password">
							<span>记住密码</span>
						</label>
						<span id="errorspan" style="margin-left:88px;"></span>
					</div>


					<div>
						<a href="#" id="login" class="button hide" OnClick="checkInCorrect()">登录</a>
					</div>
					
				</fieldset>
			</form>
		</div>
	</div>
<script type="text/javascript" src="js/login/placeholder.js"></script>
<script type="text/javascript">
		$("#login")
				.click(
						function() {

							var uname = $("#username");
							var pwd = $("#password");
							var display = $("#randiv").css('display');
							var rcode = $("#randomcode");

							if (display == 'none') {
								if ($.trim(uname.val()) == "") {
									$('#username_span').css('display','block');
									$("#passwordspan").html("");
									$("#userspan")
											.html(
													"<font color='red'>用户名不能为空！</font>");
									uname.focus();
								} else if ($.trim(pwd.val()) == "") {
									$('#username_span').css('display','none');
									$('#password_span').css('display','block');
									$("#userspan").html("");
									$("#passwordspan").html(
											"<font color='red'>密码不能为空！</font>");
									pwd.focus();
								} else {
									$('#password_span').css('display','none');
									$("#userspan").html("");
									$("#passwordspan").html("");
									$
											.ajax({
												url : '${baseurl}ajaxLogin',// 跳转到 action  
												data : {
													username : uname.val(),
													password : pwd.val(),
												},
												type : 'post',
												cache : false,
												dataType : 'json',
												success : function(data) {
													if (data.msg == 'account_error') {
												console.log("account_erroe.");
														$("#errorspan")
																.html(
																		"<font color='red'> 用户不存在！</font>");
														rcode_flag = 1;
														$("#randiv").show();
													} else if (data.msg == 'password_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 密码错误！</font>");
														rcode_flag = 1;
														$("#randiv").show();
													} else if (data.msg == 'authentication_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 您没有授权！</font>");
														rcode_flag = 1;
														$("#randiv").show();
													} else {
														location.href = "${baseurl}home";
													}
												},
												error : function() {
													// view("异常！");  
													alert("异常！");
												}
											});
								}
							} else {
								$("#errorspan").html("");
								if ($.trim(uname.val()) == "") {
									$("#passwordspan").html("");
									$("#userspan")
											.html(
													"<font color='red'>用户名不能为空！</font>");
									uname.focus();
								} else if ($.trim(pwd.val()) == "") {
									$("#userspan").html("");
									$("#passwordspan").html(
											"<font color='red'>密码不能为空！</font>");
									pwd.focus();
								} else if ($.trim(rcode.val()) == "") {
									$("#userspan").html("");
									$("#randomcode_span")
											.html(
													"<font color='red'>验证码不能为空！</font>");
									rcode.focus();
								} else {
									$("#userspan").html("");
									$("#passwordspan").html("");
									$("#randomcode_span").html("");
									$
											.ajax({
												url : '${baseurl}ajaxLogin',// 跳转到 action  
												data : {
													username : uname.val(),
													password : pwd.val(),
													randomcode : rcode.val(),
												},
												type : 'post',
												cache : false,
												dataType : 'json',
												success : function(data) {
													if (data.msg == 'account_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 用户不存在！</font>");
														rcode_flag = true;
														randomcode_refresh();
													} else if (data.msg == 'password_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 密码错误！</font>");
														rcode_flag = true;
														randomcode_refresh();
													} else if (data.msg == 'randomcode_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 验证码错误！</font>");
														rcode_flag = true;
														randomcode_refresh();
													} else if (data.msg == 'authentication_error') {
														$("#errorspan")
																.html(
																		"<font color='red'> 您没有授权！</font>");
														rcode_flag = true;
														randomcode_refresh();
													} else {
														location.href = "${baseurl}home";
													}
												},
												error : function() {
													// view("异常！");  
													alert("异常！");
												}
											});
								}
							}
						}
				);


		//刷新验证码
		//实现思路，重新给图片的src赋值，后边加时间，防止缓存
		function randomcode_refresh() {
			$("#randomcode_img").attr('src',
					'${baseurl}validatecode.jsp?time' + new Date().getTime());
		}
	</script>
<!--[if IE 6]>
<script type="text/javascript" src="js/login/belatedpng.js" ></script>
<script type="text/javascript">
	DD_belatedPNG.fix("*");
</script>

<![endif]--></body>
</html>