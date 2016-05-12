<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.Problem"%>
<%@page import="org.arthur.review.model.User"%>
<%@page import="org.arthur.review.service.ProblemService"%>
<%@page import="java.util.List"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of problems</title>
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
					<th>Problem Number</th>
					<th>Problem Name</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
        	<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
        		ProblemService psr = new ProblemService();
            	List<Problem> problemsList = psr.getAllProblems();            	        
            	
            	for (Problem problem : problemsList) {
            		
       		 %>
       		 <tr>
					<td><%=problem.getProblemNumber()%></td>
					<td><%=problem.getProblemName()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="uploadSampleSolutions.jsp">
								<input type="hidden" id="problemNumber" name="problemNumber" 
									value="<%=String.valueOf(problem.getProblemNumber())%>" />
								<input type="hidden" id="problemName" name="problemName" 
									value="<%=String.valueOf(problem.getProblemName())%>" />  
								<input type="submit" value="Upload Sample Solutions" />
							</form>
						</div>
					</td>
					<td style="border: none;">
						<div>
							<form method="post" action="FirstSolutionListSelection">
								<input type="hidden" id="problemNumber" name="problemNumber" 
									value="<%=String.valueOf(problem.getProblemNumber())%>" />
								<input type="hidden" id="problemName" name="problemName" 
									value="<%=String.valueOf(problem.getProblemName())%>" />  
								<input type="submit" value="Review Sample Solutions" />
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