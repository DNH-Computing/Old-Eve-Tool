<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes"	uri="http://stripes.sourceforge.net/stripes.tld"%>

<div id="WelcomeBox">
	You are currently logged in with a 
	
	<span class="limited"><a href="#/profile/key">Limited Key</a></span>
	<span class="full"><a href="#/profile/key">Full Key</a></span>
	
	<div class="HelpBox">
		<img alt="?" src="${pageContext.request.contextPath}/assets/img/gnome-help.jpg" />
		<div class="hidden">
			For full functionality a full API key is needed.
		</div>
	</div>
</div>

<div id="Balance">
	<h1>Balance</h1>
	
	<span>0.00 ISK</span>
</div>

<script type="text/javascript">// <![CDATA[
	$(function () {
		$(".HelpBox div").hide();

		$(".HelpBox div").dialog({
			autoOpen: falsea,
			resizable: false
		});

		$(".HelpBox img").click(function () {
			$(".HelpBox div").dialog("open");
		});
	});
	//]]>
</script>