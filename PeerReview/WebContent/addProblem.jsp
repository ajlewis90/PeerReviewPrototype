<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/admin-home.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/add-user-registration-style.css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Problem</title>

<script type="text/javascript">

	function onValidate() {

		var cOwnerName = document.getElementById("cardOwnerName").value;
		var cardNumber = document.getElementById("cardNumber").value;
		var cardOwnerTrimmed = cOwnerName.trim();
		var result = true;

		if (cardOwnerTrimmed.length == 0 || !(isNaN(cardOwnerTrimmed))) {
			result = false;
			alert("Please ensure card owner's name is properly filled");
		}

		else if (isNaN(intFloat(cardNumber))) {
			result = false;
			alert("Please ensure card number contains decimal digits");
		}

		else {
			this.submit_button.disabled = true;
			result = true;
		}

		return result;
	}
</script>
</head>
<body>
	<div id="wrap">
		  <ul class="navbar">
			 <li><a href="adminhome.jsp">Home</a></li>
			 <li><a href="viewUsersList.jsp">View All Users</a></li>
			 <li><a href="#">Problems</a>
				<ul>
				   <li><a href="addProblem.jsp">Upload Problem</a></li>
				   <li><a href="viewProblemsList.jsp">View List of Problems</a></li>				
				</ul>         
			 </li>
			 
			 <li class="navLogout"><a href="#">Options</a>
				<ul>
				   <li><a href="logout.jsp">Logout</a></li>
				</ul>         
			 </li>
		  </ul>
	</div>
	
	<jsp:useBean id="user" class="org.arthur.review.model.User" scope="session">
		<jsp:setProperty property="userName" name="user"/>
	</jsp:useBean>
	
	<div class="form">
    		<form id="addProblemForm" action="AddNewProblem" method="post" onSubmit="return onValidate()"> 
    			<p class="contact"><label for="problemNumber">Problem Number</label></p> 
    			<input id="problemNumber" name="problemNumber" placeholder="P1" required="" tabindex="1" type="text">     			     		
    			 		
    			<p class="contact"><label for="problemName">Problem Name</label></p> 
    			<input id="problemName" name="problemName" placeholder="function_name()" required="" tabindex="1" type="text">
    			 
                <p class="contact"><label for="password">Enter a brief description of the problem</label></p> 
    			<textarea id="problemDescription" name="problemDescription" cols="40" rows="5" required=""></textarea> 
    			
    			
       	 	 	<p class="contact"></p>
            	<input class="buttom" name="submit" id="submit" tabindex="5" value="Add Problem!" type="submit" > 	 
   				</form>
   			
   	</div>
</body>
</html>