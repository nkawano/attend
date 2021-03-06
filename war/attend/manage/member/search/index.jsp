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
<title>団員検索ページ</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<jsp:include page="/attend/common/header.jsp" flush="true"/>
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<jsp:include page="/attend/manage/member/sidebar.jsp" flush="true"/>
				<div id="content">
					<div class="post">
						<h2>団員検索ページ</h2>
						<p></p>
						<form method="post" action="/attend/manage/member/search/search">
						<table class="searchMember-searchTable">
							<tr>
								<td>ID(先方一致)</td>
								<td><input type="text" class="text1" name="id" value="" /></td>
								<td><input type="submit" class="searchMember-searchbutton" value="検索" /></td>
							</tr>
						</table>
						</form>
						検索結果
						<hr/>
						<form method="post" action="/attend/manage/member/update/">
						<c:if test="${memberList != null}">
						<table>
						<tr>
						<th></th>
						<th>ID</th>
						<th class="searchMember-resultTable-name">苗字</th>
						<th class="searchMember-resultTable-name">名前</th>
						</tr>
						</c:if>
						<c:forEach var="e" items="${memberList}">
						<tr>
						<td><input type="radio" name="id" value="${f:h(e.id)}"/></td>
						<td>${f:h(e.id)}</td>
						<td>${f:h(e.lastName)}</td>
						<td>${f:h(e.firstName)}</td>
						</tr>
						</c:forEach>
						</table>
						<p></p>
						<c:if test="${memberList != null}">
						<table>
							<tr>
								<td><input type="submit" class="searchMember-bottombuttons" name="update" value="更新" /></td>
								<td><input type="submit" class="searchMember-bottombuttons" name="delete" value="削除" /></td>
<!--								<td><input type="submit" class="searchMember-bottombuttons" name="searchAuth" value="権限参照" /></td>-->
								<td><input type="submit" class="searchMember-bottombuttons" name="updateAuth" value="権限変更" /></td>
								<td><span class="error">${f:h(errors.selectError)}</span></td>
							</tr>
						</table>
						</c:if>
						</form>
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
