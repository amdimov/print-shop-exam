package com.example.transferhall.dbinit;

import com.example.transferhall.models.InvoiceDetailsEntity;
import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.UserRolesEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.TransferCategoryEnum;
import com.example.transferhall.models.enums.UnitTypeEnum;
import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.repository.OrdersRepository;
import com.example.transferhall.repository.RolesRepository;
import com.example.transferhall.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class SeedDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final RolesRepository rolesRepository;
    private OrdersEntity orders;
    private OrdersEntity ordersTwo;
    private UsersEntity user;
    private PasswordEncoder passwordEncoder;

    public SeedDB(UserRepository userRepository,
                  OrdersRepository ordersRepository,
                  RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initAdmin();
//        initOrders();


    }

    private void initSellerDetails(){

    }
    
    private void initAdmin() {
        if (userRepository.count() > 0) {
            return;
        }
        this.user = new UsersEntity();
        user.setFirstName("Admin").setLastName("Adminov")
                .setEmail("admin@admin.com").setCompanyName("Megaprint Transfer")
                .setRoles(
                        Set.of(
                                rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.ADMIN),
                                rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.USER)
                        )
                )
                .setPassword(passwordEncoder.encode("admin"));
        user.setInvoiceDetails(new InvoiceDetailsEntity().setCompanyName(user.getCompanyName())
                .setCountry("Deutschland"));
        userRepository.save(user);
    }

    private void initRoles() {
        if (rolesRepository.count() > 0) {
            return;
        }
        UserRolesEntity adminRole = new UserRolesEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        UserRolesEntity userRole = new UserRolesEntity();
        userRole.setRole(UserRoleEnum.USER);

        rolesRepository.saveAll(List.of(adminRole, userRole));
    }

   

    private void initOrders() {
        if (this.ordersRepository.count() > 0) {
            return;
        }
        this.orders = new OrdersEntity();
        orders.setOrderDescription("Heat Transfer- REBELS - 50x35cm, 1 Colors, 25 sheets")
                .setOrderName("Order Name One")
                .setApparelColor("Dark")
                .setOrderCode(UUID.randomUUID().toString())
                .setTransferCategory(TransferCategoryEnum.MULTISPOT)
                .setQuantity(25).setUnitTypeEnum(UnitTypeEnum.PCS).setPricePerUnit(BigDecimal.valueOf(9.4))
                .setCurrency(CurrencyEnum.EUR).setCreated(LocalDateTime.now());
        this.ordersTwo = new OrdersEntity();
        ordersTwo.setOrderDescription("Heat Transfer- CSS - 50x35cm, 3 Colors, 150 sheets")
                .setOrderName("Order Name Two")
                .setApparelColor("White")
                .setOrderCode(UUID.randomUUID().toString())
                .setTransferCategory(TransferCategoryEnum.SINGLESPOT)
                .setConfirmedOrder(true)
                .setQuantity(150).setUnitTypeEnum(UnitTypeEnum.PCS).setPricePerUnit(BigDecimal.valueOf(6.37))
                .setCurrency(CurrencyEnum.EUR).setCreated(LocalDateTime.now());

        UsersEntity usersEntity = userRepository.findUsersLazyEntityByEmail("amdimov@gmail.com").get();
        orders.setUsers(usersEntity);
        ordersTwo.setUsers(usersEntity);

        this.ordersRepository.saveAll(List.of(orders, ordersTwo));
    }
}
