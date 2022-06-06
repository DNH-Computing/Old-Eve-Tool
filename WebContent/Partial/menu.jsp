<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="common" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<div id="Menu">
	<ul id="TopMenu">
		<common:choose>
			<common:when test="${actionBean.isLoggedIn()}">
				<li>
					<a href="#">Security Check</a>
					<ul>
						<li>
							<a href="#">Basic</a>
						</li>
						<li>					
							<a href="#">Full</a>
						</li>
						<li>
							<a href="#">Full</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">Library</a>
				</li>
				<li><stripes:link beanclass="nz.net.dnh.evetool.controller.UserController" event="logout">Logout</stripes:link></li>
			</common:when>
			<common:otherwise>
				<li><stripes:link beanclass="nz.net.dnh.evetool.controller.UserController" event="login">login</stripes:link></li>
			</common:otherwise>
		</common:choose>
	</ul>
</div>

<common:if test="${actionBean.isLoggedIn() }">
	<div style="clear:both;font-size:1px;">&nbsp;</div>
</common:if>

<script type="text/javascript">//<![CDATA[
$(function(){
	$('#TopMenu li')
		.addClass('Element')
		
		.not('#TopMenu ul *')
		.addClass('TopElement');
		
	$('.Element > a, .Element')
		.corner('5px');
		
	$('#TopMenu li ul')
		.append('<div class="borderClean"/><div class="bottomCorner"/>')
		.corner('tr bottom 5px');
		
	var elHeight = $('#TopMenu li').height();
	$('.bottomCorner')
		.css('top',elHeight+'px')
		.corner('tr 5px');
	$('.borderClean').height(elHeight+3);
	
	$("ul#TopMenu .TopElement:has(ul) > a")
		.click(function() { //When trigger is clicked...

			if ($(this).parent().find('ul').is(':visible')) return;

			//Following events are applied to the subnav itself (moving subnav up and down)
			$(this).parent()
				.addClass('OpenMenu')
				.find("ul")
				.effect("slide", { direction: "left" }, "fast")
				.show(); //Drop down the subnav on click

			$(this)
				.recorner("tl bl 5px")
				.parent()
					.recorner("tl bl 5px");

			var open = true;
			$(this).parent().mouseleave(function(){
				if (open) {
					$(this)
						.removeClass('OpenMenu')
						.find("ul")
						.hide(
							"fold",
							{size: elHeight + 4}, 
							"fast",
							function () {
								 $(this).parent() // Gets us the parent li
								 		.recorner("5px")
							 				.find("> a")
							 				.recorner("5px");
							}); //When the mouse hovers out of the subnav, move it back up
					open = false;
				}
			});

		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
		});
});
//]]></script>