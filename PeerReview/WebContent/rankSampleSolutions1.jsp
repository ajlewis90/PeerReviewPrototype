<%@page import="java.util.ArrayList"%>
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
<title>Select Samples to compare</title>
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
			<li><a href="viewAdminRankingList.jsp">View Finished Reviews</a>
				</li>
			<li class="navLogout"><a href="#">Options</a>
				<ul>
					<li><a href="logout.jsp">Logout</a></li>
				</ul></li>
		</ul>
	</div>



			<%
            //int id = Integer.parseInt(request.getParameter("updateId"));
				String problemNumber = request.getAttribute("Problem Number").toString();
            	List<SampleSolution> sampleSolutionSelectionList = (List<SampleSolution>) request.getAttribute("Available Solutions to review");
            	System.out.println("Available Solutions to review are in rank1jsp:" + sampleSolutionSelectionList);
            	List<String> sampleNumberList = new ArrayList<String>();
            	sampleNumberList.add(" ");
       			
            	for (SampleSolution sampleSolution : sampleSolutionSelectionList) {
					//System.out.println(sampleSolution.getSolution_Number());
					sampleNumberList.add(sampleSolution.getSolution_Number());
            	}
    
            	
            	ProblemService probService = new ProblemService();
            	Problem problem = probService.getProblem(problemNumber);
           
       		 %>
       		 
				<form method="post" action="SecondSolutionListSelection" name="firstform">
            	<div id="mystyle" class="myform">
               
                    <h1>Problem number <%=problemNumber%></h1>
                    <h2> First Solution Selection</h2>
                    <p>
						Select the sample solutions to compare for Problem :<%=problemNumber%></p>
                    <label>Problem Number<span class="small">Problem Number</span></label>
                    <input type="text" name="problemNumber" id="problemNumber" readonly="readonly" value="<%=problemNumber%>"/>
                    <label>Problem Name<span class="small">Problem Name</span></label>
                    <input type="text" name="problemName" id="problemName" readonly="readonly" value="<%=problem.getProblemName()%>"/>
                   
                    <label>Select first solution for comparison <span class="small">First Sample Solution</span></label>
                    <select name="firstSampleSolution" id="firstSampleSolution" size="1" onchange="this.form.submit()">
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