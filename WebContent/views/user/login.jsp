<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<stripes:layout-render name="/Layouts/eve.jsp">
	<stripes:layout-component name="PageContent">
		<stripes:form beanclass="nz.net.dnh.evetool.controller.UserController"
			method="post">
			<dl>
				<dt>
					<label for="corporation">Corporation</label>
				</dt>
				<dd>
					<stripes:text name="corporation" id="Corporation" />
				</dd>

				<dt>
					<label for="username">Username</label>
				</dt>
				<dd>
					<stripes:text name="username" id="username" />
				</dd>

				<dt>
					<label for="password">Password</label>
				</dt>
				<dd>
					<stripes:password name="password" id="password" />
				</dd>

				<dt>&nbsp;</dt>
				<dd>
					<stripes:submit name="login" value="Login" />
				</dd>

				<dd>
					<stripes:link
						beanclass="nz.net.dnh.evetool.controller.UserController"
						event="GoogleLogin">Login with Google</stripes:link>
				</dd>
			</dl>
		</stripes:form>
		
		<script type="text/javascript">//<![CDATA[
			$('#Corporation').autocomplete({
					//source: '/login/corporations',
					minLength: 0,
					autoFocus: true,
					source: function (request, response) {
						$.ajax({
							url: '<stripes:url beanclass="nz.net.dnh.evetool.controller.UserController" event="corporations" />',
							dataType: 'json',
							data: {
								term: request.term
							}, success: function (data) {
								response( $.map( data, function (item) {
									return {
										label: item.corpName,
										value: item.corpName
									};
								}));
							}
						});
					}
			})/*.keydown(function (event) {
				if (event.keyCode == 40) {
					$(this).autocomplete("search", '');
				}
			})*/;
		//]]></script>												
	</stripes:layout-component>
</stripes:layout-render>