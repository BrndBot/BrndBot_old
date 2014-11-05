
package com.mindbodyonline.clients.svSale;

import java.math.BigDecimal;
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

    private final static QName _ClientIndexValueID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ID");
    private final static QName _ShoppingCartTaxTotal_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "TaxTotal");
    private final static QName _ShoppingCartGrandTotal_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "GrandTotal");
    private final static QName _ShoppingCartSubTotal_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SubTotal");
    private final static QName _ShoppingCartDiscountTotal_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DiscountTotal");
    private final static QName _ClientIsCompany_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsCompany");
    private final static QName _ClientBirthDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "BirthDate");
    private final static QName _ClientFirstAppointmentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointmentDate");
    private final static QName _ClientLiabilityRelease_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LiabilityRelease");
    private final static QName _ClientIsProspect_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsProspect");
    private final static QName _ClientInactive_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Inactive");
    private final static QName _ClientEmailOptIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EmailOptIn");
    private final static QName _CartItemQuantity_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Quantity");
    private final static QName _CreditCardInfoSaveInfo_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SaveInfo");
    private final static QName _CreditCardInfoAmount_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Amount");
    private final static QName _ClassScheduleDaySunday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DaySunday");
    private final static QName _ClassScheduleEndTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndTime");
    private final static QName _ClassScheduleStartTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartTime");
    private final static QName _ClassScheduleDayFriday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayFriday");
    private final static QName _ClassScheduleDaySaturday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DaySaturday");
    private final static QName _ClassScheduleDayTuesday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayTuesday");
    private final static QName _ClassScheduleDayMonday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayMonday");
    private final static QName _ClassScheduleSemesterID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SemesterID");
    private final static QName _ClassScheduleEndDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDate");
    private final static QName _ClassScheduleStartDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDate");
    private final static QName _ClassScheduleDayWednesday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayWednesday");
    private final static QName _ClassScheduleDayThursday_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "DayThursday");
    private final static QName _ClientServiceActiveDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ActiveDate");
    private final static QName _ClientServicePaymentDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "PaymentDate");
    private final static QName _ClientServiceExpirationDate_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ExpirationDate");
    private final static QName _VisitMakeUp_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "MakeUp");
    private final static QName _VisitSignedIn_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "SignedIn");
    private final static QName _AppointmentEndDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "EndDateTime");
    private final static QName _AppointmentStartDateTime_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StartDateTime");
    private final static QName _AppointmentStaffRequested_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "StaffRequested");
    private final static QName _AppointmentFirstAppointment_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "FirstAppointment");
    private final static QName _SiteESA_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "ESA");
    private final static QName _ServiceOnlinePrice_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "OnlinePrice");
    private final static QName _ServiceCount_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Count");
    private final static QName _ServiceTaxIncluded_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "TaxIncluded");
    private final static QName _ServicePrice_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "Price");
    private final static QName _ClassIsAvailable_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "IsAvailable");
    private final static QName _ProductGroupID_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "GroupID");
    private final static QName _ClassDescriptionLastUpdated_QNAME = new QName("http://clients.mindbodyonline.com/api/0_5", "LastUpdated");
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

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mindbodyonline.clients.api._0_5
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomPaymentMethods }
     * 
     */
    public GetCustomPaymentMethods createGetCustomPaymentMethods() {
        return new GetCustomPaymentMethods();
    }

    /**
     * Create an instance of {@link GetCustomPaymentMethodsRequest }
     * 
     */
    public GetCustomPaymentMethodsRequest createGetCustomPaymentMethodsRequest() {
        return new GetCustomPaymentMethodsRequest();
    }

    /**
     * Create an instance of {@link GetProducts }
     * 
     */
    public GetProducts createGetProducts() {
        return new GetProducts();
    }

    /**
     * Create an instance of {@link GetProductsRequest }
     * 
     */
    public GetProductsRequest createGetProductsRequest() {
        return new GetProductsRequest();
    }

    /**
     * Create an instance of {@link UpdateServicesResponse }
     * 
     */
    public UpdateServicesResponse createUpdateServicesResponse() {
        return new UpdateServicesResponse();
    }

    /**
     * Create an instance of {@link UpdateServicesResult }
     * 
     */
    public UpdateServicesResult createUpdateServicesResult() {
        return new UpdateServicesResult();
    }

    /**
     * Create an instance of {@link GetAcceptedCardType }
     * 
     */
    public GetAcceptedCardType createGetAcceptedCardType() {
        return new GetAcceptedCardType();
    }

    /**
     * Create an instance of {@link GetAcceptedCardTypeRequest }
     * 
     */
    public GetAcceptedCardTypeRequest createGetAcceptedCardTypeRequest() {
        return new GetAcceptedCardTypeRequest();
    }

    /**
     * Create an instance of {@link CheckoutShoppingCart }
     * 
     */
    public CheckoutShoppingCart createCheckoutShoppingCart() {
        return new CheckoutShoppingCart();
    }

    /**
     * Create an instance of {@link CheckoutShoppingCartRequest }
     * 
     */
    public CheckoutShoppingCartRequest createCheckoutShoppingCartRequest() {
        return new CheckoutShoppingCartRequest();
    }

    /**
     * Create an instance of {@link RedeemSpaFinderWellnessCardResponse }
     * 
     */
    public RedeemSpaFinderWellnessCardResponse createRedeemSpaFinderWellnessCardResponse() {
        return new RedeemSpaFinderWellnessCardResponse();
    }

    /**
     * Create an instance of {@link RedeemSpaFinderWellnessCardResult }
     * 
     */
    public RedeemSpaFinderWellnessCardResult createRedeemSpaFinderWellnessCardResult() {
        return new RedeemSpaFinderWellnessCardResult();
    }

    /**
     * Create an instance of {@link RedeemSpaFinderWellnessCard }
     * 
     */
    public RedeemSpaFinderWellnessCard createRedeemSpaFinderWellnessCard() {
        return new RedeemSpaFinderWellnessCard();
    }

    /**
     * Create an instance of {@link RedeemSpaFinderWellnessCardRequest }
     * 
     */
    public RedeemSpaFinderWellnessCardRequest createRedeemSpaFinderWellnessCardRequest() {
        return new RedeemSpaFinderWellnessCardRequest();
    }

    /**
     * Create an instance of {@link GetPackages }
     * 
     */
    public GetPackages createGetPackages() {
        return new GetPackages();
    }

    /**
     * Create an instance of {@link GetPackagesRequest }
     * 
     */
    public GetPackagesRequest createGetPackagesRequest() {
        return new GetPackagesRequest();
    }

    /**
     * Create an instance of {@link GetPackagesResponse }
     * 
     */
    public GetPackagesResponse createGetPackagesResponse() {
        return new GetPackagesResponse();
    }

    /**
     * Create an instance of {@link GetPackagesResult }
     * 
     */
    public GetPackagesResult createGetPackagesResult() {
        return new GetPackagesResult();
    }

    /**
     * Create an instance of {@link UpdateProducts }
     * 
     */
    public UpdateProducts createUpdateProducts() {
        return new UpdateProducts();
    }

    /**
     * Create an instance of {@link UpdateProductsRequest }
     * 
     */
    public UpdateProductsRequest createUpdateProductsRequest() {
        return new UpdateProductsRequest();
    }

    /**
     * Create an instance of {@link GetAcceptedCardTypeResponse }
     * 
     */
    public GetAcceptedCardTypeResponse createGetAcceptedCardTypeResponse() {
        return new GetAcceptedCardTypeResponse();
    }

    /**
     * Create an instance of {@link GetAcceptedCardTypeResult }
     * 
     */
    public GetAcceptedCardTypeResult createGetAcceptedCardTypeResult() {
        return new GetAcceptedCardTypeResult();
    }

    /**
     * Create an instance of {@link UpdateServices }
     * 
     */
    public UpdateServices createUpdateServices() {
        return new UpdateServices();
    }

    /**
     * Create an instance of {@link UpdateServicesRequest }
     * 
     */
    public UpdateServicesRequest createUpdateServicesRequest() {
        return new UpdateServicesRequest();
    }

    /**
     * Create an instance of {@link GetSalesResponse }
     * 
     */
    public GetSalesResponse createGetSalesResponse() {
        return new GetSalesResponse();
    }

    /**
     * Create an instance of {@link GetSalesResult }
     * 
     */
    public GetSalesResult createGetSalesResult() {
        return new GetSalesResult();
    }

    /**
     * Create an instance of {@link GetCustomPaymentMethodsResponse }
     * 
     */
    public GetCustomPaymentMethodsResponse createGetCustomPaymentMethodsResponse() {
        return new GetCustomPaymentMethodsResponse();
    }

    /**
     * Create an instance of {@link GetCustomPaymentMethodsResult }
     * 
     */
    public GetCustomPaymentMethodsResult createGetCustomPaymentMethodsResult() {
        return new GetCustomPaymentMethodsResult();
    }

    /**
     * Create an instance of {@link GetServicesResponse }
     * 
     */
    public GetServicesResponse createGetServicesResponse() {
        return new GetServicesResponse();
    }

    /**
     * Create an instance of {@link GetServicesResult }
     * 
     */
    public GetServicesResult createGetServicesResult() {
        return new GetServicesResult();
    }

    /**
     * Create an instance of {@link GetProductsResponse }
     * 
     */
    public GetProductsResponse createGetProductsResponse() {
        return new GetProductsResponse();
    }

    /**
     * Create an instance of {@link GetProductsResult }
     * 
     */
    public GetProductsResult createGetProductsResult() {
        return new GetProductsResult();
    }

    /**
     * Create an instance of {@link UpdateProductsResponse }
     * 
     */
    public UpdateProductsResponse createUpdateProductsResponse() {
        return new UpdateProductsResponse();
    }

    /**
     * Create an instance of {@link UpdateProductsResult }
     * 
     */
    public UpdateProductsResult createUpdateProductsResult() {
        return new UpdateProductsResult();
    }

    /**
     * Create an instance of {@link GetServices }
     * 
     */
    public GetServices createGetServices() {
        return new GetServices();
    }

    /**
     * Create an instance of {@link GetServicesRequest }
     * 
     */
    public GetServicesRequest createGetServicesRequest() {
        return new GetServicesRequest();
    }

    /**
     * Create an instance of {@link CheckoutShoppingCartResponse }
     * 
     */
    public CheckoutShoppingCartResponse createCheckoutShoppingCartResponse() {
        return new CheckoutShoppingCartResponse();
    }

    /**
     * Create an instance of {@link CheckoutShoppingCartResult }
     * 
     */
    public CheckoutShoppingCartResult createCheckoutShoppingCartResult() {
        return new CheckoutShoppingCartResult();
    }

    /**
     * Create an instance of {@link GetSales }
     * 
     */
    public GetSales createGetSales() {
        return new GetSales();
    }

    /**
     * Create an instance of {@link GetSalesRequest }
     * 
     */
    public GetSalesRequest createGetSalesRequest() {
        return new GetSalesRequest();
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
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
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link ArrayOfRep }
     * 
     */
    public ArrayOfRep createArrayOfRep() {
        return new ArrayOfRep();
    }

    /**
     * Create an instance of {@link Sale }
     * 
     */
    public Sale createSale() {
        return new Sale();
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
     * Create an instance of {@link ArrayOfCartItem }
     * 
     */
    public ArrayOfCartItem createArrayOfCartItem() {
        return new ArrayOfCartItem();
    }

    /**
     * Create an instance of {@link ArrayOfCustomPaymentInfo }
     * 
     */
    public ArrayOfCustomPaymentInfo createArrayOfCustomPaymentInfo() {
        return new ArrayOfCustomPaymentInfo();
    }

    /**
     * Create an instance of {@link Staff }
     * 
     */
    public Staff createStaff() {
        return new Staff();
    }

    /**
     * Create an instance of {@link ArrayOfProduct }
     * 
     */
    public ArrayOfProduct createArrayOfProduct() {
        return new ArrayOfProduct();
    }

    /**
     * Create an instance of {@link SessionType }
     * 
     */
    public SessionType createSessionType() {
        return new SessionType();
    }

    /**
     * Create an instance of {@link CompInfo }
     * 
     */
    public CompInfo createCompInfo() {
        return new CompInfo();
    }

    /**
     * Create an instance of {@link Color }
     * 
     */
    public Color createColor() {
        return new Color();
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
     * Create an instance of {@link ArrayOfSale }
     * 
     */
    public ArrayOfSale createArrayOfSale() {
        return new ArrayOfSale();
    }

    /**
     * Create an instance of {@link Class }
     * 
     */
    public Class createClass() {
        return new Class();
    }

    /**
     * Create an instance of {@link CustomPaymentInfo }
     * 
     */
    public CustomPaymentInfo createCustomPaymentInfo() {
        return new CustomPaymentInfo();
    }

    /**
     * Create an instance of {@link ClientIndexValue }
     * 
     */
    public ClientIndexValue createClientIndexValue() {
        return new ClientIndexValue();
    }

    /**
     * Create an instance of {@link CreditCardInfo }
     * 
     */
    public CreditCardInfo createCreditCardInfo() {
        return new CreditCardInfo();
    }

    /**
     * Create an instance of {@link ArrayOfService }
     * 
     */
    public ArrayOfService createArrayOfService() {
        return new ArrayOfService();
    }

    /**
     * Create an instance of {@link MBRequest }
     * 
     */
    public MBRequest createMBRequest() {
        return new MBRequest();
    }

    /**
     * Create an instance of {@link MBObject }
     * 
     */
    public MBObject createMBObject() {
        return new MBObject();
    }

    /**
     * Create an instance of {@link CheckInfo }
     * 
     */
    public CheckInfo createCheckInfo() {
        return new CheckInfo();
    }

    /**
     * Create an instance of {@link ArrayOfProgram }
     * 
     */
    public ArrayOfProgram createArrayOfProgram() {
        return new ArrayOfProgram();
    }

    /**
     * Create an instance of {@link ShoppingCart }
     * 
     */
    public ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }

    /**
     * Create an instance of {@link TrackDataInfo }
     * 
     */
    public TrackDataInfo createTrackDataInfo() {
        return new TrackDataInfo();
    }

    /**
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
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
     * Create an instance of {@link ArrayOfClass }
     * 
     */
    public ArrayOfClass createArrayOfClass() {
        return new ArrayOfClass();
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
     * Create an instance of {@link ArrayOfPaymentInfo }
     * 
     */
    public ArrayOfPaymentInfo createArrayOfPaymentInfo() {
        return new ArrayOfPaymentInfo();
    }

    /**
     * Create an instance of {@link ArrayOfClient }
     * 
     */
    public ArrayOfClient createArrayOfClient() {
        return new ArrayOfClient();
    }

    /**
     * Create an instance of {@link PaymentInfo }
     * 
     */
    public PaymentInfo createPaymentInfo() {
        return new PaymentInfo();
    }

    /**
     * Create an instance of {@link ArrayOfAvailability }
     * 
     */
    public ArrayOfAvailability createArrayOfAvailability() {
        return new ArrayOfAvailability();
    }

    /**
     * Create an instance of {@link Tip }
     * 
     */
    public Tip createTip() {
        return new Tip();
    }

    /**
     * Create an instance of {@link SourceCredentials }
     * 
     */
    public SourceCredentials createSourceCredentials() {
        return new SourceCredentials();
    }

    /**
     * Create an instance of {@link CartItem }
     * 
     */
    public CartItem createCartItem() {
        return new CartItem();
    }

    /**
     * Create an instance of {@link ArrayOfClientIndex }
     * 
     */
    public ArrayOfClientIndex createArrayOfClientIndex() {
        return new ArrayOfClientIndex();
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
     * Create an instance of {@link Size }
     * 
     */
    public Size createSize() {
        return new Size();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link ArrayOfPackage }
     * 
     */
    public ArrayOfPackage createArrayOfPackage() {
        return new ArrayOfPackage();
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
     * Create an instance of {@link DebitAccountInfo }
     * 
     */
    public DebitAccountInfo createDebitAccountInfo() {
        return new DebitAccountInfo();
    }

    /**
     * Create an instance of {@link EncryptedTrackDataInfo }
     * 
     */
    public EncryptedTrackDataInfo createEncryptedTrackDataInfo() {
        return new EncryptedTrackDataInfo();
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
     * Create an instance of {@link Package }
     * 
     */
    public Package createPackage() {
        return new Package();
    }

    /**
     * Create an instance of {@link ClientIndex }
     * 
     */
    public ClientIndex createClientIndex() {
        return new ClientIndex();
    }

    /**
     * Create an instance of {@link GiftCardInfo }
     * 
     */
    public GiftCardInfo createGiftCardInfo() {
        return new GiftCardInfo();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
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
     * Create an instance of {@link CashInfo }
     * 
     */
    public CashInfo createCashInfo() {
        return new CashInfo();
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
     * Create an instance of {@link StoredCardInfo }
     * 
     */
    public StoredCardInfo createStoredCardInfo() {
        return new StoredCardInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndexValue.class)
    public JAXBElement<Integer> createClientIndexValueID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, ClientIndexValue.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "TaxTotal", scope = ShoppingCart.class)
    public JAXBElement<Double> createShoppingCartTaxTotal(Double value) {
        return new JAXBElement<Double>(_ShoppingCartTaxTotal_QNAME, Double.class, ShoppingCart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "GrandTotal", scope = ShoppingCart.class)
    public JAXBElement<Double> createShoppingCartGrandTotal(Double value) {
        return new JAXBElement<Double>(_ShoppingCartGrandTotal_QNAME, Double.class, ShoppingCart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "SubTotal", scope = ShoppingCart.class)
    public JAXBElement<Double> createShoppingCartSubTotal(Double value) {
        return new JAXBElement<Double>(_ShoppingCartSubTotal_QNAME, Double.class, ShoppingCart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "DiscountTotal", scope = ShoppingCart.class)
    public JAXBElement<Double> createShoppingCartDiscountTotal(Double value) {
        return new JAXBElement<Double>(_ShoppingCartDiscountTotal_QNAME, Double.class, ShoppingCart.class, value);
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
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Color.class)
    public JAXBElement<Integer> createColorID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, Color.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = CartItem.class)
    public JAXBElement<Integer> createCartItemID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, CartItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Quantity", scope = CartItem.class)
    public JAXBElement<Integer> createCartItemQuantity(Integer value) {
        return new JAXBElement<Integer>(_CartItemQuantity_QNAME, Integer.class, CartItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "SaveInfo", scope = CreditCardInfo.class)
    public JAXBElement<Boolean> createCreditCardInfoSaveInfo(Boolean value) {
        return new JAXBElement<Boolean>(_CreditCardInfoSaveInfo_QNAME, Boolean.class, CreditCardInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Amount", scope = CreditCardInfo.class)
    public JAXBElement<BigDecimal> createCreditCardInfoAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CreditCardInfoAmount_QNAME, BigDecimal.class, CreditCardInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Staff.class)
    public JAXBElement<Long> createStaffID(Long value) {
        return new JAXBElement<Long>(_ClientIndexValueID_QNAME, Long.class, Staff.class, value);
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
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, ClassSchedule.class, value);
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
        return new JAXBElement<Integer>(_ClassScheduleSemesterID_QNAME, Integer.class, ClassSchedule.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Size.class)
    public JAXBElement<Integer> createSizeID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, Size.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientService.class)
    public JAXBElement<Long> createClientServiceID(Long value) {
        return new JAXBElement<Long>(_ClientIndexValueID_QNAME, Long.class, ClientService.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClientIndex.class)
    public JAXBElement<Integer> createClientIndexID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, ClientIndex.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Appointment.class)
    public JAXBElement<Long> createAppointmentID(Long value) {
        return new JAXBElement<Long>(_ClientIndexValueID_QNAME, Long.class, Appointment.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Resource.class)
    public JAXBElement<Integer> createResourceID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, Resource.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "OnlinePrice", scope = Service.class)
    public JAXBElement<BigDecimal> createServiceOnlinePrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServiceOnlinePrice_QNAME, BigDecimal.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Count", scope = Service.class)
    public JAXBElement<Integer> createServiceCount(Integer value) {
        return new JAXBElement<Integer>(_ServiceCount_QNAME, Integer.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "TaxIncluded", scope = Service.class)
    public JAXBElement<BigDecimal> createServiceTaxIncluded(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServiceTaxIncluded_QNAME, BigDecimal.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Price", scope = Service.class)
    public JAXBElement<BigDecimal> createServicePrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServicePrice_QNAME, BigDecimal.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = SessionType.class)
    public JAXBElement<Integer> createSessionTypeID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, SessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = Class.class)
    public JAXBElement<Integer> createClassID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "EndDateTime", scope = Class.class)
    public JAXBElement<XMLGregorianCalendar> createClassEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppointmentEndDateTime_QNAME, XMLGregorianCalendar.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "StartDateTime", scope = Class.class)
    public JAXBElement<XMLGregorianCalendar> createClassStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppointmentStartDateTime_QNAME, XMLGregorianCalendar.class, Class.class, value);
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
        return new JAXBElement<Integer>(_ClassScheduleSemesterID_QNAME, Integer.class, Class.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "GroupID", scope = Product.class)
    public JAXBElement<Integer> createProductGroupID(Integer value) {
        return new JAXBElement<Integer>(_ProductGroupID_QNAME, Integer.class, Product.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "OnlinePrice", scope = Product.class)
    public JAXBElement<BigDecimal> createProductOnlinePrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServiceOnlinePrice_QNAME, BigDecimal.class, Product.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "TaxIncluded", scope = Product.class)
    public JAXBElement<BigDecimal> createProductTaxIncluded(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServiceTaxIncluded_QNAME, BigDecimal.class, Product.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "Price", scope = Product.class)
    public JAXBElement<BigDecimal> createProductPrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ServicePrice_QNAME, BigDecimal.class, Product.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://clients.mindbodyonline.com/api/0_5", name = "ID", scope = ClassDescription.class)
    public JAXBElement<Integer> createClassDescriptionID(Integer value) {
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, ClassDescription.class, value);
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
        return new JAXBElement<Integer>(_ClientIndexValueID_QNAME, Integer.class, Location.class, value);
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

}
