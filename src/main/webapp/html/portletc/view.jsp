<%@page import="com.cpfinalportlets.entidades.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<h1>Portlet C</h1>


<%
	Persona persona = (Persona) request.getAttribute("persona");
	String nombre = "";
	String direccion = "";
	String telefono = "";

	if (persona != null) {
		nombre = persona.getNombre();
		direccion = persona.getDireccion();
		telefono = persona.getTelefono().toString();

	} 
%>




<form action="" method="post">
	<div><input type="text" name="nombre" value="<%=nombre%>" /></div>
	<div><input type="text" name="direccion" value="<%=direccion%>" /></div>
	<div><input type="text" name="telefono" value="<%=telefono%>" /></div>
</form>