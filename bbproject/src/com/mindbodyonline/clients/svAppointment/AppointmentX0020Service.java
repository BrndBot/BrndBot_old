
package com.mindbodyonline.clients.svAppointment;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Provides methods and attributes relating to appointments.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Appointment_x0020_Service", targetNamespace = "http://clients.mindbodyonline.com/api/0_5", wsdlLocation = "https://api.mindbodyonline.com/0_5/AppointmentService.asmx?wsdl")
public class AppointmentX0020Service
    extends Service
{

    private final static URL APPOINTMENTX0020SERVICE_WSDL_LOCATION;
    private final static WebServiceException APPOINTMENTX0020SERVICE_EXCEPTION;
    private final static QName APPOINTMENTX0020SERVICE_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Appointment_x0020_Service");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://api.mindbodyonline.com/0_5/AppointmentService.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        APPOINTMENTX0020SERVICE_WSDL_LOCATION = url;
        APPOINTMENTX0020SERVICE_EXCEPTION = e;
    }

    public AppointmentX0020Service() {
        super(__getWsdlLocation(), APPOINTMENTX0020SERVICE_QNAME);
    }

    public AppointmentX0020Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), APPOINTMENTX0020SERVICE_QNAME, features);
    }

    public AppointmentX0020Service(URL wsdlLocation) {
        super(wsdlLocation, APPOINTMENTX0020SERVICE_QNAME);
    }

    public AppointmentX0020Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, APPOINTMENTX0020SERVICE_QNAME, features);
    }

    public AppointmentX0020Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AppointmentX0020Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AppointmentX0020ServiceSoap
     */
    @WebEndpoint(name = "Appointment_x0020_ServiceSoap")
    public AppointmentX0020ServiceSoap getAppointmentX0020ServiceSoap() {
        return super.getPort(new QName("http://clients.mindbodyonline.com/api/0_5", "Appointment_x0020_ServiceSoap"), AppointmentX0020ServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AppointmentX0020ServiceSoap
     */
    @WebEndpoint(name = "Appointment_x0020_ServiceSoap")
    public AppointmentX0020ServiceSoap getAppointmentX0020ServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://clients.mindbodyonline.com/api/0_5", "Appointment_x0020_ServiceSoap"), AppointmentX0020ServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (APPOINTMENTX0020SERVICE_EXCEPTION!= null) {
            throw APPOINTMENTX0020SERVICE_EXCEPTION;
        }
        return APPOINTMENTX0020SERVICE_WSDL_LOCATION;
    }

}
