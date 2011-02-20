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
<title>練習日更新ページ</title>
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
						<h2>練習日更新ページ</h2>
						<p>練習日と詳細情報を入力してください<br>※サンプル：日付→2010-09-10  時刻→07:10</p>
						<p><span class="error">${f:h(errors.inputError)}</span></p>
						<form method="post" action="/attend/manage/practice/update/submit">
						<table>
							<tr>
								<td>練習日</td> 
								<td><input type="text" id="loginid" ${f:text("practiceDate")} /> </td> 
								<td><span class="error">${f:h(errors.practiceDate)}</span></td>
							</tr>
							<tr>
								<td>練習開始時刻</td> 
								<td><input type="text" id="loginid" ${f:text("startTime")} /> </td>
								<td><span class="error">${f:h(errors.startTime)}</span></td>
							</tr>
							<tr>
								<td>練習終了時刻</td> 
								<td><input type="text" id="loginid" ${f:text("endTime")} /> </td>
								<td><span class="error">${f:h(errors.endTime)}</span></td>
							</tr>
							<tr>
								<td>練習場所</td> 
								<td><input type="text" id="loginid" ${f:text("practicePlace")}/></td>
								<td><span class="error">${f:h(errors.practicePlace)}</span></td>
							</tr>
							<tr>
								<td>集合時刻</td> 
								<td><input type="text" id="loginid" ${f:text("gatheringTime")} /> </td>
								<td><span class="error">${f:h(errors.gatheringTime)}</span></td>
							</tr>
							<tr>
								<td>集合場所</td> 
								<td><input type="text" id="loginid" ${f:text("gatheringPoint")}/></td>
								<td><span class="error">${f:h(errors.gatheringPoint)}</span></td>
							</tr>
							<tr>
								<td>備考</td> 
								<td><input type="text" id="loginid" ${f:text("racital")}/></td>
								<td><span class="error">${f:h(errors.recital)}</span></td>
							</tr>
							<tr>
								<td><input type="hidden" ${f:hidden("key")}/></td>
								<td><input type="submit" name="cancel" id="submit" value="戻る" /></td>
								<td><input type="submit" name="submit" id="submit" value="登録" /></td>
							</tr>
						</table>
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
