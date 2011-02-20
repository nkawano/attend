<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<div id="sidebar">
	<ul>
		<jsp:include page="/attend/common/login.jsp" flush="true"/>
		<li>
			<h2>メニュー</h2>
			<ul>
				<li><a href="/attend/manage/member/regist/">団員登録</a></li>
				<li><a href="/attend/manage/member/search/"">団員検索</a></li>
				<li><a href="#">権限変更</a></li>
			</ul>
		</li>
	</ul>
</div>
<!-- end #sidebar -->
