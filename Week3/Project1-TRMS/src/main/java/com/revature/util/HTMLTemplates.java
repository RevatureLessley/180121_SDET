package com.revature.util;

import java.io.PrintWriter;

public class HTMLTemplates {
	public static void goBackButton(PrintWriter out){
		out.println(
				"<hr><input type='button' value='GO BACK' onclick='goBack()'>"
				+ "<script>function goBack(){ window.history.back(); }</script>"
				);
	}


	public static void navbar(PrintWriter out){
		out.println(
				"<div class=\"container-fluid\" style=\"margin-top: 50px\"\">\r\n" + 
				"		<nav class=\"navbar navbar-inverse navbar-fixed-top\">\r\n" + 
				"			<div class=\"container-fluid\">\r\n" + 
				"				<div class=\"navbar-header\">\r\n" + 
				"					<a class=\"navbar-brand\" href=\"#\">TRMS</a>\r\n" + 
				"				</div>\r\n" + 
				"				<ul class=\"nav navbar-nav\">\r\n" + 
				"					<li class=\"active\"><a href=\"index.html\">Home</a></li>\r\n" + 
				"					<li><a href=\"#\">Info</a></li>\r\n" + 
				"					<li><a href=\"ReimCo.html\">Reimbursement Coverage</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"				<ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"					<li><a href=\"registration.html\"><span\r\n" + 
				"							class=\"glyphicon glyphicon-user\"></span> Register</a></li>\r\n" + 
				"					<li><a href=\"login.html\"><span\r\n" + 
				"							class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</div>\r\n" + 
				"		</nav>"
				);
	}

	public static void headers (PrintWriter out) {
		out.println(
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- Latest compiled and minified CSS -->\r\n" + 
				"<link rel=\"stylesheet\"\r\n" + 
				"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				"\r\n" + 
				"<!-- jQuery library -->\r\n" + 
				"<script\r\n" + 
				"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				"\r\n" + 
				"<!-- Latest compiled JavaScript -->\r\n" + 
				"<script\r\n" + 
				"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				"	\r\n" + 
				"<script src=\"resource/javascript/AJAX.js\"></script>\r\n" + 
				"\r\n" + 
				"</head>"
				);
		
	}


}