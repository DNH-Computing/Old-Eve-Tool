<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes"	uri="http://stripes.sourceforge.net/stripes.tld"%>
<div id="SearchBox">
	<div class="Content">
		<h1>Search library</h1>
		
		<form method="post">
			<input type="text" name="SearchString" id="SearchString" />
		</form>
	</div>
</div>

<script type="text/javascript">//<![CDATA[
	$(function () {
		$('#SearchBox #SearchString').GoMenu({
			source: '${pageContext.request.contextPath}/search',
			minLength: 0,
			delay: 0
		});
	});
//]]></script>