package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.service.facade.admin.customer.CustomerAdminService;
import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.service.facade.admin.delivery.DeliveryStatusAdminService;
import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.service.facade.admin.order.OrderStatusAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;

    @Value("${server.ssl.key-store-password}")
    private String zynkey;
    @Value("${server.ssl.key-store-path}")
    String keystorePath;

    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }



    @Bean
    public RestTemplate restTemplate() throws Exception {
        char[] keystorePassword = zynkey.toCharArray();
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileSystemResource(keystorePath).getInputStream(), keystorePassword);
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, keystorePassword)
                .loadTrustMaterial(keyStore, (chain, authType) -> true)  // Trust strategy that accepts all certificates
                .build();
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(
                        SSLConnectionSocketFactoryBuilder.create()
                                .setSslContext(sslContext)
                                .build()
                )
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , CustomerAdminService customerService) {
    return (args) -> {
        if(true){

            createDeliveryStatus();
            createOrderStatus();

        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Customer
        Customer userForCustomer = new Customer("customer");
		userForCustomer.setPassword("123");
		// Role Customer
		Role roleForCustomer = new Role();
		roleForCustomer.setAuthority(AuthoritiesConstants.CUSTOMER);
		roleForCustomer.setCreatedAt(LocalDateTime.now());
		Role roleForCustomerSaved = roleService.create(roleForCustomer);
		RoleUser roleUserForCustomer = new RoleUser();
		roleUserForCustomer.setRole(roleForCustomerSaved);
		if (userForCustomer.getRoleUsers() == null)
			userForCustomer.setRoleUsers(new ArrayList<>());

		userForCustomer.getRoleUsers().add(roleUserForCustomer);
		if (userForCustomer.getModelPermissionUsers() == null)
			userForCustomer.setModelPermissionUsers(new ArrayList<>());


        userForCustomer.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        customerService.create(userForCustomer);

            }
        };
    }



    private void createDeliveryStatus(){
            DeliveryStatus itemSuccess = new DeliveryStatus();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Delivered");
            itemSuccess.setCode("Delivered");
            deliveryStatusService.create(itemSuccess);
            DeliveryStatus itemPrimary = new DeliveryStatus();
            itemPrimary.setStyle("primary");
            itemPrimary.setLabel("InProgress");
            itemPrimary.setCode("InProgress");
            deliveryStatusService.create(itemPrimary);
            DeliveryStatus itemDanger = new DeliveryStatus();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Cancelled");
            itemDanger.setCode("Cancelled");
            deliveryStatusService.create(itemDanger);
            DeliveryStatus itemWarning = new DeliveryStatus();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Pending");
            itemWarning.setCode("Pending");
            deliveryStatusService.create(itemWarning);

    }
    private void createOrderStatus(){
            OrderStatus itemSuccess = new OrderStatus();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Completed");
            itemSuccess.setCode("Completed");
            orderStatusService.create(itemSuccess);
            OrderStatus itemPrimary = new OrderStatus();
            itemPrimary.setStyle("primary");
            itemPrimary.setLabel("InProgress");
            itemPrimary.setCode("InProgress");
            orderStatusService.create(itemPrimary);
            OrderStatus itemDanger = new OrderStatus();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Cancelled");
            itemDanger.setCode("Cancelled");
            orderStatusService.create(itemDanger);
            OrderStatus itemWarning = new OrderStatus();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Pending");
            itemWarning.setCode("Pending");
            orderStatusService.create(itemWarning);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Address"));
        modelPermissions.add(new ModelPermission("City"));
        modelPermissions.add(new ModelPermission("Delivery"));
        modelPermissions.add(new ModelPermission("DeliveryStatus"));
        modelPermissions.add(new ModelPermission("Order"));
        modelPermissions.add(new ModelPermission("OrderStatus"));
        modelPermissions.add(new ModelPermission("Product"));
        modelPermissions.add(new ModelPermission("ProductCategory"));
        modelPermissions.add(new ModelPermission("OrderItem"));
        modelPermissions.add(new ModelPermission("Customer"));
        modelPermissions.add(new ModelPermission("Country"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    DeliveryStatusAdminService deliveryStatusService;
    @Autowired
    OrderStatusAdminService orderStatusService;
}


