/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.mindbodyonline.clients.svStaff;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mindbodyonline.clients.api._0_5 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClientServiceID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ID");
    private final static QName _ClientServiceActiveDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ActiveDate");
    private final static QName _ClientServicePaymentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "PaymentDate");
    private final static QName _ClientServiceExpirationDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ExpirationDate");
    private final static QName _LocationTax2_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Tax2");
    private final static QName _LocationFacilitySquareFeet_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FacilitySquareFeet");
    private final static QName _LocationBusinessID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "BusinessID");
    private final static QName _LocationLatitude_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Latitude");
    private final static QName _LocationTax1_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Tax1");
    private final static QName _LocationDistanceInMiles_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DistanceInMiles");
    private final static QName _LocationLongitude_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Longitude");
    private final static QName _LocationCanBook_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "CanBook");
    private final static QName _LocationHasSite_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "HasSite");
    private final static QName _LocationTreatmentRooms_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "TreatmentRooms");
    private final static QName _LocationTax5_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Tax5");
    private final static QName _LocationTax4_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Tax4");
    private final static QName _LocationTax3_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Tax3");
    private final static QName _SiteESA_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ESA");
    private final static QName _ClientIsCompany_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsCompany");
    private final static QName _ClientBirthDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "BirthDate");
    private final static QName _ClientFirstAppointmentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointmentDate");
    private final static QName _ClientLiabilityRelease_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LiabilityRelease");
    private final static QName _ClientIsProspect_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsProspect");
    private final static QName _ClientInactive_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Inactive");
    private final static QName _ClientEmailOptIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EmailOptIn");
    private final static QName _AppointmentEndDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDateTime");
    private final static QName _AppointmentStartDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDateTime");
    private final static QName _AppointmentStaffRequested_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StaffRequested");
    private final static QName _AppointmentFirstAppointment_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mindbodyonline.clients.api._0_5
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddOrUpdateStaff }
     * 
     */
    public AddOrUpdateStaff createAddOrUpdateStaff() {
        return new AddOrUpdateStaff();
    }

    /**
     * Create an instance of {@link AddOrUpdateStaffRequest }
     * 
     */
    public AddOrUpdateStaffRequest createAddOrUpdateStaffRequest() {
        return new AddOrUpdateStaffRequest();
    }

    /**
     * Create an instance of {@link GetStaffImgURL }
     * 
     */
    public GetStaffImgURL createGetStaffImgURL() {
        return new GetStaffImgURL();
    }

    /**
     * Create an instance of {@link GetStaffImgURLRequest }
     * 
     */
    public GetStaffImgURLRequest createGetStaffImgURLRequest() {
        return new GetStaffImgURLRequest();
    }

    /**
     * Create an instance of {@link GetStaffPermissions }
     * 
     */
    public GetStaffPermissions createGetStaffPermissions() {
        return new GetStaffPermissions();
    }

    /**
     * Create an instance of {@link GetStaffPermissionsRequest }
     * 
     */
    public GetStaffPermissionsRequest createGetStaffPermissionsRequest() {
        return new GetStaffPermissionsRequest();
    }

    /**
     * Create an instance of {@link AddOrUpdateStaffResponse }
     * 
     */
    public AddOrUpdateStaffResponse createAddOrUpdateStaffResponse() {
        return new AddOrUpdateStaffResponse();
    }

    /**
     * Create an instance of {@link AddOrUpdateStaffResult }
     * 
     */
    public AddOrUpdateStaffResult createAddOrUpdateStaffResult() {
        return new AddOrUpdateStaffResult();
    }

    /**
     * Create an instance of {@link GetStaffResponse }
     * 
     */
    public GetStaffResponse createGetStaffResponse() {
        return new GetStaffResponse();
    }

    /**
     * Create an instance of {@link GetStaffResult }
     * 
     */
    public GetStaffResult createGetStaffResult() {
        return new GetStaffResult();
    }

    /**
     * Create an instance of {@link GetStaff }
     * 
     */
    public GetStaff createGetStaff() {
        return new GetStaff();
    }

    /**
     * Create an instance of {@link GetStaffRequest }
     * 
     */
    public GetStaffRequest createGetStaffRequest() {
        return new GetStaffRequest();
    }

    /**
     * Create an instance of {@link GetStaffImgURLResponse }
     * 
     */
    public GetStaffImgURLResponse createGetStaffImgURLResponse() {
        return new GetStaffImgURLResponse();
    }

    /**
     * Create an instance of {@link GetStaffImgURLResult }
     * 
     */
    public GetStaffImgURLResult createGetStaffImgURLResult() {
        return new GetStaffImgURLResult();
    }

    /**
     * Create an instance of {@link GetStaffPermissionsResponse }
     * 
     */
    public GetStaffPermissionsResponse createGetStaffPermissionsResponse() {
        return new GetStaffPermissionsResponse();
    }

    /**
     * Create an instance of {@link GetStaffPermissionsResult }
     * 
     */
    public GetStaffPermissionsResult createGetStaffPermissionsResult() {
        return new GetStaffPermissionsResult();
    }

    /**
     * Create an instance of {@link SourceCredentials }
     * 
     */
    public SourceCredentials createSourceCredentials() {
        return new SourceCredentials();
    }

    /**
     * Create an instance of {@link ArrayOfClientIndex }
     * 
     */
    public ArrayOfClientIndex createArrayOfClientIndex() {
        return new ArrayOfClientIndex();
    }

    /**
     * Create an instance of {@link ArrayOfStaff }
     * 
     */
    public ArrayOfStaff createArrayOfStaff() {
        return new ArrayOfStaff();
    }

    /**
     * Create an instance of {@link StaffCredentials }
     * 
     */
    public StaffCredentials createStaffCredentials() {
        return new StaffCredentials();
    }

    /**
     * Create an instance of {@link ArrayOfClientRelationship }
     * 
     */
    public ArrayOfClientRelationship createArrayOfClientRelationship() {
        return new ArrayOfClientRelationship();
    }

    /**
     * Create an instance of {@link Appointment }
     * 
     */
    public Appointment createAppointment() {
        return new Appointment();
    }

    /**
     * Create an instance of {@link Unavailability }
     * 
     */
    public Unavailability createUnavailability() {
        return new Unavailability();
    }

    /**
     * Create an instance of {@link ArrayOfRep }
     * 
     */
    public ArrayOfRep createArrayOfRep() {
        return new ArrayOfRep();
    }

    /**
     * Create an instance of {@link Program }
     * 
     */
    public Program createProgram() {
        return new Program();
    }

    /**
     * Create an instance of {@link UserCredentials }
     * 
     */
    public UserCredentials createUserCredentials() {
        return new UserCredentials();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link Staff }
     * 
     */
    public Staff createStaff() {
        return new Staff();
    }

    /**
     * Create an instance of {@link ArrayOfStaffFilter }
     * 
     */
    public ArrayOfStaffFilter createArrayOfStaffFilter() {
        return new ArrayOfStaffFilter();
    }

    /**
     * Create an instance of {@link SessionType }
     * 
     */
    public SessionType createSessionType() {
        return new SessionType();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link ArrayOfPermission }
     * 
     */
    public ArrayOfPermission createArrayOfPermission() {
        return new ArrayOfPermission();
    }

    /**
     * Create an instance of {@link MBResult }
     * 
     */
    public MBResult createMBResult() {
        return new MBResult();
    }

    /**
     * Create an instance of {@link ArrayOfCustomClientField }
     * 
     */
    public ArrayOfCustomClientField createArrayOfCustomClientField() {
        return new ArrayOfCustomClientField();
    }

    /**
     * Create an instance of {@link Relationship }
     * 
     */
    public Relationship createRelationship() {
        return new Relationship();
    }

    /**
     * Create an instance of {@link ClientIndexValue }
     * 
     */
    public ClientIndexValue createClientIndexValue() {
        return new ClientIndexValue();
    }

    /**
     * Create an instance of {@link MBObject }
     * 
     */
    public MBObject createMBObject() {
        return new MBObject();
    }

    /**
     * Create an instance of {@link MBRequest }
     * 
     */
    public MBRequest createMBRequest() {
        return new MBRequest();
    }

    /**
     * Create an instance of {@link ArrayOfProgram }
     * 
     */
    public ArrayOfProgram createArrayOfProgram() {
        return new ArrayOfProgram();
    }

    /**
     * Create an instance of {@link ScheduleItem }
     * 
     */
    public ScheduleItem createScheduleItem() {
        return new ScheduleItem();
    }

    /**
     * Create an instance of {@link Availability }
     * 
     */
    public Availability createAvailability() {
        return new Availability();
    }

    /**
     * Create an instance of {@link ArrayOfResource }
     * 
     */
    public ArrayOfResource createArrayOfResource() {
        return new ArrayOfResource();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link ClientIndex }
     * 
     */
    public ClientIndex createClientIndex() {
        return new ClientIndex();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
    }

    /**
     * Create an instance of {@link Permission }
     * 
     */
    public Permission createPermission() {
        return new Permission();
    }

    /**
     * Create an instance of {@link ArrayOfLong }
     * 
     */
    public ArrayOfLong createArrayOfLong() {
        return new ArrayOfLong();
    }

    /**
     * Create an instance of {@link ClientCreditCard }
     * 
     */
    public ClientCreditCard createClientCreditCard() {
        return new ClientCreditCard();
    }

    /**
     * Create an instance of {@link ArrayOfInt }
     * 
     */
    public ArrayOfInt createArrayOfInt() {
        return new ArrayOfInt();
    }

    /**
     * Create an instance of {@link ClientService }
     * 
     */
    public ClientService createClientService() {
        return new ClientService();
    }

    /**
     * Create an instance of {@link ClientRelationship }
     * 
     */
    public ClientRelationship createClientRelationship() {
        return new ClientRelationship();
    }

    /**
     * Create an instance of {@link CustomClientField }
     * 
     */
    public CustomClientField createCustomClientField() {
        return new CustomClientField();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Rep }
     * 
     */
    public Rep createRep() {
        return new Rep();
    }

    /**
     * Create an instance of {@link ArrayOfUnavailability }
     * 
     */
    public ArrayOfUnavailability createArrayOfUnavailability() {
        return new ArrayOfUnavailability();
    }

    /**
     * Create an instance of {@link ArrayOfAppointment }
     * 
     */
    public ArrayOfAppointment createArrayOfAppointment() {
        return new ArrayOfAppointment();
    }

    /**
     * Create an instance of {@link ArrayOfAvailability }
     * 
     */
    public ArrayOfAvailability createArrayOfAvailability() {
        return new ArrayOfAvailability();
    }

    /**
     * Create an instance of {@link ArrayOfClientIndexValue }
     * 
     */
    public ArrayOfClientIndexValue createArrayOfClientIndexValue() {
        return new ArrayOfClientIndexValue();
    }

    /**
     * Create an instance of {@link ArrayOfLocation }
     * 
     */
    public ArrayOfLocation createArrayOfLocation() {
        return new ArrayOfLocation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientService.class)
    public JAXBElement<Long> createClientServiceID(Long value) {
        return new JAXBElement<Long>(_ClientServiceID_QNAME, Long.class, ClientService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ActiveDate", scope = ClientService.class)
    public JAXBElement<XMLGregorianCalendar> createClientServiceActiveDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClientServiceActiveDate_QNAME, XMLGregorianCalendar.class, ClientService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "PaymentDate", scope = ClientService.class)
    public JAXBElement<XMLGregorianCalendar> createClientServicePaymentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClientServicePaymentDate_QNAME, XMLGregorianCalendar.class, ClientService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ExpirationDate", scope = ClientService.class)
    public JAXBElement<XMLGregorianCalendar> createClientServiceExpirationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClientServiceExpirationDate_QNAME, XMLGregorianCalendar.class, ClientService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Tax2", scope = Location.class)
    public JAXBElement<Float> createLocationTax2(Float value) {
        return new JAXBElement<Float>(_LocationTax2_QNAME, Float.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Location.class)
    public JAXBElement<Integer> createLocationID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "FacilitySquareFeet", scope = Location.class)
    public JAXBElement<Integer> createLocationFacilitySquareFeet(Integer value) {
        return new JAXBElement<Integer>(_LocationFacilitySquareFeet_QNAME, Integer.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "BusinessID", scope = Location.class)
    public JAXBElement<Integer> createLocationBusinessID(Integer value) {
        return new JAXBElement<Integer>(_LocationBusinessID_QNAME, Integer.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Latitude", scope = Location.class)
    public JAXBElement<Double> createLocationLatitude(Double value) {
        return new JAXBElement<Double>(_LocationLatitude_QNAME, Double.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Tax1", scope = Location.class)
    public JAXBElement<Float> createLocationTax1(Float value) {
        return new JAXBElement<Float>(_LocationTax1_QNAME, Float.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DistanceInMiles", scope = Location.class)
    public JAXBElement<Double> createLocationDistanceInMiles(Double value) {
        return new JAXBElement<Double>(_LocationDistanceInMiles_QNAME, Double.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Longitude", scope = Location.class)
    public JAXBElement<Double> createLocationLongitude(Double value) {
        return new JAXBElement<Double>(_LocationLongitude_QNAME, Double.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "CanBook", scope = Location.class)
    public JAXBElement<Boolean> createLocationCanBook(Boolean value) {
        return new JAXBElement<Boolean>(_LocationCanBook_QNAME, Boolean.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "HasSite", scope = Location.class)
    public JAXBElement<Boolean> createLocationHasSite(Boolean value) {
        return new JAXBElement<Boolean>(_LocationHasSite_QNAME, Boolean.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "TreatmentRooms", scope = Location.class)
    public JAXBElement<Integer> createLocationTreatmentRooms(Integer value) {
        return new JAXBElement<Integer>(_LocationTreatmentRooms_QNAME, Integer.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Tax5", scope = Location.class)
    public JAXBElement<Float> createLocationTax5(Float value) {
        return new JAXBElement<Float>(_LocationTax5_QNAME, Float.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Tax4", scope = Location.class)
    public JAXBElement<Float> createLocationTax4(Float value) {
        return new JAXBElement<Float>(_LocationTax4_QNAME, Float.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Tax3", scope = Location.class)
    public JAXBElement<Float> createLocationTax3(Float value) {
        return new JAXBElement<Float>(_LocationTax3_QNAME, Float.class, Location.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = SessionType.class)
    public JAXBElement<Integer> createSessionTypeID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, SessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndex.class)
    public JAXBElement<Integer> createClientIndexID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClientIndex.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ESA", scope = Site.class)
    public JAXBElement<Boolean> createSiteESA(Boolean value) {
        return new JAXBElement<Boolean>(_SiteESA_QNAME, Boolean.class, Site.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "IsCompany", scope = Client.class)
    public JAXBElement<Boolean> createClientIsCompany(Boolean value) {
        return new JAXBElement<Boolean>(_ClientIsCompany_QNAME, Boolean.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "BirthDate", scope = Client.class)
    public JAXBElement<XMLGregorianCalendar> createClientBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClientBirthDate_QNAME, XMLGregorianCalendar.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "FirstAppointmentDate", scope = Client.class)
    public JAXBElement<XMLGregorianCalendar> createClientFirstAppointmentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClientFirstAppointmentDate_QNAME, XMLGregorianCalendar.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "LiabilityRelease", scope = Client.class)
    public JAXBElement<Boolean> createClientLiabilityRelease(Boolean value) {
        return new JAXBElement<Boolean>(_ClientLiabilityRelease_QNAME, Boolean.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "IsProspect", scope = Client.class)
    public JAXBElement<Boolean> createClientIsProspect(Boolean value) {
        return new JAXBElement<Boolean>(_ClientIsProspect_QNAME, Boolean.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Inactive", scope = Client.class)
    public JAXBElement<Boolean> createClientInactive(Boolean value) {
        return new JAXBElement<Boolean>(_ClientInactive_QNAME, Boolean.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EmailOptIn", scope = Client.class)
    public JAXBElement<Boolean> createClientEmailOptIn(Boolean value) {
        return new JAXBElement<Boolean>(_ClientEmailOptIn_QNAME, Boolean.class, Client.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Appointment.class)
    public JAXBElement<Long> createAppointmentID(Long value) {
        return new JAXBElement<Long>(_ClientServiceID_QNAME, Long.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EndDateTime", scope = Appointment.class)
    public JAXBElement<XMLGregorianCalendar> createAppointmentEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppointmentEndDateTime_QNAME, XMLGregorianCalendar.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartDateTime", scope = Appointment.class)
    public JAXBElement<XMLGregorianCalendar> createAppointmentStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppointmentStartDateTime_QNAME, XMLGregorianCalendar.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StaffRequested", scope = Appointment.class)
    public JAXBElement<Boolean> createAppointmentStaffRequested(Boolean value) {
        return new JAXBElement<Boolean>(_AppointmentStaffRequested_QNAME, Boolean.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "FirstAppointment", scope = Appointment.class)
    public JAXBElement<Boolean> createAppointmentFirstAppointment(Boolean value) {
        return new JAXBElement<Boolean>(_AppointmentFirstAppointment_QNAME, Boolean.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Staff.class)
    public JAXBElement<Long> createStaffID(Long value) {
        return new JAXBElement<Long>(_ClientServiceID_QNAME, Long.class, Staff.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Resource.class)
    public JAXBElement<Integer> createResourceID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, Resource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndexValue.class)
    public JAXBElement<Integer> createClientIndexValueID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClientIndexValue.class, value);
    }

}
