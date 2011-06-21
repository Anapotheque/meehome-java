<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page session="false"%><%@page import="java.text.SimpleDateFormat,java.util.Date,java.util.Enumeration,java.util.Iterator"%><%
/*
 * Page de test utilisée par les équipements Cisco CSS de répartition de charge.
 */

response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires", 0);

String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
String appName = "GFB";

String nodeName = "";
try { 
	nodeName = java.net.InetAddress.getLocalHost().getHostName();
}
catch (java.net.UnknownHostException uhex){
	nodeName = "ERROR: Can't determine local host name " + uhex.getMessage();
}
%><html>
<head><title>Availability of Application <%=appName%> on Node <%=nodeName%></title></head>
<body>
Availability test<br/>
Application: <%=appName%><br/>
Node: <%=nodeName%><br/>
Timestamp: <%=timestamp%>
</body>
</html>