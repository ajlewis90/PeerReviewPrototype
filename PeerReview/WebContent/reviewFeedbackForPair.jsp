<%@page import="org.arthur.review.service.StudentRankingService"%>
<%@page import="org.arthur.review.service.AdminRankingService"%>
<%@page import="org.arthur.review.model.StudentReview"%>
<%@page import="org.arthur.review.model.AdminReview"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/admin-home.css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	function showDetails(){
		document.getElementById('hidden-div').style.display = "block";
	}
	
	
</script>

<title>Review Feedback</title>
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
				</ul></li>
		</ul>
	</div>
	
	<%
		String userName = session.getAttribute("userName").toString();
		String reviewPairKey = request.getParameter("reviewPairKey");
		
		AdminRankingService adminRanker = new AdminRankingService();
		AdminReview adminReview = adminRanker.getReviewDetails(reviewPairKey);
		
		StudentRankingService studentRanker = new StudentRankingService();
		StudentReview studentReview = studentRanker.getReviewDetails(reviewPairKey, userName);
		
	%>
	
	<form name="compareSolutionsStudent" action="viewStudentRankingList.jsp" method="post" onSubmit = "return confirmComplete()">
	<div style="width: 100%;">
		<div style="float: left; width: 50% margin: auto; border: 2px solid #73AD21;padding: 10px;">
			
			<p class="contact">
				<label for="reviewIdentification">Your review</label>
			</p>
				
			<p class="contact">
				<label for="reviewPair">Reviewed Solution Pair</label>
			</p>
			<input id="studentReviewPairKey" name="problemNumber" 
				tabindex="1" type="text" readonly="readonly"
				value="<%=studentReview.getReviewPairKey()%>">
			
			<p class="contact">
				<label for="studentReviewJudgement">Your Judgment</label>
			</p>
			<textarea id="studentReviewJudgement" name="studentReviewJudgement" cols="60"
						rows="1" readonly="readonly"><%=studentReview.getRankingJudgement()%></textarea>
			
			
			<p class="contact">
				<label for="studentReviewJustification">Your Judgment's Justification</label>
			</p>
			<textarea id="studentReviewJustification" name="studentReviewJustification" cols="60"
						rows="20" readonly="readonly"><%=studentReview.getRankingJustification()%></textarea>

				<div>
					<p class="contact">
						<label for="leftKey">Left hand side solution</label>
					</p>
					<input type="text" id="leftKey" name="leftKey" readonly="readonly"
						value="<%=studentReview.getLhsSolutionKey()%>">
					
					<p class="contact">
						<label for="leftBadAspects">Negative aspects of left hand side solution</label>
					</p>
					<textarea id="leftBadAspects" name="leftBadAspects" cols="60"
						rows="20"><%=studentReview.getLhsSolutionBadAspects()%></textarea>

					<p class="contact">
						<label for="leftGoodAspects">Positive aspects of left hand side solution</label>
					</p>
					<textarea id="leftGoodAspects" name="leftGoodAspects" cols="60"
						rows="20"><%=studentReview.getLhsSolutionGoodAspects()%></textarea>

					<p class="contact">
						<label for="rightKey">Right hand side solution</label>
					</p>
					<input type="text" id="rightKey" name="rightKey" readonly="readonly"
						value="<%=studentReview.getRhsSolutionKey()%>">
					
					<p class="contact">
						<label for="rightBadAspects">Negative aspects of right hand side solution</label>
					</p>
					<textarea id="rightBadAspects" name="rightBadAspects" cols="60"
						rows="20"><%=studentReview.getRhSolutionBadAspects()%></textarea>

					<p class="contact">
						<label for="rightGoodAspects">Positive aspects of right hand side solution</label>
					</p>
					<textarea id="rightGoodAspects" name="rightGoodAspects" cols="60"
						rows="20"><%=studentReview.getRhsSolutionGoodAspects()%></textarea>
					
				</div>
			
		</div>
		<div style="float: right; width: 50% width: 50% margin: auto; border: 2px solid #73AD21; padding: 10px;">
			
			<p class="contact">
				<label for="reviewIdentification">Course admin's review </label><a onclick="showDetails()"><b><u>See details</u></b></a>
			</p>
				
			<p class="contact">
				<label for="reviewPair">Reviewed Solution Pair</label>
			</p>
			<input id="adminReviewPairKey" name="adminReviewPairKey" 
				tabindex="1" type="text" readonly="readonly"
				value="<%=adminReview.getReviewPairKey()%>">
			
			<p class="contact">
				<label for="adminReviewJudgement">Admin's Judgment</label>
			</p>
			<textarea id="adminReviewJudgement" name="adminReviewJudgement" cols="60"
						rows="1" readonly="readonly"><%=adminReview.getRankingJudgement()%></textarea>
						
			<p class="contact">
				<label for="admintReviewJustification">Admin's Justification</label>
			</p>
			<textarea id="admintReviewJustification" name="admintReviewJustification" cols="60"
						rows="20" readonly="readonly"><%=adminReview.getRankingJustification()%></textarea>


			<p class="contact">
				
			</p>
				<div id="hidden-div" style="display:none">
					<p class="contact">
						<label for="leftKey">Left hand side solution</label>
					</p>
					<input type="text" id="leftKey" name="leftKey" readonly="readonly"
						value="<%=adminReview.getLhsSolutionKey()%>">
					
					<p class="contact">
						<label for="leftBadAspects">Negative aspects of left hand side solution</label>
					</p>
					<textarea id="leftBadAspects" name="leftBadAspects" cols="60"
						rows="20"><%=adminReview.getLhsSolutionBadAspects()%></textarea>

					<p class="contact">
						<label for="leftGoodAspects">Positive aspects of left hand side solution</label>
					</p>
					<textarea id="leftGoodAspects" name="leftGoodAspects" cols="60"
						rows="20"><%=adminReview.getLhsSolutionGoodAspects()%></textarea>

					<p class="contact">
						<label for="rightKey">Right hand side solution</label>
					</p>
					<input type="text" id="rightKey" name="rightKey" readonly="readonly"
						value="<%=adminReview.getRhsSolutionKey()%>">
					
					<p class="contact">
						<label for="rightBadAspects">Negative aspects of right hand side solution</label>
					</p>
					<textarea id="rightBadAspects" name="rightBadAspects" cols="60"
						rows="20"><%=adminReview.getRhSolutionBadAspects()%></textarea>

					<p class="contact">
						<label for="rightGoodAspects">Positive aspects of right hand side solution</label>
					</p>
					<textarea id="rightGoodAspects" name="rightGoodAspects" cols="60"
						rows="20"><%=adminReview.getRhsSolutionGoodAspects()%></textarea>
					

				</div>
			
			
		</div>
				
		</div>
		
		<div id="space">
		</div>
		
		<div style="width: 100%;">
		<div style="float: center; width: 50% margin: auto; border: 2px solid #73AD21;padding: 10px; 
		 height:150px;">
			<p class="contact">
				
				<input type="hidden" name="userName" id="userName" value="<%=userName%>">
			</p>
			<button type="submit">Back to Finished Reviews</button>
		</div>
		
		</div>
		</form>
	
</body>
</html>