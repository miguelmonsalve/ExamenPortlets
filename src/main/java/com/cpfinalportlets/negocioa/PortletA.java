package com.cpfinalportlets.negocioa;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.cpfinalportlets.entidades.Persona;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import javax.xml.namespace.QName;

public class PortletA extends GenericPortlet {

	protected String viewTemplate;

	private static Log _log = LogFactoryUtil.getLog(PortletA.class);
	
	
	public void init() {
		viewTemplate = getInitParameter("view-template");
	}

	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		include(viewTemplate, renderRequest, renderResponse);
	}

	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	@ProcessAction(name="enviarFormularioB")
	public void enviarFormularioB(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {
		
		String nombre = arg0.getParameter("nombre");
		String direccion = arg0.getParameter("direccion");
		Integer telefono = Integer.parseInt(arg0.getParameter("telefono"));
		Persona persona = new Persona(nombre, direccion, telefono);
		
		QName qname = new QName("http://cpfinalportlets.com", "datosformulariob", "x");
		arg1.setEvent(qname, persona);
	}
	
	@ProcessAction(name="enviarFormularioC")
	public void enviarFormularioC(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {
		
		String nombre = arg0.getParameter("nombre");
		String direccion = arg0.getParameter("direccion");
		Integer telefono = Integer.parseInt(arg0.getParameter("telefono"));
		Persona persona = new Persona(nombre, direccion, telefono);
		arg0.setAttribute("persona", persona);
	
		
		QName qname = new QName("http://cpfinalportlets.com", "datosformularioc", "x");
		arg1.setEvent(qname, persona);
		
	}

	@ProcessEvent(qname="{http://cpfinalportlets.com}datosformulariob")
    public void procesarFormularioB(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	
    	Event evento= arg0.getEvent();
    	Persona persona = (Persona) evento.getValue();
    	
    	
    	arg0.setAttribute("persona", persona);
    	
    	
    }
	
	@ProcessEvent(qname = "{http://cpfinalportlets.com}datosformularioc")
	public void procesarFormularioC(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {

		Event evento = arg0.getEvent();
		Persona persona = (Persona) evento.getValue();
		arg0.setAttribute("persona", persona);

	}
}
