<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />


<title>Success</title>
</head>
<body>
	
	<div id="wrap">
		  <ul class="navbar">
			 <li><a href="adminhome.jsp">Home</a></li>
			 <li><a href="viewUsersList.jsp">View All Users</a></li>
			 <li><a href="#">Problems</a>
				<ul>
				   <li><a href="addProblem.jsp">Upload Problem</a></li>
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
	
	<jsp:useBean id="user" class="org.arthur.review.model.User" scope="session">
		<jsp:setProperty property="userName" name="user"/>
	</jsp:useBean>
	Welcome, <jsp:getProperty property="userName" name="user"/>! 
	
	<div class="topcorner">
		<a href="logout.jsp">Logout</a>
	</div>
</body>
</html>