<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.SampleSolution"%>
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
					<th>Serial Number</th>
					<th>Problem Name</th>
					<th>Solution Number</th>
					<th>Problem Solution Pair</th>
					
				</tr>
			</thead>
			<tbody>
        	<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
        		ProblemService psr = new ProblemService();
            	List<SampleSolution> sampleSolutionList = psr.getAllSampleSolutions();            	
            	int serialNumber = 0;
            	
            	for (SampleSolution sampleSolution : sampleSolutionList) {
            		
       		 %>
       		 <tr>
       		 		<td><%=++serialNumber%></td>
					<td><%=sampleSolution.getProblem_Number()%></td>
					<td><%=sampleSolution.getSolution_Number()%></td>
					<td><%=sampleSolution.getProblem_Solution_Pair()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="editSampleSolution.jsp">
								<input type="hidden" id="problemNumber" name="problemNumber" 
									value="<%=String.valueOf(sampleSolution.getProblem_Number())%>" />
								<input type="hidden" id="problemSolutionPair" name="problemSolutionPair" 
									value="<%=String.valueOf(sampleSolution.getProblem_Solution_Pair())%>" />
								<input type="hidden" id="goodAspects" name="goodAspects" 
									value="<%=String.valueOf(sampleSolution.getGood_Apects())%>" />
								<input type="hidden" id="badAspects" name="badAspects" 
									value="<%=String.valueOf(sampleSolution.getBad_Aspects())%>" />
								<input type="hidden" id="uploadLocation" name="uploadLocation" 
									value="<%=String.valueOf(sampleSolution.getUpload_Location())%>" />  
								<input type="submit" value="Edit Sample Solutions" />
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