/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svClass;

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
    private final static QName _ClassEndDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDateTime");
    private final static QName _ClassStartDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDateTime");
    private final static QName _ClassIsAvailable_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsAvailable");
    private final static QName _ClassSemesterID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SemesterID");
    private final static QName _ClassScheduleDaySunday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DaySunday");
    private final static QName _ClassScheduleEndTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndTime");
    private final static QName _ClassScheduleStartTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartTime");
    private final static QName _ClassScheduleDayFriday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayFriday");
    private final static QName _ClassScheduleDaySaturday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DaySaturday");
    private final static QName _ClassScheduleDayTuesday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayTuesday");
    private final static QName _ClassScheduleDayMonday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayMonday");
    private final static QName _ClassScheduleEndDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDate");
    private final static QName _ClassScheduleStartDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDate");
    private final static QName _ClassScheduleDayWednesday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayWednesday");
    private final static QName _ClassScheduleDayThursday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayThursday");
    private final static QName _ClientIsCompany_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsCompany");
    private final static QName _ClientBirthDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "BirthDate");
    private final static QName _ClientFirstAppointmentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointmentDate");
    private final static QName _ClientLiabilityRelease_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LiabilityRelease");
    private final static QName _ClientIsProspect_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsProspect");
    private final static QName _ClientInactive_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Inactive");
    private final static QName _ClientEmailOptIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EmailOptIn");
    private final static QName _ClassDescriptionLastUpdated_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LastUpdated");
    private final static QName _AppointmentStaffRequested_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StaffRequested");
    private final static QName _AppointmentFirstAppointment_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointment");
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
    private final static QName _VisitMakeUp_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "MakeUp");
    private final static QName _VisitSignedIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SignedIn");
    private final static QName _SiteESA_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ESA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mindbodyonline.clients.api._0_5
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoveFromWaitlist }
     * 
     */
    public RemoveFromWaitlist createRemoveFromWaitlist() {
        return new RemoveFromWaitlist();
    }

    /**
     * Create an instance of {@link RemoveFromWaitlistRequest }
     * 
     */
    public RemoveFromWaitlistRequest createRemoveFromWaitlistRequest() {
        return new RemoveFromWaitlistRequest();
    }

    /**
     * Create an instance of {@link AddClientsToEnrollmentsResponse }
     * 
     */
    public AddClientsToEnrollmentsResponse createAddClientsToEnrollmentsResponse() {
        return new AddClientsToEnrollmentsResponse();
    }

    /**
     * Create an instance of {@link AddClientsToEnrollmentsResult }
     * 
     */
    public AddClientsToEnrollmentsResult createAddClientsToEnrollmentsResult() {
        return new AddClientsToEnrollmentsResult();
    }

    /**
     * Create an instance of {@link GetSemesters }
     * 
     */
    public GetSemesters createGetSemesters() {
        return new GetSemesters();
    }

    /**
     * Create an instance of {@link GetSemestersRequest }
     * 
     */
    public GetSemestersRequest createGetSemestersRequest() {
        return new GetSemestersRequest();
    }

    /**
     * Create an instance of {@link GetClassesResponse }
     * 
     */
    public GetClassesResponse createGetClassesResponse() {
        return new GetClassesResponse();
    }

    /**
     * Create an instance of {@link GetClassesResult }
     * 
     */
    public GetClassesResult createGetClassesResult() {
        return new GetClassesResult();
    }

    /**
     * Create an instance of {@link GetClasses }
     * 
     */
    public GetClasses createGetClasses() {
        return new GetClasses();
    }

    /**
     * Create an instance of {@link GetClassesRequest }
     * 
     */
    public GetClassesRequest createGetClassesRequest() {
        return new GetClassesRequest();
    }

    /**
     * Create an instance of {@link GetCourses }
     * 
     */
    public GetCourses createGetCourses() {
        return new GetCourses();
    }

    /**
     * Create an instance of {@link GetCoursesRequest }
     * 
     */
    public GetCoursesRequest createGetCoursesRequest() {
        return new GetCoursesRequest();
    }

    /**
     * Create an instance of {@link AddClientsToClasses }
     * 
     */
    public AddClientsToClasses createAddClientsToClasses() {
        return new AddClientsToClasses();
    }

    /**
     * Create an instance of {@link AddClientsToClassesRequest }
     * 
     */
    public AddClientsToClassesRequest createAddClientsToClassesRequest() {
        return new AddClientsToClassesRequest();
    }

    /**
     * Create an instance of {@link GetClassDescriptions }
     * 
     */
    public GetClassDescriptions createGetClassDescriptions() {
        return new GetClassDescriptions();
    }

    /**
     * Create an instance of {@link GetClassDescriptionsRequest }
     * 
     */
    public GetClassDescriptionsRequest createGetClassDescriptionsRequest() {
        return new GetClassDescriptionsRequest();
    }

    /**
     * Create an instance of {@link GetClassSchedules }
     * 
     */
    public GetClassSchedules createGetClassSchedules() {
        return new GetClassSchedules();
    }

    /**
     * Create an instance of {@link GetClassSchedulesRequest }
     * 
     */
    public GetClassSchedulesRequest createGetClassSchedulesRequest() {
        return new GetClassSchedulesRequest();
    }

    /**
     * Create an instance of {@link GetEnrollmentsResponse }
     * 
     */
    public GetEnrollmentsResponse createGetEnrollmentsResponse() {
        return new GetEnrollmentsResponse();
    }

    /**
     * Create an instance of {@link GetEnrollmentsResult }
     * 
     */
    public GetEnrollmentsResult createGetEnrollmentsResult() {
        return new GetEnrollmentsResult();
    }

    /**
     * Create an instance of {@link AddClientsToEnrollments }
     * 
     */
    public AddClientsToEnrollments createAddClientsToEnrollments() {
        return new AddClientsToEnrollments();
    }

    /**
     * Create an instance of {@link AddClientsToEnrollmentsRequest }
     * 
     */
    public AddClientsToEnrollmentsRequest createAddClientsToEnrollmentsRequest() {
        return new AddClientsToEnrollmentsRequest();
    }

    /**
     * Create an instance of {@link GetClassVisits }
     * 
     */
    public GetClassVisits createGetClassVisits() {
        return new GetClassVisits();
    }

    /**
     * Create an instance of {@link GetClassVisitsRequest }
     * 
     */
    public GetClassVisitsRequest createGetClassVisitsRequest() {
        return new GetClassVisitsRequest();
    }

    /**
     * Create an instance of {@link GetClassVisitsResponse }
     * 
     */
    public GetClassVisitsResponse createGetClassVisitsResponse() {
        return new GetClassVisitsResponse();
    }

    /**
     * Create an instance of {@link GetClassVisitsResult }
     * 
     */
    public GetClassVisitsResult createGetClassVisitsResult() {
        return new GetClassVisitsResult();
    }

    /**
     * Create an instance of {@link GetSemestersResponse }
     * 
     */
    public GetSemestersResponse createGetSemestersResponse() {
        return new GetSemestersResponse();
    }

    /**
     * Create an instance of {@link GetSemestersResult }
     * 
     */
    public GetSemestersResult createGetSemestersResult() {
        return new GetSemestersResult();
    }

    /**
     * Create an instance of {@link UpdateClientVisitsResponse }
     * 
     */
    public UpdateClientVisitsResponse createUpdateClientVisitsResponse() {
        return new UpdateClientVisitsResponse();
    }

    /**
     * Create an instance of {@link UpdateClientVisitsResult }
     * 
     */
    public UpdateClientVisitsResult createUpdateClientVisitsResult() {
        return new UpdateClientVisitsResult();
    }

    /**
     * Create an instance of {@link GetClassSchedulesResponse }
     * 
     */
    public GetClassSchedulesResponse createGetClassSchedulesResponse() {
        return new GetClassSchedulesResponse();
    }

    /**
     * Create an instance of {@link GetClassSchedulesResult }
     * 
     */
    public GetClassSchedulesResult createGetClassSchedulesResult() {
        return new GetClassSchedulesResult();
    }

    /**
     * Create an instance of {@link RemoveClientsFromClassesResponse }
     * 
     */
    public RemoveClientsFromClassesResponse createRemoveClientsFromClassesResponse() {
        return new RemoveClientsFromClassesResponse();
    }

    /**
     * Create an instance of {@link RemoveClientsFromClassesResult }
     * 
     */
    public RemoveClientsFromClassesResult createRemoveClientsFromClassesResult() {
        return new RemoveClientsFromClassesResult();
    }

    /**
     * Create an instance of {@link GetWaitlistEntriesResponse }
     * 
     */
    public GetWaitlistEntriesResponse createGetWaitlistEntriesResponse() {
        return new GetWaitlistEntriesResponse();
    }

    /**
     * Create an instance of {@link GetWaitlistEntriesResult }
     * 
     */
    public GetWaitlistEntriesResult createGetWaitlistEntriesResult() {
        return new GetWaitlistEntriesResult();
    }

    /**
     * Create an instance of {@link GetWaitlistEntries }
     * 
     */
    public GetWaitlistEntries createGetWaitlistEntries() {
        return new GetWaitlistEntries();
    }

    /**
     * Create an instance of {@link GetWaitlistEntriesRequest }
     * 
     */
    public GetWaitlistEntriesRequest createGetWaitlistEntriesRequest() {
        return new GetWaitlistEntriesRequest();
    }

    /**
     * Create an instance of {@link GetEnrollments }
     * 
     */
    public GetEnrollments createGetEnrollments() {
        return new GetEnrollments();
    }

    /**
     * Create an instance of {@link GetEnrollmentsRequest }
     * 
     */
    public GetEnrollmentsRequest createGetEnrollmentsRequest() {
        return new GetEnrollmentsRequest();
    }

    /**
     * Create an instance of {@link AddClientsToClassesResponse }
     * 
     */
    public AddClientsToClassesResponse createAddClientsToClassesResponse() {
        return new AddClientsToClassesResponse();
    }

    /**
     * Create an instance of {@link AddClientsToClassesResult }
     * 
     */
    public AddClientsToClassesResult createAddClientsToClassesResult() {
        return new AddClientsToClassesResult();
    }

    /**
     * Create an instance of {@link UpdateClientVisits }
     * 
     */
    public UpdateClientVisits createUpdateClientVisits() {
        return new UpdateClientVisits();
    }

    /**
     * Create an instance of {@link UpdateClientVisitsRequest }
     * 
     */
    public UpdateClientVisitsRequest createUpdateClientVisitsRequest() {
        return new UpdateClientVisitsRequest();
    }

    /**
     * Create an instance of {@link RemoveFromWaitlistResponse }
     * 
     */
    public RemoveFromWaitlistResponse createRemoveFromWaitlistResponse() {
        return new RemoveFromWaitlistResponse();
    }

    /**
     * Create an instance of {@link RemoveFromWaitlistResult }
     * 
     */
    public RemoveFromWaitlistResult createRemoveFromWaitlistResult() {
        return new RemoveFromWaitlistResult();
    }

    /**
     * Create an instance of {@link RemoveClientsFromClasses }
     * 
     */
    public RemoveClientsFromClasses createRemoveClientsFromClasses() {
        return new RemoveClientsFromClasses();
    }

    /**
     * Create an instance of {@link RemoveClientsFromClassesRequest }
     * 
     */
    public RemoveClientsFromClassesRequest createRemoveClientsFromClassesRequest() {
        return new RemoveClientsFromClassesRequest();
    }

    /**
     * Create an instance of {@link GetClassDescriptionsResponse }
     * 
     */
    public GetClassDescriptionsResponse createGetClassDescriptionsResponse() {
        return new GetClassDescriptionsResponse();
    }

    /**
     * Create an instance of {@link GetClassDescriptionsResult }
     * 
     */
    public GetClassDescriptionsResult createGetClassDescriptionsResult() {
        return new GetClassDescriptionsResult();
    }

    /**
     * Create an instance of {@link GetCoursesResponse }
     * 
     */
    public GetCoursesResponse createGetCoursesResponse() {
        return new GetCoursesResponse();
    }

    /**
     * Create an instance of {@link GetCoursesResult }
     * 
     */
    public GetCoursesResult createGetCoursesResult() {
        return new GetCoursesResult();
    }

    /**
     * Create an instance of {@link ArrayOfClassDescription }
     * 
     */
    public ArrayOfClassDescription createArrayOfClassDescription() {
        return new ArrayOfClassDescription();
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
     * Create an instance of {@link Appointment }
     * 
     */
    public Appointment createAppointment() {
        return new Appointment();
    }

    /**
     * Create an instance of {@link ArrayOfClientRelationship }
     * 
     */
    public ArrayOfClientRelationship createArrayOfClientRelationship() {
        return new ArrayOfClientRelationship();
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
     * Create an instance of {@link ClassSchedule }
     * 
     */
    public ClassSchedule createClassSchedule() {
        return new ClassSchedule();
    }

    /**
     * Create an instance of {@link ArrayOfVisit }
     * 
     */
    public ArrayOfVisit createArrayOfVisit() {
        return new ArrayOfVisit();
    }

    /**
     * Create an instance of {@link Semester }
     * 
     */
    public Semester createSemester() {
        return new Semester();
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
     * Create an instance of {@link Visit }
     * 
     */
    public Visit createVisit() {
        return new Visit();
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
     * Create an instance of {@link Class }
     * 
     */
    public Class createClass() {
        return new Class();
    }

    /**
     * Create an instance of {@link Relationship }
     * 
     */
    public Relationship createRelationship() {
        return new Relationship();
    }

    /**
     * Create an instance of {@link WaitlistEntry }
     * 
     */
    public WaitlistEntry createWaitlistEntry() {
        return new WaitlistEntry();
    }

    /**
     * Create an instance of {@link ClientIndexValue }
     * 
     */
    public ClientIndexValue createClientIndexValue() {
        return new ClientIndexValue();
    }

    /**
     * Create an instance of {@link ArrayOfSemester }
     * 
     */
    public ArrayOfSemester createArrayOfSemester() {
        return new ArrayOfSemester();
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
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
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
     * Create an instance of {@link ArrayOfCourse }
     * 
     */
    public ArrayOfCourse createArrayOfCourse() {
        return new ArrayOfCourse();
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
     * Create an instance of {@link ArrayOfClass }
     * 
     */
    public ArrayOfClass createArrayOfClass() {
        return new ArrayOfClass();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
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
     * Create an instance of {@link ArrayOfDateTime }
     * 
     */
    public ArrayOfDateTime createArrayOfDateTime() {
        return new ArrayOfDateTime();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link CustomClientField }
     * 
     */
    public CustomClientField createCustomClientField() {
        return new CustomClientField();
    }

    /**
     * Create an instance of {@link ArrayOfClient }
     * 
     */
    public ArrayOfClient createArrayOfClient() {
        return new ArrayOfClient();
    }

    /**
     * Create an instance of {@link ArrayOfWaitlistEntry }
     * 
     */
    public ArrayOfWaitlistEntry createArrayOfWaitlistEntry() {
        return new ArrayOfWaitlistEntry();
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
     * Create an instance of {@link ClassDescription }
     * 
     */
    public ClassDescription createClassDescription() {
        return new ClassDescription();
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
     * Create an instance of {@link ArrayOfClassSchedule }
     * 
     */
    public ArrayOfClassSchedule createArrayOfClassSchedule() {
        return new ArrayOfClassSchedule();
    }

    /**
     * Create an instance of {@link ArrayOfClientIndexValue }
     * 
     */
    public ArrayOfClientIndexValue createArrayOfClientIndexValue() {
        return new ArrayOfClientIndexValue();
    }

    /**
     * Create an instance of {@link Level }
     * 
     */
    public Level createLevel() {
        return new Level();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndex.class)
    public JAXBElement<Integer> createClientIndexID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClientIndex.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Class.class)
    public JAXBElement<Integer> createClassID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EndDateTime", scope = Class.class)
    public JAXBElement<XMLGregorianCalendar> createClassEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassEndDateTime_QNAME, XMLGregorianCalendar.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartDateTime", scope = Class.class)
    public JAXBElement<XMLGregorianCalendar> createClassStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassStartDateTime_QNAME, XMLGregorianCalendar.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "IsAvailable", scope = Class.class)
    public JAXBElement<Boolean> createClassIsAvailable(Boolean value) {
        return new JAXBElement<Boolean>(_ClassIsAvailable_QNAME, Boolean.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "SemesterID", scope = Class.class)
    public JAXBElement<Integer> createClassSemesterID(Integer value) {
        return new JAXBElement<Integer>(_ClassSemesterID_QNAME, Integer.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DaySunday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDaySunday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDaySunday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClassSchedule.class)
    public JAXBElement<Integer> createClassScheduleID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EndTime", scope = ClassSchedule.class)
    public JAXBElement<XMLGregorianCalendar> createClassScheduleEndTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassScheduleEndTime_QNAME, XMLGregorianCalendar.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartTime", scope = ClassSchedule.class)
    public JAXBElement<XMLGregorianCalendar> createClassScheduleStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassScheduleStartTime_QNAME, XMLGregorianCalendar.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DayFriday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDayFriday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDayFriday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DaySaturday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDaySaturday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDaySaturday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DayTuesday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDayTuesday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDayTuesday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DayMonday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDayMonday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDayMonday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "SemesterID", scope = ClassSchedule.class)
    public JAXBElement<Integer> createClassScheduleSemesterID(Integer value) {
        return new JAXBElement<Integer>(_ClassSemesterID_QNAME, Integer.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EndDate", scope = ClassSchedule.class)
    public JAXBElement<XMLGregorianCalendar> createClassScheduleEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassScheduleEndDate_QNAME, XMLGregorianCalendar.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartDate", scope = ClassSchedule.class)
    public JAXBElement<XMLGregorianCalendar> createClassScheduleStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassScheduleStartDate_QNAME, XMLGregorianCalendar.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DayWednesday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDayWednesday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDayWednesday_QNAME, Boolean.class, ClassSchedule.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DayThursday", scope = ClassSchedule.class)
    public JAXBElement<Boolean> createClassScheduleDayThursday(Boolean value) {
        return new JAXBElement<Boolean>(_ClassScheduleDayThursday_QNAME, Boolean.class, ClassSchedule.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClassDescription.class)
    public JAXBElement<Integer> createClassDescriptionID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClassDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "LastUpdated", scope = ClassDescription.class)
    public JAXBElement<XMLGregorianCalendar> createClassDescriptionLastUpdated(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassDescriptionLastUpdated_QNAME, XMLGregorianCalendar.class, ClassDescription.class, value);
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
        return new JAXBElement<XMLGregorianCalendar>(_ClassEndDateTime_QNAME, XMLGregorianCalendar.class, Appointment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartDateTime", scope = Appointment.class)
    public JAXBElement<XMLGregorianCalendar> createAppointmentStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClassStartDateTime_QNAME, XMLGregorianCalendar.class, Appointment.class, value);
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndexValue.class)
    public JAXBElement<Integer> createClientIndexValueID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, ClientIndexValue.class, value);
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Resource.class)
    public JAXBElement<Integer> createResourceID(Integer value) {
        return new JAXBElement<Integer>(_ClientServiceID_QNAME, Integer.class, Resource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "MakeUp", scope = Visit.class)
    public JAXBElement<Boolean> createVisitMakeUp(Boolean value) {
        return new JAXBElement<Boolean>(_VisitMakeUp_QNAME, Boolean.class, Visit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "SignedIn", scope = Visit.class)
    public JAXBElement<Boolean> createVisitSignedIn(Boolean value) {
        return new JAXBElement<Boolean>(_VisitSignedIn_QNAME, Boolean.class, Visit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ESA", scope = Site.class)
    public JAXBElement<Boolean> createSiteESA(Boolean value) {
        return new JAXBElement<Boolean>(_SiteESA_QNAME, Boolean.class, Site.class, value);
    }

}
