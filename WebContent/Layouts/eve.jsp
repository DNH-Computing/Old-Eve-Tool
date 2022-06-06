<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes"	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="dnh-common" uri="https://www.dnh.net.nz/common.tld" %>
<%@ taglib prefix="common" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-definition>
	<!DOCTYPE html>
	<html>
		<head>
			<!-- Put out the title -->
			<dnh-common:headtitle />
			
			<!-- Put out the links -->
			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery-ui.css" media="screen" rel="stylesheet" type="text/css" >
			<link href="${pageContext.request.contextPath}/assets/css/EveTool.css" media="screen" rel="stylesheet" type="text/css" >
			<link href="${pageContext.request.contextPath}/assets/css/GoMenu.css" media="screen" rel="stylesheet" type="text/css" >		
			
			<!-- Put out the scripts -->
<!-- 			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"></script> -->
<!-- 			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/jquery-ui.js"></script> -->
			<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAAfCbAnsRdOFtObU0m1OV6nhTZLU06VJXrFdeTzxLQEmX-DFk85hQwE5J0qU6kccCK_9vRZxPCIf1cEw"></script>
			<script type="text/javascript">
				google.load("jquery", "1.4.4");
				google.load("jqueryui", "1.8.7");
			</script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tiny_mce/jquery.tinymce.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery_plugins/jquery.corner.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery_plugins/go_menu.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery_plugins/ComboBox.js"></script>
		</head>
		<body>
			<div id="Header">
				<div class="Relitive">
					<div id="Logo">
						<div class="Content">
							LOGO
						</div>
					</div>
					
					<div id="Title">
						<div class="Content">
							<h1>EVE&trade; API Tool</h1>
							<h2>Provided by <a href="http://www.dnh.net.nz/">DNH Computing</a></h2>
						</div>
					</div>
					
					<jsp:include page="/Partial/search.jsp" />
				</div>
			</div>
			
			<div id="Leftbar">
				<div class="Content">
					<jsp:include page="/Partial/menu.jsp" />
				
					<common:if test="${actionBean.isLoggedIn()}">
						<jsp:include page="/Partial/balance.jsp" />	
					</common:if>
				</div>
			</div>
			
			<div id="MainFrame">
				<div class="Content">
					<stripes:layout-component name="PageContent"></stripes:layout-component>
				</div>
				
				<div id="Footer" class="Content">
					Site design and content &copy; <a href="http://www.dnh.net.nz/">DNH Computing</a> 2011<br />
					<br />
					EVE Online and the EVE logo are the registered trademarks of CCP hf. All rights are reserved worldwide. 
					All other trademarks are the property of their respective owners. EVE Online, the EVE logo, EVE and all 
					associated logos and designs are the intellectual property of CCP hf. All artwork, screenshots, characters, 
					vehicles, storylines, world facts or other recognizable features of the intellectual property relating to 
					these trademarks are likewise the intellectual property of CCP hf. CCP hf. has granted permission to 
					EVE API Tool to use EVE Online and all associated logos and designs for promotional and information 
					purposes on its website but does not endorse, and is not in any way affiliated with, EVE API Tool. 
					CCP is in no way responsible for the content on or functioning of this website, nor can it be liable 
					for any damage arising from the use of this website. 
				</div>
			</div>
			
			<script type="text/javascript">//<![CDATA[
				$('.Content')
					.corner("5px")
					.parent()
						.css('padding', '2px')
						.corner("5px")
				;
			//]]></script>
			
			<noscript>
				<div class="Cover"></div>
				
				<div class="Message">
					<h1>Javascript Required</h1>
				
					<p>Javascript is required to use this application. Please enable javascript and refresh this page.</p>
				</div>
			</noscript>
		</body>
	</html>
</stripes:layout-definition>