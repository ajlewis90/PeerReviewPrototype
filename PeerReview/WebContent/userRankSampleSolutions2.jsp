<%@page import="org.arthur.review.pseudomodel.ProblemWithSamplesView"%>
<%@page import="org.arthur.review.model.SampleSolution"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.Problem"%>
<%@page import="org.arthur.review.model.User"%>
<%@page import="org.arthur.review.service.ProblemService"%>
<%@page import="org.arthur.review.service.AdminRankingService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style-copy.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/admin-home.css"
	media="all" />
	

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Samples to compare</title>
</head>
<body>
	<div id="wrap">
		<ul class="navbar">
			<li><a href="studentHome.jsp">Home</a></li>
			<li><a href="viewProblemsToReview.jsp">Problems to Review</a></li>
			<li><a href="viewStudentRankingList.jsp">Finished Reviews</a></li>
			<li id="navLogout"><a href="#">Options</a>
				<ul>
					<li><a href="logout.jsp">Logout</a></li>
				</ul></li>
		</ul>
	</div>

	<%
		//int id = Integer.parseInt(request.getParameter("updateId"));
		String userName = session.getAttribute("userName").toString();
		String problemNumber = request.getAttribute("Problem Number").toString();
		String firstSampleSolution = request.getAttribute("First Sample Solution Number").toString();
		List<SampleSolution> sampleSolutionSelectionList = (List<SampleSolution>) request
				.getAttribute("Available Solutions to review");
		List<String> sampleNumberList = new ArrayList<String>();
		sampleNumberList.add(" ");

		for (SampleSolution sampleSolution : sampleSolutionSelectionList) {
			//System.out.println(sampleSolution.getSolution_Number());
			sampleNumberList.add(sampleSolution.getSolution_Number());
		}

		ProblemService probService = new ProblemService();
		Problem problem = probService.getProblem(problemNumber);
	%>

	<form method="post" action="LeftRightOrderDecision" name="firstform">
            	<div id="mystyle" class="myform">
               
                    <h1>Problem number <%=problemNumber%></h1>
                    <h2>Second Solution Selection</h2>
                    <p>
						Select the solutions to compare for Problem :<%=problemNumber%></p>
                    <label>Problem Number<span class="small">Problem Number</span></label>
                    <input type="text" name="problemNumber" id="problemNumber" readonly="readonly" value="<%=problemNumber%>"/>
                    <label>Problem Name<span class="small">Problem Name</span></label>
                    <input type="text" name="problemName" id="problemName" readonly="readonly" value="<%=problem.getProblemName()%>"/>
                    <label>First solution selected <span class="small">First Solution</span></label>
                    <input type="text" name="firstSampleSolution" id="firstSampleSolution" readonly="readonly" value="<%=firstSampleSolution%>"/> 
                    
                    
                    <input type="hidden" name="userName" id="userName" value="<%=userName%>"/><br>
                                                                                            
                    <label>Select second solution for comparison <span class="small">Second Solution</span></label>
                    <select name="secondSampleSolution" id="secondSampleSolution" size="1" onchange="this.form.submit()">
        				<%for (String sampleNumber : sampleNumberList) {
        					System.out.println(sampleNumber);        				
        				%> 
        				<option><%=String.valueOf(sampleNumber)%></option>
        				<%
                        	}
                    	%>
       	 			</select>
                    
                    <div class="spacer"></div>
              
            </div>
            </form>
		
</body>
</html>