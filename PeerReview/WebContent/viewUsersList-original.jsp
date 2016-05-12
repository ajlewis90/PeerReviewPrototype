<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.SecurityQuestion"%>
<%@page import="org.arthur.review.model.User"%>
<%@page import="org.arthur.review.service.LoginService"%>
<%@page import="java.util.List"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />
        <title>View All Users</title>
    </head>
    <body>
        <div id="wrap">
		  <ul class="navbar">
			 <li><a href="adminhome.jsp">Home</a></li>
			 <li><a href="viewUsersList.jsp">View All Users</a></li>
			 <li><a href="#">Problems</a>
				<ul>
				   <li><a href="addProblem.jsp" >Upload Problem</a></li>
				   <li><a href="viewProblemsList.jsp">Problems to Review</a></li>
				   <li><a href="viewSampleSolutionsList.jsp">View List of Sample Solutions</a></li>				
				</ul>         
			 </li>
			 <li><a href="viewAdminRankingList.jsp">View Finished Reviews</a>        
			 </li>		
			 <li class="navLogout"><a href="#">Options</a>
				<ul>
				   <li><a href="logout.jsp">Logout</a></li>
				</ul>         
			 </li>
		  </ul>
		</div>
		
        
		<table align="center">
			<thead>
				<tr>
					<th>Serial Number</th>
					<th>Username</th>
					<th>UPI</th>
					<th>Email</th>
					<th>Administrator</th>
				</tr>
			</thead>
			<tbody>
        	<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
        		LoginService ls = new LoginService();
            	List<User> userList = ls.getAllUsers();
            	String cardStatusDescription = null;
            	int quesOID = 0;
            	int serialNumber = 0;
            	for (User user : userList) {
            		quesOID = user.getSecurity_question_id(); 
            		String quesDescription = ls.mapQuestionOIDToDescription(quesOID);
       		 %>
       		 <tr>
       		 		<td><%=++serialNumber%>
					<td><%=user.getUserName()%></td>
					<td><%=user.getUpi()%></td>
					<td><%=user.getEmail()%></td>
					<td><%=user.isAdmin()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="adminUpdateUser.jsp">
								<input type="hidden" id="cardNumber" name="cardNumber" 
									value="<%=String.valueOf(user.getUserName())%>" />
								<input type="hidden" id="oldAdminStatus" name="oldAdminStatus" 
									value="<%=String.valueOf(user.isAdmin())%>" />  
								<input type="submit" value="Update User" />
							</form>
						</div>
					</td>
			</tr>
			<%
                        }
                    %>
			</tbody>
		</table>
	

</body>
</html>