<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.SecurityQuestion"%>
<%@page import="org.arthur.review.service.LoginService"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/add-user-registration-style.css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration form</title>
</head>
<body>
	<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
            LoginService ls = new LoginService();
            List<SecurityQuestion> sqList = ls.getAllSecurityQuestions();
            System.out.println("size of sq list: " + sqList.size());
     %>
	<div class="form">
    		<form id="contactform" action="AddNewUser" method="post"> 
    			<p class="contact"><label for="username">Create a username</label></p> 
    			<input id="userName" name="userName" placeholder="Username" required="" tabindex="1" type="text">     			     		
    			 
                <p class="contact"><label for="password">Create a password</label></p> 
    			<input type="password" id="password" name="password" required=""> 
                <p class="contact"><label for="repassword">Confirm your password</label></p> 
    			<input type="password" id="repassword" name="repassword" required=""> 
    			
    			<p class="contact"><label for="upi">UPI</label></p> 
    			<input id="upi" name="upi" placeholder="jsmi100" required="" type="text"> 
                
                <p class="contact"><label for="email">Email:</label></p> 
    			<input id="email" name="email" placeholder="example@domain.com" required="" type="email"> 
                
                 <p class="contact"><label for="securityQuestion">Security Question</label></p>
    				<select name="securityQuestion" id="securityQuestion" size="1">
        				<%for (SecurityQuestion sq : sqList) {
        					System.out.println(sq.getQues_description());
        				%> 
        				<option><%=String.valueOf(sq.getQues_description())%></option>
        				<%
        				
                        }
                    	%>
       	 			</select>
       	 		<br>
       	 	<p class="contact"><label for="securityAnswer">Answer</label></p>
       	 	<input id="securityAnswer" name="securityAnswer" placeholder="e.g. My favorite food is chicken!" required="" type="text">
       	 	
       	 	 <p class="contact"></p>
            <input class="buttom" name="submit" id="submit" tabindex="5" value="Register me!" type="submit"> 	 
   </form>
   </div>
</body>
</html>