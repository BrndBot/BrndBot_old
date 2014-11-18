/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svClient;

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

    private final static QName _VisitMakeUp_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "MakeUp");
    private final static QName _VisitSignedIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SignedIn");
    private final static QName _ClientIsCompany_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsCompany");
    private final static QName _ClientBirthDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "BirthDate");
    private final static QName _ClientFirstAppointmentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointmentDate");
    private final static QName _ClientLiabilityRelease_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LiabilityRelease");
    private final static QName _ClientIsProspect_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsProspect");
    private final static QName _ClientInactive_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Inactive");
    private final static QName _ClientEmailOptIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EmailOptIn");
    private final static QName _ClientIndexID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ID");
    private final static QName _ContactLogFollowupByDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FollowupByDate");
    private final static QName _AppointmentEndDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDateTime");
    private final static QName _AppointmentStartDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDateTime");
    private final static QName _AppointmentStaffRequested_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StaffRequested");
    private final static QName _AppointmentFirstAppointment_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointment");
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

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mindbodyonline.clients.api._0_5
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddOrUpdateClientsResponse }
     * 
     */
    public AddOrUpdateClientsResponse createAddOrUpdateClientsResponse() {
        return new AddOrUpdateClientsResponse();
    }

    /**
     * Create an instance of {@link AddOrUpdateClientsResult }
     * 
     */
    public AddOrUpdateClientsResult createAddOrUpdateClientsResult() {
        return new AddOrUpdateClientsResult();
    }

    /**
     * Create an instance of {@link GetClientVisits }
     * 
     */
    public GetClientVisits createGetClientVisits() {
        return new GetClientVisits();
    }

    /**
     * Create an instance of {@link GetClientVisitsRequest }
     * 
     */
    public GetClientVisitsRequest createGetClientVisitsRequest() {
        return new GetClientVisitsRequest();
    }

    /**
     * Create an instance of {@link GetClientContactLogsResponse }
     * 
     */
    public GetClientContactLogsResponse createGetClientContactLogsResponse() {
        return new GetClientContactLogsResponse();
    }

    /**
     * Create an instance of {@link GetClientContactLogsResult }
     * 
     */
    public GetClientContactLogsResult createGetClientContactLogsResult() {
        return new GetClientContactLogsResult();
    }

    /**
     * Create an instance of {@link AddOrUpdateContactLogs }
     * 
     */
    public AddOrUpdateContactLogs createAddOrUpdateContactLogs() {
        return new AddOrUpdateContactLogs();
    }

    /**
     * Create an instance of {@link AddOrUpdateContactLogsRequest }
     * 
     */
    public AddOrUpdateContactLogsRequest createAddOrUpdateContactLogsRequest() {
        return new AddOrUpdateContactLogsRequest();
    }

    /**
     * Create an instance of {@link GetClientReferralTypesResponse }
     * 
     */
    public GetClientReferralTypesResponse createGetClientReferralTypesResponse() {
        return new GetClientReferralTypesResponse();
    }

    /**
     * Create an instance of {@link GetClientReferralTypesResult }
     * 
     */
    public GetClientReferralTypesResult createGetClientReferralTypesResult() {
        return new GetClientReferralTypesResult();
    }

    /**
     * Create an instance of {@link GetClientScheduleResponse }
     * 
     */
    public GetClientScheduleResponse createGetClientScheduleResponse() {
        return new GetClientScheduleResponse();
    }

    /**
     * Create an instance of {@link GetClientScheduleResult }
     * 
     */
    public GetClientScheduleResult createGetClientScheduleResult() {
        return new GetClientScheduleResult();
    }

    /**
     * Create an instance of {@link AddClientFormulaNoteResponse }
     * 
     */
    public AddClientFormulaNoteResponse createAddClientFormulaNoteResponse() {
        return new AddClientFormulaNoteResponse();
    }

    /**
     * Create an instance of {@link AddClientFormulaNoteResult }
     * 
     */
    public AddClientFormulaNoteResult createAddClientFormulaNoteResult() {
        return new AddClientFormulaNoteResult();
    }

    /**
     * Create an instance of {@link AddArrivalResponse }
     * 
     */
    public AddArrivalResponse createAddArrivalResponse() {
        return new AddArrivalResponse();
    }

    /**
     * Create an instance of {@link AddArrivalResult }
     * 
     */
    public AddArrivalResult createAddArrivalResult() {
        return new AddArrivalResult();
    }

    /**
     * Create an instance of {@link DeleteClientFormulaNoteResponse }
     * 
     */
    public DeleteClientFormulaNoteResponse createDeleteClientFormulaNoteResponse() {
        return new DeleteClientFormulaNoteResponse();
    }

    /**
     * Create an instance of {@link DeleteClientFormulaNoteResult }
     * 
     */
    public DeleteClientFormulaNoteResult createDeleteClientFormulaNoteResult() {
        return new DeleteClientFormulaNoteResult();
    }

    /**
     * Create an instance of {@link GetClientVisitsResponse }
     * 
     */
    public GetClientVisitsResponse createGetClientVisitsResponse() {
        return new GetClientVisitsResponse();
    }

    /**
     * Create an instance of {@link GetClientVisitsResult }
     * 
     */
    public GetClientVisitsResult createGetClientVisitsResult() {
        return new GetClientVisitsResult();
    }

    /**
     * Create an instance of {@link GetClientFormulaNotesResponse }
     * 
     */
    public GetClientFormulaNotesResponse createGetClientFormulaNotesResponse() {
        return new GetClientFormulaNotesResponse();
    }

    /**
     * Create an instance of {@link GetClientFormulaNotesResult }
     * 
     */
    public GetClientFormulaNotesResult createGetClientFormulaNotesResult() {
        return new GetClientFormulaNotesResult();
    }

    /**
     * Create an instance of {@link GetClientsResponse }
     * 
     */
    public GetClientsResponse createGetClientsResponse() {
        return new GetClientsResponse();
    }

    /**
     * Create an instance of {@link GetClientsResult }
     * 
     */
    public GetClientsResult createGetClientsResult() {
        return new GetClientsResult();
    }

    /**
     * Create an instance of {@link GetClientServices }
     * 
     */
    public GetClientServices createGetClientServices() {
        return new GetClientServices();
    }

    /**
     * Create an instance of {@link GetClientServicesRequest }
     * 
     */
    public GetClientServicesRequest createGetClientServicesRequest() {
        return new GetClientServicesRequest();
    }

    /**
     * Create an instance of {@link ValidateLoginResponse }
     * 
     */
    public ValidateLoginResponse createValidateLoginResponse() {
        return new ValidateLoginResponse();
    }

    /**
     * Create an instance of {@link ValidateLoginResult }
     * 
     */
    public ValidateLoginResult createValidateLoginResult() {
        return new ValidateLoginResult();
    }

    /**
     * Create an instance of {@link GetCustomClientFieldsResponse }
     * 
     */
    public GetCustomClientFieldsResponse createGetCustomClientFieldsResponse() {
        return new GetCustomClientFieldsResponse();
    }

    /**
     * Create an instance of {@link GetCustomClientFieldsResult }
     * 
     */
    public GetCustomClientFieldsResult createGetCustomClientFieldsResult() {
        return new GetCustomClientFieldsResult();
    }

    /**
     * Create an instance of {@link GetClientFormulaNotes }
     * 
     */
    public GetClientFormulaNotes createGetClientFormulaNotes() {
        return new GetClientFormulaNotes();
    }

    /**
     * Create an instance of {@link GetClientFormulaNotesRequest }
     * 
     */
    public GetClientFormulaNotesRequest createGetClientFormulaNotesRequest() {
        return new GetClientFormulaNotesRequest();
    }

    /**
     * Create an instance of {@link GetContactLogTypes }
     * 
     */
    public GetContactLogTypes createGetContactLogTypes() {
        return new GetContactLogTypes();
    }

    /**
     * Create an instance of {@link GetContactLogTypesRequest }
     * 
     */
    public GetContactLogTypesRequest createGetContactLogTypesRequest() {
        return new GetContactLogTypesRequest();
    }

    /**
     * Create an instance of {@link GetClientSchedule }
     * 
     */
    public GetClientSchedule createGetClientSchedule() {
        return new GetClientSchedule();
    }

    /**
     * Create an instance of {@link GetClientScheduleRequest }
     * 
     */
    public GetClientScheduleRequest createGetClientScheduleRequest() {
        return new GetClientScheduleRequest();
    }

    /**
     * Create an instance of {@link GetClientServicesResponse }
     * 
     */
    public GetClientServicesResponse createGetClientServicesResponse() {
        return new GetClientServicesResponse();
    }

    /**
     * Create an instance of {@link GetClientServicesResult }
     * 
     */
    public GetClientServicesResult createGetClientServicesResult() {
        return new GetClientServicesResult();
    }

    /**
     * Create an instance of {@link DeleteClientFormulaNote }
     * 
     */
    public DeleteClientFormulaNote createDeleteClientFormulaNote() {
        return new DeleteClientFormulaNote();
    }

    /**
     * Create an instance of {@link DeleteCientFormulaNoteRequest }
     * 
     */
    public DeleteCientFormulaNoteRequest createDeleteCientFormulaNoteRequest() {
        return new DeleteCientFormulaNoteRequest();
    }

    /**
     * Create an instance of {@link GetClientAccountBalances }
     * 
     */
    public GetClientAccountBalances createGetClientAccountBalances() {
        return new GetClientAccountBalances();
    }

    /**
     * Create an instance of {@link GetClientAccountBalancesRequest }
     * 
     */
    public GetClientAccountBalancesRequest createGetClientAccountBalancesRequest() {
        return new GetClientAccountBalancesRequest();
    }

    /**
     * Create an instance of {@link GetClientReferralTypes }
     * 
     */
    public GetClientReferralTypes createGetClientReferralTypes() {
        return new GetClientReferralTypes();
    }

    /**
     * Create an instance of {@link GetClientReferralTypesRequest }
     * 
     */
    public GetClientReferralTypesRequest createGetClientReferralTypesRequest() {
        return new GetClientReferralTypesRequest();
    }

    /**
     * Create an instance of {@link GetContactLogTypesResponse }
     * 
     */
    public GetContactLogTypesResponse createGetContactLogTypesResponse() {
        return new GetContactLogTypesResponse();
    }

    /**
     * Create an instance of {@link GetContactLogTypesResult }
     * 
     */
    public GetContactLogTypesResult createGetContactLogTypesResult() {
        return new GetContactLogTypesResult();
    }

    /**
     * Create an instance of {@link AddClientFormulaNote }
     * 
     */
    public AddClientFormulaNote createAddClientFormulaNote() {
        return new AddClientFormulaNote();
    }

    /**
     * Create an instance of {@link AddClientFormulaNoteRequest }
     * 
     */
    public AddClientFormulaNoteRequest createAddClientFormulaNoteRequest() {
        return new AddClientFormulaNoteRequest();
    }

    /**
     * Create an instance of {@link GetClientContactLogs }
     * 
     */
    public GetClientContactLogs createGetClientContactLogs() {
        return new GetClientContactLogs();
    }

    /**
     * Create an instance of {@link GetClientContactLogsRequest }
     * 
     */
    public GetClientContactLogsRequest createGetClientContactLogsRequest() {
        return new GetClientContactLogsRequest();
    }

    /**
     * Create an instance of {@link SendUserNewPasswordResponse }
     * 
     */
    public SendUserNewPasswordResponse createSendUserNewPasswordResponse() {
        return new SendUserNewPasswordResponse();
    }

    /**
     * Create an instance of {@link ClientSendUserNewPasswordResult }
     * 
     */
    public ClientSendUserNewPasswordResult createClientSendUserNewPasswordResult() {
        return new ClientSendUserNewPasswordResult();
    }

    /**
     * Create an instance of {@link ValidateLogin }
     * 
     */
    public ValidateLogin createValidateLogin() {
        return new ValidateLogin();
    }

    /**
     * Create an instance of {@link ValidateLoginRequest }
     * 
     */
    public ValidateLoginRequest createValidateLoginRequest() {
        return new ValidateLoginRequest();
    }

    /**
     * Create an instance of {@link GetClientContractsResponse }
     * 
     */
    public GetClientContractsResponse createGetClientContractsResponse() {
        return new GetClientContractsResponse();
    }

    /**
     * Create an instance of {@link GetClientContractsResult }
     * 
     */
    public GetClientContractsResult createGetClientContractsResult() {
        return new GetClientContractsResult();
    }

    /**
     * Create an instance of {@link GetClientIndexes }
     * 
     */
    public GetClientIndexes createGetClientIndexes() {
        return new GetClientIndexes();
    }

    /**
     * Create an instance of {@link GetClientIndexesRequest }
     * 
     */
    public GetClientIndexesRequest createGetClientIndexesRequest() {
        return new GetClientIndexesRequest();
    }

    /**
     * Create an instance of {@link GetActiveClientMembershipsResponse }
     * 
     */
    public GetActiveClientMembershipsResponse createGetActiveClientMembershipsResponse() {
        return new GetActiveClientMembershipsResponse();
    }

    /**
     * Create an instance of {@link GetActiveClientMembershipsResult }
     * 
     */
    public GetActiveClientMembershipsResult createGetActiveClientMembershipsResult() {
        return new GetActiveClientMembershipsResult();
    }

    /**
     * Create an instance of {@link GetClientPurchasesResponse }
     * 
     */
    public GetClientPurchasesResponse createGetClientPurchasesResponse() {
        return new GetClientPurchasesResponse();
    }

    /**
     * Create an instance of {@link GetClientPurchasesResult }
     * 
     */
    public GetClientPurchasesResult createGetClientPurchasesResult() {
        return new GetClientPurchasesResult();
    }

    /**
     * Create an instance of {@link UploadClientDocumentResponse }
     * 
     */
    public UploadClientDocumentResponse createUploadClientDocumentResponse() {
        return new UploadClientDocumentResponse();
    }

    /**
     * Create an instance of {@link UploadClientDocumentResult }
     * 
     */
    public UploadClientDocumentResult createUploadClientDocumentResult() {
        return new UploadClientDocumentResult();
    }

    /**
     * Create an instance of {@link GetClients }
     * 
     */
    public GetClients createGetClients() {
        return new GetClients();
    }

    /**
     * Create an instance of {@link GetClientsRequest }
     * 
     */
    public GetClientsRequest createGetClientsRequest() {
        return new GetClientsRequest();
    }

    /**
     * Create an instance of {@link GetCustomClientFields }
     * 
     */
    public GetCustomClientFields createGetCustomClientFields() {
        return new GetCustomClientFields();
    }

    /**
     * Create an instance of {@link GetCustomClientFieldsRequest }
     * 
     */
    public GetCustomClientFieldsRequest createGetCustomClientFieldsRequest() {
        return new GetCustomClientFieldsRequest();
    }

    /**
     * Create an instance of {@link GetClientAccountBalancesResponse }
     * 
     */
    public GetClientAccountBalancesResponse createGetClientAccountBalancesResponse() {
        return new GetClientAccountBalancesResponse();
    }

    /**
     * Create an instance of {@link GetClientAccountBalancesResult }
     * 
     */
    public GetClientAccountBalancesResult createGetClientAccountBalancesResult() {
        return new GetClientAccountBalancesResult();
    }

    /**
     * Create an instance of {@link UploadClientDocument }
     * 
     */
    public UploadClientDocument createUploadClientDocument() {
        return new UploadClientDocument();
    }

    /**
     * Create an instance of {@link UploadClientDocumentRequest }
     * 
     */
    public UploadClientDocumentRequest createUploadClientDocumentRequest() {
        return new UploadClientDocumentRequest();
    }

    /**
     * Create an instance of {@link UpdateClientServices }
     * 
     */
    public UpdateClientServices createUpdateClientServices() {
        return new UpdateClientServices();
    }

    /**
     * Create an instance of {@link UpdateClientServicesRequest }
     * 
     */
    public UpdateClientServicesRequest createUpdateClientServicesRequest() {
        return new UpdateClientServicesRequest();
    }

    /**
     * Create an instance of {@link GetClientPurchases }
     * 
     */
    public GetClientPurchases createGetClientPurchases() {
        return new GetClientPurchases();
    }

    /**
     * Create an instance of {@link GetClientPurchasesRequest }
     * 
     */
    public GetClientPurchasesRequest createGetClientPurchasesRequest() {
        return new GetClientPurchasesRequest();
    }

    /**
     * Create an instance of {@link GetActiveClientMemberships }
     * 
     */
    public GetActiveClientMemberships createGetActiveClientMemberships() {
        return new GetActiveClientMemberships();
    }

    /**
     * Create an instance of {@link GetActiveClientMembershipsRequest }
     * 
     */
    public GetActiveClientMembershipsRequest createGetActiveClientMembershipsRequest() {
        return new GetActiveClientMembershipsRequest();
    }

    /**
     * Create an instance of {@link AddOrUpdateContactLogsResponse }
     * 
     */
    public AddOrUpdateContactLogsResponse createAddOrUpdateContactLogsResponse() {
        return new AddOrUpdateContactLogsResponse();
    }

    /**
     * Create an instance of {@link AddOrUpdateContactLogsResult }
     * 
     */
    public AddOrUpdateContactLogsResult createAddOrUpdateContactLogsResult() {
        return new AddOrUpdateContactLogsResult();
    }

    /**
     * Create an instance of {@link AddArrival }
     * 
     */
    public AddArrival createAddArrival() {
        return new AddArrival();
    }

    /**
     * Create an instance of {@link AddArrivalRequest }
     * 
     */
    public AddArrivalRequest createAddArrivalRequest() {
        return new AddArrivalRequest();
    }

    /**
     * Create an instance of {@link GetRequiredClientFieldsResponse }
     * 
     */
    public GetRequiredClientFieldsResponse createGetRequiredClientFieldsResponse() {
        return new GetRequiredClientFieldsResponse();
    }

    /**
     * Create an instance of {@link GetRequiredClientFieldsResult }
     * 
     */
    public GetRequiredClientFieldsResult createGetRequiredClientFieldsResult() {
        return new GetRequiredClientFieldsResult();
    }

    /**
     * Create an instance of {@link SendUserNewPassword }
     * 
     */
    public SendUserNewPassword createSendUserNewPassword() {
        return new SendUserNewPassword();
    }

    /**
     * Create an instance of {@link ClientSendUserNewPasswordRequest }
     * 
     */
    public ClientSendUserNewPasswordRequest createClientSendUserNewPasswordRequest() {
        return new ClientSendUserNewPasswordRequest();
    }

    /**
     * Create an instance of {@link AddOrUpdateClients }
     * 
     */
    public AddOrUpdateClients createAddOrUpdateClients() {
        return new AddOrUpdateClients();
    }

    /**
     * Create an instance of {@link AddOrUpdateClientsRequest }
     * 
     */
    public AddOrUpdateClientsRequest createAddOrUpdateClientsRequest() {
        return new AddOrUpdateClientsRequest();
    }

    /**
     * Create an instance of {@link UpdateClientServicesResponse }
     * 
     */
    public UpdateClientServicesResponse createUpdateClientServicesResponse() {
        return new UpdateClientServicesResponse();
    }

    /**
     * Create an instance of {@link UpdateClientServicesResult }
     * 
     */
    public UpdateClientServicesResult createUpdateClientServicesResult() {
        return new UpdateClientServicesResult();
    }

    /**
     * Create an instance of {@link GetRequiredClientFields }
     * 
     */
    public GetRequiredClientFields createGetRequiredClientFields() {
        return new GetRequiredClientFields();
    }

    /**
     * Create an instance of {@link GetRequiredClientFieldsRequest }
     * 
     */
    public GetRequiredClientFieldsRequest createGetRequiredClientFieldsRequest() {
        return new GetRequiredClientFieldsRequest();
    }

    /**
     * Create an instance of {@link GetClientContracts }
     * 
     */
    public GetClientContracts createGetClientContracts() {
        return new GetClientContracts();
    }

    /**
     * Create an instance of {@link GetClientContractsRequest }
     * 
     */
    public GetClientContractsRequest createGetClientContractsRequest() {
        return new GetClientContractsRequest();
    }

    /**
     * Create an instance of {@link GetClientIndexesResponse }
     * 
     */
    public GetClientIndexesResponse createGetClientIndexesResponse() {
        return new GetClientIndexesResponse();
    }

    /**
     * Create an instance of {@link GetClientIndexesResult }
     * 
     */
    public GetClientIndexesResult createGetClientIndexesResult() {
        return new GetClientIndexesResult();
    }

    /**
     * Create an instance of {@link ArrayOfSaleItem }
     * 
     */
    public ArrayOfSaleItem createArrayOfSaleItem() {
        return new ArrayOfSaleItem();
    }

    /**
     * Create an instance of {@link ArrayOfClientService }
     * 
     */
    public ArrayOfClientService createArrayOfClientService() {
        return new ArrayOfClientService();
    }

    /**
     * Create an instance of {@link ArrayOfClientContract }
     * 
     */
    public ArrayOfClientContract createArrayOfClientContract() {
        return new ArrayOfClientContract();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link ClientContract }
     * 
     */
    public ClientContract createClientContract() {
        return new ClientContract();
    }

    /**
     * Create an instance of {@link ArrayOfContactLogComment }
     * 
     */
    public ArrayOfContactLogComment createArrayOfContactLogComment() {
        return new ArrayOfContactLogComment();
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
     * Create an instance of {@link Sale }
     * 
     */
    public Sale createSale() {
        return new Sale();
    }

    /**
     * Create an instance of {@link ArrayOfRep }
     * 
     */
    public ArrayOfRep createArrayOfRep() {
        return new ArrayOfRep();
    }

    /**
     * Create an instance of {@link ArrayOfContactLogSubtype }
     * 
     */
    public ArrayOfContactLogSubtype createArrayOfContactLogSubtype() {
        return new ArrayOfContactLogSubtype();
    }

    /**
     * Create an instance of {@link ArrayOfVisit }
     * 
     */
    public ArrayOfVisit createArrayOfVisit() {
        return new ArrayOfVisit();
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
     * Create an instance of {@link ContactLog }
     * 
     */
    public ContactLog createContactLog() {
        return new ContactLog();
    }

    /**
     * Create an instance of {@link ContactLogType }
     * 
     */
    public ContactLogType createContactLogType() {
        return new ContactLogType();
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
     * Create an instance of {@link ArrayOfContactLogType }
     * 
     */
    public ArrayOfContactLogType createArrayOfContactLogType() {
        return new ArrayOfContactLogType();
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
     * Create an instance of {@link ClientCreditCard }
     * 
     */
    public ClientCreditCard createClientCreditCard() {
        return new ClientCreditCard();
    }

    /**
     * Create an instance of {@link ArrayOfLong }
     * 
     */
    public ArrayOfLong createArrayOfLong() {
        return new ArrayOfLong();
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
     * Create an instance of {@link ArrayOfClient }
     * 
     */
    public ArrayOfClient createArrayOfClient() {
        return new ArrayOfClient();
    }

    /**
     * Create an instance of {@link ArrayOfAvailability }
     * 
     */
    public ArrayOfAvailability createArrayOfAvailability() {
        return new ArrayOfAvailability();
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
     * Create an instance of {@link ArrayOfFormulaNote }
     * 
     */
    public ArrayOfFormulaNote createArrayOfFormulaNote() {
        return new ArrayOfFormulaNote();
    }

    /**
     * Create an instance of {@link ContactLogComment }
     * 
     */
    public ContactLogComment createContactLogComment() {
        return new ContactLogComment();
    }

    /**
     * Create an instance of {@link Unavailability }
     * 
     */
    public Unavailability createUnavailability() {
        return new Unavailability();
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
     * Create an instance of {@link Relationship }
     * 
     */
    public Relationship createRelationship() {
        return new Relationship();
    }

    /**
     * Create an instance of {@link ArrayOfContactLog }
     * 
     */
    public ArrayOfContactLog createArrayOfContactLog() {
        return new ArrayOfContactLog();
    }

    /**
     * Create an instance of {@link SaleItem }
     * 
     */
    public SaleItem createSaleItem() {
        return new SaleItem();
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
     * Create an instance of {@link ContactLogSubtype }
     * 
     */
    public ContactLogSubtype createContactLogSubtype() {
        return new ContactLogSubtype();
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
     * Create an instance of {@link ArrayOfClientMembership }
     * 
     */
    public ArrayOfClientMembership createArrayOfClientMembership() {
        return new ArrayOfClientMembership();
    }

    /**
     * Create an instance of {@link ArrayOfPayment }
     * 
     */
    public ArrayOfPayment createArrayOfPayment() {
        return new ArrayOfPayment();
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
     * Create an instance of {@link ClientMembership }
     * 
     */
    public ClientMembership createClientMembership() {
        return new ClientMembership();
    }

    /**
     * Create an instance of {@link ArrayOfAppointment }
     * 
     */
    public ArrayOfAppointment createArrayOfAppointment() {
        return new ArrayOfAppointment();
    }

    /**
     * Create an instance of {@link ArrayOfClientIndexValue }
     * 
     */
    public ArrayOfClientIndexValue createArrayOfClientIndexValue() {
        return new ArrayOfClientIndexValue();
    }

    /**
     * Create an instance of {@link FormulaNote }
     * 
     */
    public FormulaNote createFormulaNote() {
        return new FormulaNote();
    }

    /**
     * Create an instance of {@link ArrayOfLocation }
     * 
     */
    public ArrayOfLocation createArrayOfLocation() {
        return new ArrayOfLocation();
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndex.class)
    public JAXBElement<Integer> createClientIndexID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, ClientIndex.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ContactLog.class)
    public JAXBElement<Long> createContactLogID(Long value) {
        return new JAXBElement<Long>(_ClientIndexID_QNAME, Long.class, ContactLog.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "FollowupByDate", scope = ContactLog.class)
    public JAXBElement<XMLGregorianCalendar> createContactLogFollowupByDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ContactLogFollowupByDate_QNAME, XMLGregorianCalendar.class, ContactLog.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Appointment.class)
    public JAXBElement<Long> createAppointmentID(Long value) {
        return new JAXBElement<Long>(_ClientIndexID_QNAME, Long.class, Appointment.class, value);
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientService.class)
    public JAXBElement<Long> createClientServiceID(Long value) {
        return new JAXBElement<Long>(_ClientIndexID_QNAME, Long.class, ClientService.class, value);
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientContract.class)
    public JAXBElement<Integer> createClientContractID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, ClientContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Staff.class)
    public JAXBElement<Long> createStaffID(Long value) {
        return new JAXBElement<Long>(_ClientIndexID_QNAME, Long.class, Staff.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = SessionType.class)
    public JAXBElement<Integer> createSessionTypeID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, SessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndexValue.class)
    public JAXBElement<Integer> createClientIndexValueID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, ClientIndexValue.class, value);
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
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, Location.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ESA", scope = Site.class)
    public JAXBElement<Boolean> createSiteESA(Boolean value) {
        return new JAXBElement<Boolean>(_SiteESA_QNAME, Boolean.class, Site.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Resource.class)
    public JAXBElement<Integer> createResourceID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexID_QNAME, Integer.class, Resource.class, value);
    }

}
