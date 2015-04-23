<%@page import="gatherer.constants.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gatherer.Get"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jsp test</title>


<%
	String[] s = new String[2];
	//s[0] = "MATCH (p:Product) RETURN DISTINCT p.type ORDER BY p.type ";
	
	s[0] = "productattributes" ;
	s[1] = "VX-2 Wired Controller, Gamepad" ;
	
	ArrayList<String> gOutput =  new ArrayList<String>() ; 
	// s[1] = "" ;
try {
	Get get =  new Get() ; 
	gOutput = get.start(s) ; 

} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>
<%= gOutput %> 


<%="testing jsp code"%>


<p>OK.</p>

<script type="text/javascript">
        var typeArray = [


<%for (int i=0; i<gOutput.size(); i++) {%>
	["<%=gOutput.get(i)%>"],
<%}%>
	["Harde Schijf"], [ "RAM geheugen" ], [ "Videokaart" ] ];

	var HSArray = [ [ "SSD 5000" ] ];

	var RAMARRAY = [ [ "RAM 800" ] ];

	var VIDArray = [ [ "VID 1020" ] ];

	function populate() {
		for (i = 0; i < typeArray.length; i++) {
			var select = document.getElementById("productTypes");
			select.options[select.options.length] = new Option(typeArray[i]);
		}
	}

	function fillProducts() {
		var e = document.getElementById("productTypes");
		var str = e.options[e.selectedIndex].value;
	
		if (str == "Harde Schijf") {
			for(i=0;i<HSArray.length;i++){
				var select = document.getElementById("productValues");
				select.options.length = 0;
				select.options[select.options.length] = new Option(HSArray[i]);
			}
		
		}

		if (str == "RAM geheugen") {
			for (i = 0; i < RAMARRAY.length; i++) {
				var select = document.getElementById("productValues");
				select.options.length = 0;
				select.options[select.options.length] = new Option(RAMARRAY[i]);
			}
		}
		if (str == "Videokaart") {
			for (i = 0; i < VIDArray.length; i++) {
				var select = document.getElementById("productValues");
				select.options.length = 0;
				select.options[select.options.length] = new Option(VIDArray[i]);
			}
		}
	}
</script>
</head>

<body onload="populate();">
<form method="post" action="dowat">
	
	<select  id="productTypes" onChange="fillProducts()" size="10"
		style="width: 200px;">
		<option >
		<button type="submit" ></option>
	</select>

	<select id="productValues" size="10" style="width: 200px;">
	</select>

	<select id="shoppingCart" size="10" style="width: 200px;">
	</select>
	<button type="submit" >
	post 
	</button>
	
</form>



</body>
</html>