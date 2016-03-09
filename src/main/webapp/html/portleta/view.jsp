<%@page import="com.cpfinalportlets.entidades.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<h1>Portlet A</h1>

<%
Persona persona = (Persona) request.getAttribute("persona");
String nombre = "Nombre";
String direccion = "Direccion";
String telefono = "Telefono";

if (persona != null) {
	nombre = persona.getNombre();
	direccion = persona.getDireccion();
	telefono = persona.getTelefono().toString();

} 



%>


<portlet:actionURL name="enviarFormularioB" var="urlenviarFormularioB"/>
<portlet:actionURL name="enviarFormularioC" var="urlenviarFormularioC"/>

<form action ="<%=urlenviarFormularioB%>" method="post">
	<div>Nombre <input type="text" name="nombre" value="<%=nombre%>"/></div>
	<div>Direccion <input type="text" name="direccion" value="<%=direccion%>"/></div>
	<div>Telefono <input type="number" name="telefono" value="<%=telefono%>"/></div>
	<input type="submit" name="Enviar a B" value="Enviar a B" formaction="<%=urlenviarFormularioB%>" />
	<input type="submit" name="Enviar a C" value="Enviar a C" formaction="<%=urlenviarFormularioC%>" />
</form>

