<%@page import="org.arthur.review.model.StudentReview"%>
<%@page import="org.arthur.review.service.StudentRankingService"%>
<%@page import="java.util.ArrayList"%>
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
			<li><a href="studenthome.jsp">Home</a></li>
			<li><a href="viewProblemsToReview.jsp">Problems to Review</a></li>
			<li><a href="#">Finished Reviews</a></li>
			<li id="navLogout"><a href="#">Options</a>
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
           		String userName = session.getAttribute("userName").toString();
        		StudentRankingService studentRanker = new StudentRankingService();
        		List <StudentReview>studentPairsReviewed = new ArrayList <StudentReview>();
        		studentPairsReviewed = studentRanker.getAllgetAllPairsReviewedByStudent(userName);        	     
            	
            	for (StudentReview studentPairReviewed : studentPairsReviewed) {
            		
       		 %>
       		 <tr>       		 
					<td><%=studentPairReviewed.getLhsSolutionKey()%></td>
					<td><%=studentPairReviewed.getRhsSolutionKey()%></td>
					<td><%=studentPairReviewed.getRankingJudgement()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="reviewFeedbackForPair.jsp">
								<input type="hidden" id="reviewPairKey" name="reviewPairKey" 
									value="<%=String.valueOf(studentPairReviewed.getReviewPairKey())%>" />
								<input type="hidden" name="userName" id="userName" value="<%=userName%>">
								<input type="submit" value="View Admin's Review Feedback" />
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