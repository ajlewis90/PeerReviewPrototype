<%@page import="java.io.IOException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.arthur.review.model.SampleSolution"%>
<%@page import="org.arthur.review.model.User"%>
<%@page import="org.arthur.review.service.ProblemService"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Sample Solution</title>
</head>
<body>
	<%		
			BufferedReader reader = null;
			String problemSolutionPair = request.getParameter("problemSolutionPair");
			ProblemService psr = new ProblemService();
			SampleSolution sampleSolution = psr.getSampleSolution(problemSolutionPair);	
			//FileReader fileReader = new FileReader(sampleSolution.getUpload_Location());
			try{
				reader = new BufferedReader(new FileReader(sampleSolution.getUpload_Location()));
			
				StringBuilder sBuilder = new StringBuilder();
				String line;
				int lineNumber = 0;
			
				while((line = reader.readLine())!=null){
					lineNumber++;
					sBuilder.append(lineNumber + " " + line + "\n");
					//sBuilder.append(line + "\n");
				}
			
				String code = sBuilder.toString();
			//System.out.println(code);
			//reader.close();
      %>

	<div class="form">
		<form id="addProblemForm" action="EditSampleSolution" method="post"
			onSubmit="">
			
			<p class="contact">
				<label for="problemNumber">Problem Number</label>
			</p>
			<input id="problemNumber" name="problemNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution.getProblem_Number()%>">
			
			<p class="contact">
				<label for="solutionNumber">Sample Solution Number</label>
			</p>
			<input id="solutionNumber" name="solutionNumber" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution.getSolution_Number()%>">
			
			<p class="contact">
				<label for="problemSolutionPair">Problem Solution Pair</label>
			</p>
			<input id="problemSolutionPair" name="problemSolutionPair" placeholder="P1"
				tabindex="1" type="text" readonly="readonly"
				value="<%=sampleSolution.getProblem_Solution_Pair()%>">

			<p class="contact">
				<label for="sampleCode">Sample solution code</label>
			</p>
			<textarea id="sampleCode" name="sampleCode" cols="60" rows="50"
				readonly="readonly"><%=code%></textarea>
			
			<p class="contact">
				<label for="badAspects">Negative aspects of code</label>
			</p>
			<textarea id="badAspects" name="badAspects" cols="60" rows="20"><%=sampleSolution.getBad_Aspects()%></textarea>
			
			<p class="contact">
				<label for="goodAspects">Positive aspects of code</label>
			</p>
			<textarea id="goodAspects" name="goodAspects" cols="60" rows="20"><%=sampleSolution.getGood_Apects()%></textarea>
			
			<input type="hidden" id="uploadLocation" name="uploadLocation" 
									value="<%=String.valueOf(sampleSolution.getUpload_Location())%>" />  
			
			<p class="contact"></p>
			<input class="buttom" name="submit" id="submit" tabindex="5"
				value="Update Sample Solution Specifics" type="submit">
		</form>

	</div>

	<%
			}catch (IOException e){
				e.printStackTrace();
			}finally{
   				try{
   					if(reader != null){
   						reader.close();
   					}
   				}catch (IOException ex){
   					ex.printStackTrace();
   				}
   		}
   	%>
</body>
</html>