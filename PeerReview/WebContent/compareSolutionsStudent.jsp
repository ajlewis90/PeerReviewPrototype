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
<style type="text/css">

#positiveAspects{
	position:relative;
}

#negativeAspects{
	position:relative;
}

#judgment{
	position:relative;
}

p.bottomRightCorner{
	position: absolute;
	bottom: 0;
	right: 10px;
	
}

p.bottomLeftCorner{
	position: absolute;
	bottom: 0;
	left: 10px;
	
}
</style>	

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	function showJustification(){
		document.getElementById('rankingJustification').style.display = "block";
	}
	
	function showReviewInstructions(){
		document.getElementById('hidden-instructionsDiv').style.display = "block";
	}
	
	function showBadHideGood(){
		document.getElementById('negativeAspects').style.display = "block";
		document.getElementById('positiveAspects').style.display = "none";
	}
	
	function showGoodHideBad(){
		document.getElementById('negativeAspects').style.display = "none";
		document.getElementById('positiveAspects').style.display = "block";
	}
	
	function showRankHideBad(){
		document.getElementById('negativeAspects').style.display = "none";
		document.getElementById('judgment').style.display = "block";
	}
	
	function showBadHideRank(){
		document.getElementById('negativeAspects').style.display = "block";
		document.getElementById('judgment').style.display = "none";
	}
	
	function hideReviewInstructions(){
		document.getElementById('hidden-instructionsDiv').style.display = "none";
	}
	
	function confirmComplete(){
		
		var answer = confirm("Are you sure about this?");
		
		if(answer == true){
			return true;
		}
		else{
			return false;
		}
			
	}
</script>

<title>Compare Sample Solutions</title>
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
		String problemNumber = session.getAttribute("Problem Number").toString();
		ProblemService problemService = new ProblemService();
		Problem problem = problemService.getProblem(problemNumber);
		
		String userName = session.getAttribute("userName").toString();
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
			feedback for the questions that follow. <a
				onClick="showReviewInstructions()"> <b><u>See Details</u></b></a>
		</p>
		<div class= "reviewContainer" id="hidden-instructionsDiv" style="display: none">
			<p><a
				onClick="hideReviewInstructions()"> <b><u>Hide Details</u></b></a><br>
				Consider the following aspects of the code by taking the following
				parameters into account:<br> <b>Code readability:</b> <br>
				- Does the code require any comments or is it easy to read on the
				first go.<br> - Are variable names meaningful and/or
				appropriately named? <br> - Check if white spaces are adequate
				enough to ensure a good read <br> - Are computations (if any)
				easy to read or would addition of parenthesis help? <br> <b>Code
					style: </b> <br> - Redundant loops and/or variable names <br>
				- What do you think about the logic/algorithm used? <br>
			</p>
		</div>
		<p>
			<b>Please note that both these solutions compile and work
				correctly</b>
		</p>
		<form name="compareSolutionsStudent" action="CompareSolutionsStudent"
			method="post" onSubmit="return confirmComplete()">
			<div style="width: 100%;">
				<div
					style="float: left; width: 45%; margin: auto; border: 2px solid #73AD21; padding: 10px;">

					<p class="contact">
						<label for="problemNumber">Problem Number</label>
					</p>
					<input id="problemNumber" name="problemNumber"
						tabindex="1" type="text" readonly="readonly"
						value="<%=sampleSolution1.getProblem_Number()%>">

					<p class="contact">
						<label for="leftSolutionNumber">Sample Solution Number</label>
					</p>
					<input id="leftSolutionNumber" name="leftSolutionNumber"
						tabindex="1" type="text" readonly="readonly"
						value="<%=sampleSolution1.getSolution_Number()%>">

					<input id="leftProblemSolutionPair" name="leftProblemSolutionPair"
						tabindex="1" type="hidden"
						value="<%=sampleSolution1.getProblem_Solution_Pair()%>">

					<p class="contact">
						<label for="leftSampleCode">Sample solution code</label>
					</p>
					<textarea id="leftSampleCode" name="leftSampleCode"  readonly="readonly"
						style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 200px; width: 450px;"><%=firstSampleCode%></textarea>

					<input type="hidden" id="leftUploadLocation"
						name="leftUploadLocation"
						value="<%=String.valueOf(sampleSolution1.getUpload_Location())%>" />

				</div>


				<div
					style="float: right; margin: auto; width: 45%; border: 2px solid #73AD21; padding: 10px;">

					<p class="contact">
						<label for="problemNumber">Problem Number</label>
					</p>
					<input id="problemNumber" name="problemNumber" tabindex="1"
						type="text" readonly="readonly"
						value="<%=sampleSolution2.getProblem_Number()%>">

					<p class="contact">
						<label for="rightSolutionNumber">Sample Solution Number</label>
					</p>
					<input id="rightSolutionNumber" name="rightSolutionNumber"
						tabindex="1" type="text" readonly="readonly"
						value="<%=sampleSolution2.getSolution_Number()%>">

					
					<input id="rightProblemSolutionPair"
						name="rightProblemSolutionPair" tabindex="1"
						type="hidden" readonly="readonly"
						value="<%=sampleSolution2.getProblem_Solution_Pair()%>">

					<p class="contact">
						<label for="rightSampleCode">Sample solution code</label>
					</p>
					<textarea id="rightSampleCode" name="rightSampleCode" readonly="readonly"
						style="resize: none; overflow-x:scroll; overflow-y: scroll; height: 200px; width: 450px;"><%=secondSampleCode%></textarea>

					
					<input type="hidden" id="rightUploadLocation"
						name="rightUploadLocation"
						value="<%=String.valueOf(sampleSolution2.getUpload_Location())%>" />

				</div>

			</div>

			<div id="space"></div>

			<div style="width: 100%; height:275px;margin: auto; padding: 10px;">
					<div id="positiveAspects" style="height:250px;margin: auto; border: 2px solid #73AD21; padding: 10px;">
						<div id = "lhsPositiveAspects" style="float: left; width: 350px; height: 200 px; margin:auto;
						margin-left:100px;">
						<p class="contact">
							<label for="leftGoodAspects">Positive aspects of Left
								side code</label>
						</p>
						<textarea id="leftGoodAspects" name="leftGoodAspects"
							style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 150px; width: 350px;"></textarea>
						</div>
						
						<div id="rhsGoodAspects" style="float: right; width: 350px; height: 200 px; margin:auto;
						margin-right:100px;">
						<p class="contact">
							<label for="rightGoodAspects">Positive aspects of Right
								side code</label>
						</p>
						<textarea id="rightGoodAspects" name="rightGoodAspects"
							style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 150px; width: 350px;"></textarea>
						</div>
						
						<p class = "bottomRightCorner"><a onClick="showBadHideGood()"> <b><u>View Negative Aspects</u></b></a></p>
					</div>
					
					
					<div id="negativeAspects" style="height:250px; margin: auto; border: 2px solid #73AD21; padding: 10px; display:none;">
						<div id = "lhsNegativeAspects" style="float: left; width: 350px; height: 200 px; margin:auto;
						margin-left:100px;">
						<p class="contact">
							<label for="leftBadAspects">Negative aspects of Left
								side code</label>
						</p>
						<textarea id="leftBadAspects" name="leftBadAspects"
							style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 150px; width: 350px;"></textarea>
						</div>
						
						<div id="rhsBadAspects" style="float: right; width: 350px; height: 200 px; margin:auto;
						margin-right:100px;">
						<p class="contact">
							<label for="rightBadAspects">Negative aspects of Right
								side code</label>
						</p>
						<textarea id="rightBadAspects" name="rightBadAspects"
							style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 150px; width: 350px;"></textarea>
						</div>
						<p class = "bottomLeftCorner"><a onClick="showGoodHideBad()"> <b><u>View Positive Aspects</u></b></a></p>
						<p class = "bottomRightCorner"><a onClick="showRankHideBad()"> <b><u>Review Judgment</u></b></a></p>
					</div>


				<div id="judgment"
					style="height: 250px; margin: auto; border: 2px solid #73AD21; padding: 10px; display: none;">
					<div id="rankingVerdict"
						style="float: left; width: 350px; height: 200 px; margin: auto; margin-left: 100px;">
						<p>
							<label>Choose better solution: </label> <br> <select
								name="betterSolution" id="betterSolution" size="1"
								onchange="showJustification()">
								<option>Please select the better solution</option>
								<option><%=sampleSolution1.getSolution_Number()%> is
									the better solution
								</option>
								<option><%=sampleSolution2.getSolution_Number()%> is
									the better solution
								</option>
								<option>Both
									<%=sampleSolution1.getSolution_Number()%> and
									<%=sampleSolution2.getSolution_Number()%> are of the same
									quality
								</option>
							</select>
						</p>
					</div>

					<div id="rankingJustification"
						style="display: none; float: right; width: 350px; height: 200 px; margin: auto; margin-right: 100px;">
						<p class="contact">
							<label for="justifyBetterSolution">Please give some
								justification for your choice</label><br>
							<textarea id="justifyBetterSolution" name="justifyBetterSolution"
								style="resize: none; overflow-x: scroll; overflow-y: scroll; height: 150px; width: 350px;"></textarea>
							<input type="hidden" name="userName" id="userName"
								value="<%=userName%>">

							<button type="submit">Finish Review</button>
						</p>
					</div>
					<p class = "bottomLeftCorner"><a onClick="showBadHideRank()"> <b><u>Negative Aspects</u></b></a></p>
					
				</div>
			</div>
				</form>
			</div>
		




</body>
</html>