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
<title>出席入力ページ</title>
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
						<h2>${f:h(currentYear)}年${f:h(currentMonth)}月</h2>
						<c:if test="${attendanceList == null}">
							<p>練習日程がまだ登録されていません。</p>
						</c:if>						
						<c:if test="${attendanceList != null}">
							<p>出席情報を入力してください</p>
							<p><span class="error">${f:h(errors.cannotUpdate)}</span></p>
							<c:forEach var="e" items="${attendanceList}" varStatus="status">	
								<form method="post" action="/attend/inputAttendance/submit">
								<ul>
									<table>
										<c:set value="${e.attendance}" var="attendance" scope="request"/>
										<tr>
											<fmt:formatDate value="${e.practiceRef.model.startDate}" pattern="MM月dd日"/> <fmt:formatDate value="${e.practiceRef.model.startDate}" pattern="HH:mm"/> - <fmt:formatDate value="${e.practiceRef.model.endDate}" pattern="HH:mm"/> ＠${f:h(e.practiceRef.model.practicePlace)}</li>
										</tr>
										<tr>
											<td>
												<c:if test="${!(e.inputFlg)}">
													<select name="attendance">
												</c:if>
												<c:if test="${e.inputFlg}">
													<select name="attendance" disabled>
												</c:if>
														<option ${f:select("attendance", "0")}>未定</option>
														<option ${f:select("attendance", "1")}>出席</option>
														<option ${f:select("attendance", "2")}>欠席</option>
														<option ${f:select("attendance", "3")}>遅刻</option>
														<option ${f:select("attendance", "4")}>早退</option>
													</select>
											</td>
										<c:if test="${!(e.inputFlg)}">
											<td>&nbsp&nbsp&nbsp備考&nbsp<input type="text" id="reason" name="racital" value="${e.racital}"></input></td>
											<td><p>未入力</p></td>
											<td><input type="submit" id="submitdisabled" name="update" value="更新" disabled="disabled"/></td>
											<td><input type="submit" id="submit" name="regist" value="決定" /></td>
										</c:if>
										<c:if test="${e.inputFlg}">
											<td>&nbsp&nbsp&nbsp備考&nbsp<input type="text" id="reasondisabled" name="racital" value="${e.racital}" disabled></input></td>
											<td><p>入力済</p></td>
											<td><input type="submit" id="submit" name="update" value="更新" /></td>
											<td><input type="submit" id="submitdisabled" name="regist" value="決定" disabled="disabled"/></td>
										</c:if>
										</tr>
									</table>
									<input type="hidden" name="key" value="${f:h(e.key)}"/>										
									<input type="hidden" name="memberKey" value="${f:h(e.memberKey) }"/>
									<input type="hidden" name="practiceKey" value="${f:h(e.practiceKey) }"/>
									<input type="hidden" name="currentDate" value="${currentDate}" />
								</ul>
								</form>
							</c:forEach>
						</c:if>
						<p class="links"><a href="./?date=${f:h(beforeDate)}">前の月へ</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="./?date=${f:h(nextDate)}"">次の月へ</a></p>
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
