<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />
<style type="text/css">
.topcorner {
	position: absolute;
	top: 0;
	right: 0;
	
}
</style>

<title>Success</title>
</head>
<body>
	<div id="wrap">
		  <ul class="navbar">
			 <li><a href="studenthome.jsp">Home</a></li>
			 <li><a href="viewProblemsToReview.jsp">Problems to Review</a></li>
			 <li><a href="viewStudentRankingList.jsp">Finished Reviews</a></li>
			 <li id="navLogout"><a href="#">Options</a>
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
</body>
</html>