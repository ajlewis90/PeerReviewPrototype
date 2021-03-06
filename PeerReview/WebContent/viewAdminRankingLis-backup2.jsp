<%@page import="java.util.ArrayList"%>
<%@page import="org.arthur.review.model.AdminReview"%>
<%@page import="java.util.List"%>
<%@page import="org.arthur.review.service.AdminRankingService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />
<title>Insert title here</title>
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
					<li><a href="viewSampleSolutionsList.jsp">View List of
							Sample Solutions</a></li>
				</ul></li>
			<li><a href="viewAdminRankingList.jsp">View Finished Reviews</a></li>
			<li class="navLogout"><a href="#">Options</a>
				<ul>
					<li><a href="logout.jsp">Logout</a></li>
				</ul></li>
		</ul>
	</div>
	
	<table align="center">
			<thead>
				<tr>
					<th>LHS Problem</th>
					<th>RHS Problem</th>
					<th>Ranking Judgment</th>					
					<th></th>
				</tr>
			</thead>
			<tbody>
        	<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
        		AdminRankingService adminRanker = new AdminRankingService();
        		List <AdminReview>	adminPairsReviewed = new ArrayList <AdminReview>();
        		adminPairsReviewed = adminRanker.getAllgetAllPairsReviewedByAdmin();        	     
            	
            	for (AdminReview adminPairReviewed : adminPairsReviewed) {
            		
       		 %>
       		 <tr>
					<td><%=adminPairReviewed.getLhsSolutionKey()%></td>
					<td><%=adminPairReviewed.getRhsSolutionKey()%></td>
					<td><%=adminPairReviewed.getRankingJudgement()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="viewAdminReviewPairDetails.jsp">
								<input type="hidden" id="reviewPairKey" name="reviewPairKey" 
									value="<%=String.valueOf(adminPairReviewed.getReviewPairKey())%>" />
								
								<input type="submit" value="View Review Details" />
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