package com.example.transferhall.service.Impl;

import com.example.transferhall.models.InvoiceDetailsEntity;
import com.example.transferhall.models.ShippingDetailsEntity;
import com.example.transferhall.models.UserRolesEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.dto.InvoiceDetailsDTO;
import com.example.transferhall.models.dto.ShippingDetailsDTO;
import com.example.transferhall.models.dto.UserDetailsDTO;
import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.models.serviceModels.UserRegisterServiceModel;
import com.example.transferhall.models.views.PendingUsersView;
import com.example.transferhall.repository.RolesRepository;
import com.example.transferhall.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    private UsersServiceImpl usersServiceToTest;
    private UserRegisterServiceModel registerModelBindingTest;
    private UserRolesEntity userRolesEntityTest;
    private UsersEntity usersEntityTest;
    private InvoiceDetailsEntity invoiceDetailsTest;
    private ShippingDetailsEntity shippingDetailsTest;
    private InvoiceDetailsDTO invoiceDetailsDTO;
    private ShippingDetailsDTO shippingDetailsDTO;

    @Mock
    private RolesRepository rolesRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void init(){
        this.registerModelBindingTest = new UserRegisterServiceModel()
                .setCompanyName("Company")
                .setPassword("password")
                .setEmail("email@email.com")
                .setConfirmPassword("email@email.com")
                .setFirstName("First")
                .setLastName("Last");
        this.usersEntityTest = new UsersEntity()
                .setCompanyName("Company")
                .setPassword("password")
                .setEmail("email@email.com")
                .setFirstName("First")
                .setLastName("Last");
        this.invoiceDetailsTest = new InvoiceDetailsEntity()
                .setCountry("Not set")
                .setCompanyAdress("Not set")
                .setCompanyName(usersEntityTest.getCompanyName())
                .setNameIssuedTo(usersEntityTest.getFirstName() + " " + usersEntityTest.getLastName())
                .setUsers(this.usersEntityTest);
        this.shippingDetailsTest = new ShippingDetailsEntity()
                .setCountry("Not set")
                .setCity("Not set")
                .setPostCode("Not set")
                .setDeliveryAddress("Not set")
                .setPhoneNumber("Not set")
                .setFullname(usersEntityTest.getFirstName() + " " + usersEntityTest.getLastName())
                .setUsersEntity(this.usersEntityTest);
        invoiceDetailsDTO =
                new InvoiceDetailsDTO()
                        .setCountry("Not set")
                        .setCompanyAdress("Not set")
                        .setCompanyName(usersEntityTest.getCompanyName())
                        .setNameIssuedTo(usersEntityTest.getFirstName() + " " + usersEntityTest.getLastName());
        shippingDetailsDTO =
                new ShippingDetailsDTO()
                        .setCountry("Not set")
                        .setCity("Not set")
                        .setPostCode("Not set")
                        .setDeliveryAddress("Not set")
                        .setPhoneNumber("Not set")
                        .setFullname(usersEntityTest.getFirstName() + " " + usersEntityTest.getLastName());

        this.usersServiceToTest = new UsersServiceImpl(userRepository, modelMapper, passwordEncoder, rolesRepository);
        
        userRolesEntityTest = new UserRolesEntity().setRole(UserRoleEnum.USER);

//        this.usersServiceToTest.register(this.registerModelBindingTest);
    }

    @Test
    void registerTest(){
        Mockito.when(rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.USER))
                .thenReturn(userRolesEntityTest);
        Mockito.when(modelMapper.map(registerModelBindingTest, UsersEntity.class))
                .thenReturn(this.usersEntityTest);
        Mockito.when(userRepository.save(this.usersEntityTest))
                .thenReturn(this.usersEntityTest);
        UsersEntity user = modelMapper.map(registerModelBindingTest, UsersEntity.class);
        user.setRoles(Set.of(rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.USER)));
        user.setInvoiceDetails(this.invoiceDetailsTest);
        user.setShippingDetails(this.shippingDetailsTest);

        Assertions.assertEquals(user.getCompanyName(), registerModelBindingTest.getCompanyName());
        Assertions.assertEquals(user.getFirstName(), registerModelBindingTest.getFirstName());
        Assertions.assertEquals(user.getEmail(), registerModelBindingTest.getEmail());
        Assertions.assertEquals(user.getRoles(), Set.of(rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.USER)));
        Assertions.assertEquals(this.usersEntityTest, modelMapper.map(this.registerModelBindingTest, UsersEntity.class));
        Assertions.assertEquals(this.usersEntityTest, userRepository.save(this.usersEntityTest));
        Assertions.assertEquals(user.getInvoiceDetails(), this.invoiceDetailsTest);
        Assertions.assertEquals(user.getShippingDetails(), this.shippingDetailsTest);
        Assertions.assertEquals(user.getShippingDetails().getCity(), this.shippingDetailsTest.getCity());
        Assertions.assertEquals(user.getShippingDetails().getFullname(), "First Last");
        Assertions.assertEquals(user.getShippingDetails().getCountry(), "Not set");
        Assertions.assertEquals(user.getInvoiceDetails().getCompanyName(), this.invoiceDetailsTest.getCompanyName());
        Assertions.assertEquals(this.invoiceDetailsTest.getUsers(), this.usersEntityTest);
        Assertions.assertEquals(this.shippingDetailsTest.getUsersEntity(), this.usersEntityTest);
    }

    @Test
    void isEmailAlreadyTakenTest(){
        Mockito.when(userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail()))
                .thenReturn(Optional.of(this.usersEntityTest));
        boolean expectedTrue = this.usersServiceToTest.isEmailAlreadyTaken(this.usersEntityTest.getEmail());
        Assertions.assertTrue(expectedTrue);
    }

    @Test
    void getInvoiceDetails(){

        Mockito.when(userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail()))
                .thenReturn(Optional.of(this.usersEntityTest));
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail());
        user.get().setInvoiceDetails(this.invoiceDetailsTest);

        Mockito.when(this.modelMapper.map(user.get().getInvoiceDetails(), InvoiceDetailsDTO.class))
                .thenReturn(invoiceDetailsDTO);
        Optional<InvoiceDetailsDTO> userInvDTO = this.usersServiceToTest.getInvoiceDetails(this.usersEntityTest.getEmail());
        Assertions.assertEquals(userInvDTO.get().getCompanyName(), invoiceDetailsDTO.getCompanyName());
        Assertions.assertEquals(this.usersServiceToTest.getInvoiceDetails("another@email.com"),
                Optional.empty());
    }
    @Test
    void getShippingDetailsTest(){

        Mockito.when(userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail()))
                .thenReturn(Optional.of(this.usersEntityTest));
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail());
        user.get().setShippingDetails(this.shippingDetailsTest);

        Mockito.when(this.modelMapper.map(user.get().getShippingDetails(), ShippingDetailsDTO.class))
                .thenReturn(shippingDetailsDTO);
        Optional<ShippingDetailsDTO> userInvDTO = this.usersServiceToTest.getShippingDetails(this.usersEntityTest.getEmail());
        Assertions.assertEquals(userInvDTO.get().getCountry(), shippingDetailsDTO.getCountry());
        Assertions.assertEquals(this.usersServiceToTest.getShippingDetails("another@email.com"),
                Optional.empty());
    }

    @Test
    void getDetailedUserByIdTest(){
        Long id = 1L;
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO()
                .setCompanyName("Company")
                .setEmail("email@email.com")
                .setFirstName("First")
                .setLastName("Last");

        Mockito.when(this.userRepository.findById(id))
                .thenReturn(Optional.of(this.usersEntityTest));
        Mockito.when(this.modelMapper.map(this.usersEntityTest, UserDetailsDTO.class))
                .thenReturn(userDetailsDTO);

        Optional<UserDetailsDTO> actualDto = this.usersServiceToTest.getDetailedUserById(id);
        Assertions.assertEquals(actualDto.get().getCompanyName(), userDetailsDTO.getCompanyName());
    }

    @Test
    void getPendingUserByIdBasicInfoTest(){
        Long id = 1L;
        PendingUsersView pendingUsersView = new PendingUsersView()
                .setId(id)
                .setDiscountPercent(0)
                .setCompanyName("Company")
                .setEmail("email@email.com");
        Mockito.when(this.userRepository.findById(id))
                .thenReturn(Optional.of(this.usersEntityTest));
        Mockito.when(this.modelMapper.map(this.usersEntityTest, PendingUsersView.class))
                .thenReturn(pendingUsersView);
        Optional<PendingUsersView> actualDto = this.usersServiceToTest.getPendingUserByIdBasicInfo(id);
        Assertions.assertEquals(actualDto.get().getCompanyName(), pendingUsersView.getCompanyName());
    }

    @Test
    void editShippingDetailsOfUserTest(){

        Mockito.when(userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail()))
                .thenReturn(Optional.of(this.usersEntityTest));
        this.usersEntityTest.setShippingDetails(this.shippingDetailsTest);
        Mockito.when(modelMapper.map(this.usersEntityTest.getShippingDetails(), ShippingDetailsDTO.class))
                .thenReturn(this.shippingDetailsDTO);
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(this.usersEntityTest.getEmail());
        user.get().setShippingDetails(this.shippingDetailsTest);
        Optional<ShippingDetailsDTO> actualDTO = this.usersServiceToTest
                .editShippingDetailsOfUser(this.shippingDetailsDTO, user.get().getEmail());

        Assertions.assertEquals(actualDTO.get().getCountry(), user.get().getShippingDetails().getCountry());

    }





}