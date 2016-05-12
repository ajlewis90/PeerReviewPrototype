<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*"%>   
<%@page import="org.arthur.review.model.Problem"%>
<%@page import="org.arthur.review.service.ProblemService"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Code Samples</title>
</head>
<body>
	<h1>File Upload Form</h1>
	
	<%
        	String problemNumber = request.getParameter("problemNumber");
        	
        	ProblemService psr = new ProblemService();
       		Problem problem = psr.getProblem(problemNumber);
       		//int oldCardStatusOID = card.getCard_status_oid();
       		//String oldCardStatusDescription = cs.mapCardStatusOIDToDescription(oldCardStatusOID);
       		//List<CardStatusDescription> csdList = cs.getCardStatusDescription();
        %>
	
    <fieldset>
        <legend>Upload File</legend>
        <form action="UploadSampleSolution" method="post" enctype="multipart/form-data">
        	<label for="problemNumber">Problem Number: </label>
        	<input id="problemNumber" type="text" name="problemNumber" readonly="readonly" value="<%=problem.getProblemNumber()%>"><br/> 
        	<label for="solutionNumber">Sample Solution Number: </label>
        	<input id="solutionNumber" type="text" name="solutionNumber" required=""/><br/> 
            <label for="fileName">Select File: </label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>            
            <input type="submit" value="Upload"/>
        </form>
    </fieldset>
</body>
</html>