<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.12.0.js"></script>

<link href="css/style2.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="Login" method="post" name="login_frm" id="login_frm">
		

		<div id="login_box">
			<div id="login_header">Login</div>
			<div id="form_val">
				<div>Username: </div><br><div><input type="text" name="userName" id="userName" required=""/>
				</div>
				<br>
				<div>Password: </div><br><div><input type="password" name="password" id="password" required=""/>
				</div>
				<div style="clear: both; height: 0px;"></div>

				<div id="msgbox"></div>
			</div>
			
			<div id="login_footer">
				<label> <input type="submit" name="login" id="login"
					value="Login" />  <a href="signup.jsp">Register</a>
				</label>
			</div>
			
		</div>
	</form>
</body>
</html>