<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>練習日削除ページ</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<jsp:include page="/attend/common/header.jsp" flush="true"/>
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<jsp:include page="/attend/manage/practice/sidebar.jsp" flush="true"/>
				<div id="content">
					<div class="post">
						<h2>練習日削除ページ</h2>
						<p>練習日を削除しますか？</p>
						<form method="post" action="/attend/manage/practice/delete/submit">
						<table>
							<tr>
								<td>開始日時</td> 
								<td><fmt:formatDate value="${practice.startDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
							<tr>
								<td>終了日時</td> 
								<td><fmt:formatDate value="${practice.endDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
							<tr>
								<td>練習場所</td> 
								<td>${f:h(practice.practicePlace)}</td>
							</tr>
							<tr>
								<td>集合時刻</td> 
								<td><fmt:formatDate value="${practice.gatheringDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
							<tr>
								<td>集合場所</td> 
								<td>${f:h(practice.gatheringPoint)}</td>
							</tr>
							<tr>
								<td>備考</td> 
								<td>${f:h(practice.recital)}</td>
							</tr>
							<tr>
								<td><input type="hidden" name="key" value="${f:h(practice.key)}"/></td>
								<td><input type="submit" name="cancel" id="submit" value="戻る" /></td>
								<td><input type="submit" name="submit" id="submit" value="削除" /></td>
							</tr>
						</table>
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