<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>出欠情報確認ページ</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<jsp:include page="/attend/common/header.jsp" flush="true"/>
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<jsp:include page="/attend/common/sidebar.jsp" flush="true"/>
				<div id="content">
					<div class="post">
						<h2>2010年10月</h2>
						<p></p>
						<table>
							<tr>
								<td>2010/10/01</td> 
								<td>出席</td>
							</tr>
							<tr>
								<td>2010/10/01</td> 
								<td>欠席</td>
							</tr>
							<tr>
								<td>2010/10/01</td> 
								<td>遅刻</td>
							</tr>
							<tr>
								<td>2010/10/01</td> 
								<td>出席</td>
							</tr>
							<tr>
								<td>2010/10/01</td> 
								<td>出席</td>
							</tr>
						</table>
						<p class="links"><a href="#">前の月へ</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="b0x w">次の月へ</a></p>
						<input type="submit" id="submit" value="修正" />
					</div>
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<jsp:include page="/attend/common/footer.jsp" flush="true"/>
</body>
</html>
