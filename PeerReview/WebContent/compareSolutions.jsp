<%@page import="org.arthur.review.pseudomodel.ProblemWithSamplesView"%>
<%@page import="org.arthur.review.model.SampleSolution"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.Problem"%>
<%@page import="org.arthur.review.model.User"%>
<%@page import="org.arthur.review.service.ProblemService"%>
<%@page import="org.arthur.review.service.AdminRankingService"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/admin-home.css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

	function showJustification() {
		document.getElementById('hidden-div').style.display = "block";
	}

	function showReviewInstructions() {
		document.getElementById('hidden-instructionsDiv').style.display = "block";
	}

	function confirmComplete() {

		var answer = confirm("Please click 'OK' if you want to submit your review for these two solutions or"
				+ " click 'Cancel' if you want to make any changes to your review.");

		if (answer == true) {
			return true;
		} else {
			return false;
		}

	}
</script>

<title>Compare Sample Solutions</title>
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
			<li><a href="viewAdminRankingList.jsp">View Completed Reviews</a>
				</li>
			<li class="navLogout"><a href="#">Options</a>
				<ul>
					<li><a href="logout.jsp">Logout</a></li>
				</ul></li>
		</ul>
	</div>
	<%
		String problemNumber = session.getAttribute("Problem Number").toString();
		ProblemService problemService = new ProblemService();
		Problem problem = problemService.getProblem(problemNumber);
	
		String firstSampleCode = session.getAttribute("Code of LHS").toString();
		String secondSampleCode = session.getAttribute("Code of RHS").toString();
		
		String firstSample = session.getAttribute("first sample").toString();
		String secondSample = session.getAttribute("second sample").toString();
		
		ProblemService probService = new ProblemService();
		SampleSolution sampleSolution1 = probService.getSampleSolution(firstSample);
		SampleSolution sampleSolution2 = probService.getSampleSolution(secondSample);
	%>
	<div style="width: 100%;">
		<p>
			Given below are two student solutions for a Boolean function <b><%=problem.getProblemName()%></b>().
			<br>
			<%=problem.getProblemDescription()%>
			<br> Carefully compare and analyse each solution to give your
			feedback for the questions that follow. <br> <a
				onClick="showReviewInstructions()"> <b><u> Click here for details</u></b></a>
		</p>
		<div id="hidden-instructionsDiv" style="display: none">
			<p>
				Consider the following aspects of the code by taking the following
				parameters into account:<br> <b>Code readability:</b> <br> - Does the code require any comments or is it
				easy to read on the first go.<br> - Are variable names meaningful
				and/or appropriately named? <br> - Check if white spaces are
				adequate enough to ensure a good read <br> - Are computations (if
				any) easy to read or would addition of parenthesis help? <br> <b>Code
					style: </b>  <br> - Redundant loops and/or
				variable names <br> - What do you think about the
				logic/algorithm used? <br>
			</p>
		</div>
		<p>
			<b>Please note that both these solutions compile and work
				correctly</b>
		</p>
	<form name="compareSolutions" action="CompareSolutions" method="post" onSubmit = "return confirmComplete()">
	<div style="width: 100%;">
		<div style="float: left; width: 50% margin: auto; border: 2px solid #73AD21;padding: 10px;">
			
			<p class="contact">
				<label for="problemNumber">Problem Number</label>
			</p>
			<input id="problemNumber" name="problemNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution1.getProblem_Number()%>">
			
			<p class="contact">
				<label for="leftSolutionNumber">Sample Solution Number</label>
			</p>
			<input id="leftSolutionNumber" name="leftSolutionNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution1.getSolution_Number()%>">
			
			<p class="contact">
				<label for="leftProblemSolutionPair">Problem Solution Pair</label>
			</p>
			<input id="leftProblemSolutionPair" name="leftProblemSolutionPair" placeholder="P1"
				tabindex="1" type="hidden"
				value="<%=sampleSolution1.getProblem_Solution_Pair()%>">

			<p class="contact">
				<label for="leftSampleCode">Sample solution code</label>
			</p>
			<textarea id="leftSampleCode" name="leftSampleCode" cols="60" rows="50"
				readonly="readonly"><%=firstSampleCode%></textarea>
			
			<p class="contact">
				<label for="leftBadAspects">Negative aspects of code</label>
			</p>
			<textarea id="leftBadAspects" name="leftBadAspects" cols="60" rows="20"><%=sampleSolution1.getBad_Aspects()%></textarea>
			
			<p class="contact">
				<label for="leftGoodAspects">Positive aspects of code</label>
			</p>
			<textarea id="leftGoodAspects" name="leftGoodAspects" cols="60" rows="20"><%=sampleSolution1.getGood_Apects()%></textarea>
			
			<input type="hidden" id="leftUploadLocation" name="leftUploadLocation" 
									value="<%=String.valueOf(sampleSolution1.getUpload_Location())%>" />  
			
			
		</div>
		<div style="float: right; width: 50% width: 50% margin: auto; border: 2px solid #73AD21; padding: 10px;">
				
			<p class="contact">
				<label for="problemNumber">Problem Number</label>
			</p>
			<input id="problemNumber" name="problemNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution2.getProblem_Number()%>">
			
			<p class="contact">
				<label for="rightSolutionNumber">Sample Solution Number</label>
			</p>
			<input id="rightSolutionNumber" name="rightSolutionNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution2.getSolution_Number()%>">
			
			<p class="contact">
				<label for="rightProblemSolutionPair">Problem Solution Pair</label>
			</p>
			<input id="rightProblemSolutionPair" name="rightProblemSolutionPair" placeholder="P1"
				tabindex="1" type="hidden" readonly=""
				value="<%=sampleSolution2.getProblem_Solution_Pair()%>">

			<p class="contact">
				<label for="rightSampleCode">Sample solution code</label>
			</p>
			<textarea id="rightSampleCode" name="rightSampleCode" cols="60" rows="50"
				readonly="readonly"><%=secondSampleCode%></textarea>
			
			<p class="contact">
				<label for="rightBadAspects">Negative aspects of code</label>
			</p>
			<textarea id="rightBadAspects" name="rightBadAspects" cols="60" rows="20"><%=sampleSolution2.getBad_Aspects()%></textarea>
			
			<p class="contact">
				<label for="rightGoodAspects">Positive aspects of code</label>
			</p>
			<textarea id="rightGoodAspects" name="rightGoodAspects" cols="60" rows="20"><%=sampleSolution2.getGood_Apects()%></textarea>
			
			<input type="hidden" id="rightUploadLocation" name="rightUploadLocation" 
									value="<%=String.valueOf(sampleSolution2.getUpload_Location())%>" />  
			
			
			
		</div>
				
		</div>
		
		<div id="space">
		</div>
		
		<div style="width: 100%;">
		<div style="float: left; width: 50% margin: auto; border: 2px solid #73AD21;padding: 10px; 
		 height:150px;">
			<p>
			<label>Choose better solution: </label> <br>
                    <select name="betterSolution" id="betterSolution" size="1" onchange="showJustification()">
        				<option>Please select the better solution</option>
        				<option><%=sampleSolution1.getSolution_Number()%> is the better solution</option>
        				<option><%=sampleSolution2.getSolution_Number()%> is the better solution</option>
        				<option>Both <%=sampleSolution1.getSolution_Number()%> and <%=sampleSolution2.getSolution_Number()%> are of the same quality</option>
       	 			</select>
			</p>
		</div>
		
		<div id="hidden-div" style="float: right; width: 50% margin: auto; border: 2px solid #73AD21;padding: 10px; 
		 height:150px; display:none">
			<p class="contact">
				<label for="justifyBetterSolution">Please give some justification for your choice</label><br>
				<textarea id="justifyBetterSolution" name="justifyBetterSolution" cols="60" rows="6"></textarea>
			</p>
			<button type="submit">Finish Review</button>
		</div>
		
		</div>
		</form>
	</div>
	    
	
</body>
</html>