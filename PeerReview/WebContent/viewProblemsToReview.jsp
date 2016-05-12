<%@page import="org.arthur.review.model.SampleSolution"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.arthur.review.service.StudentRankingService"%>
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
        	String userName = request.getSession().getAttribute("userName").toString();

    		List<Problem> allProblems = new ArrayList<Problem>();
    		ProblemService problemService = new ProblemService();
    		
    		StudentRankingService studentRanker = new StudentRankingService();
    		
    		allProblems = problemService.getAllProblems();
    		
    		int numberOfSolutionsPerProblem = 0;
    		int problemReviewLimit = 0;
    		int actualReviewedCombinationCount = 0;
    		
    		List<Problem> problemsToReview = new ArrayList<Problem>();
    		
    		for (Problem problem : allProblems){
    			List<SampleSolution> sampleSolutions = new ArrayList<SampleSolution>();
    			
    			sampleSolutions = problemService.getProblemSpecificSampleSolutions(problem.getProblemNumber());
    			numberOfSolutionsPerProblem = sampleSolutions.size();
    			
    			problemReviewLimit = (numberOfSolutionsPerProblem * (numberOfSolutionsPerProblem - 1))/2;
    			System.out.println("problemReviewLimit count: " + problemReviewLimit);
    			
    			actualReviewedCombinationCount = studentRanker.getProblemReviewsForUser(userName, problem.getProblemNumber());
    			System.out.println("actualReviewedCombinationCount is: "+actualReviewedCombinationCount);
    			
    			if (actualReviewedCombinationCount < problemReviewLimit){
    				problemsToReview.add(problem);
    			}
    		}
        		
        		System.out.println("username is: " + userName);
        		ProblemService psr = new ProblemService();
            	//List<Problem> problemsList = (List<Problem>) request.getAttribute("ProblemsToReview");            	       
            	
            	for (Problem problem : problemsToReview) {
            		
       		 %>
       		 <tr>
					<td><%=problem.getProblemNumber()%></td>
					<td><%=problem.getProblemName()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="UserFirstSolutionListSelection">
								<input type="hidden" id="problemNumber" name="problemNumber" 
									value="<%=String.valueOf(problem.getProblemNumber())%>" />
								<input type="hidden" id="userName" name="userName" 
									value="<%=userName%>"/>
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