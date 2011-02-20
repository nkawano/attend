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
<title>練習日検索ページ</title>
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
						<h2>練習日検索ページ</h2>
						<p>初期表示時は過去の練習は表示しません。<br>表示したい場合は、日付を入力して検索して下さい。</p>
						<p><span class="error">${f:h(errors.cannotFind)}</span></p>
						<p><span class="error">${f:h(errors.cannotSearch)}</span></p>
						<form method="post" action="/attend/manage/practice/search/search">
						<table>
							<tr>
								<td>日付(YYYY-MM)</td> 
								<td><input type="text" name="date" id="loginid" value="${date}" /></td>
							</tr>
							<tr>
								<td></td> 
								<td><input type="submit" id="submit" value="検索" /></td>
							</tr>
						</table>
						</form>
						
						<form method="post" action="/attend/manage/practice/update/">
						<c:if test="${practiceList != null}">
						<table>
						<tr>
						<td>開始日時</td>
						<td>終了日時</td>
						<td>練習場所</td>
						<td></td>
						</tr>
						</c:if>
						<c:forEach var="e" items="${practiceList}">	
						<tr>
						<td><fmt:formatDate value="${e.startDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td><fmt:formatDate value="${e.endDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${f:h(e.practicePlace)}</td>
						<td><input type="radio" name="key" value="${f:h(e.key)}"/></td>
						</tr>
						</c:forEach>
						</table>
						<p></p>
						<c:if test="${practiceList != null}">
						<table>
							<tr>
								<td><input type="submit" id="submit" name="update" value="更新" /></td>
								<td><input type="submit" id="submit" name="delete" value="削除" /></td>
								<td><span class="error">${f:h(errors.selectError)}</td>
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
