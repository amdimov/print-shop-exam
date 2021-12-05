package com.example.transferhall.service.Impl;

import com.example.transferhall.models.InvoiceDetailsEntity;
import com.example.transferhall.models.ShippingDetailsEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.dto.InvoiceDetailsDTO;
import com.example.transferhall.models.dto.ShippingDetailsDTO;
import com.example.transferhall.models.dto.UserDetailsDTO;
import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.models.serviceModels.UserRegisterServiceModel;
import com.example.transferhall.models.views.PendingUsersView;
import com.example.transferhall.repository.RolesRepository;
import com.example.transferhall.repository.UserRepository;
import com.example.transferhall.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    public UsersServiceImpl(UserRepository userRepository,
                            ModelMapper modelMapper,
                            PasswordEncoder passwordEncoder,
                            RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }


    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        UsersEntity user = modelMapper.map(userRegisterServiceModel, UsersEntity.class);
        user.setRoles(Set.of(
                        rolesRepository.getUserRolesEntitiesByRole(UserRoleEnum.USER)
                ))
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        user.setInvoiceDetails(new InvoiceDetailsEntity()
                .setCompanyName(user.getCompanyName())
                .setNameIssuedTo(user.getFirstName() + " " + user.getLastName())
                .setCountry("Deutschland"));
        this.userRepository.save(user);
    }

    @Override
    public boolean isEmailAlreadyTaken(String email) {
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(email);
        return user.isPresent();
    }

    @Override
    public Optional<InvoiceDetailsDTO> getInvoiceDetails(String email) {
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(email);
        if (user.isPresent() && user.get().getInvoiceDetails() != null) {
            return Optional.of(
                    modelMapper.map(user.get()
                                    .getInvoiceDetails(),
                            InvoiceDetailsDTO.class)
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<ShippingDetailsDTO> getShippingDetails(String email) {
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(email);
        if (user.isPresent() && user.get().getShippingDetails() != null) {
            return Optional.of(
                    modelMapper.map(user.get()
                                    .getShippingDetails(),
                            ShippingDetailsDTO.class)
            );
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<InvoiceDetailsDTO> editInvoiceDetailsOfUser(InvoiceDetailsDTO binding, String email) {
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(email);
        if (user.isPresent() && user.get().getInvoiceDetails() != null) {
            InvoiceDetailsEntity invoiceDetails = user.get().getInvoiceDetails();
            mapInvoiceFromDTO(invoiceDetails, binding);
            user.get().setInvoiceDetails(invoiceDetails);
            userRepository.save(user.get());
            return Optional.of(modelMapper.map(invoiceDetails, InvoiceDetailsDTO.class));
        }
        InvoiceDetailsEntity invoice = mapInvoiceFromDTO(new InvoiceDetailsEntity(), binding);
        user.get().setInvoiceDetails(invoice);
        userRepository.save(user.get());
        return Optional.of(modelMapper.map(invoice, InvoiceDetailsDTO.class));
    }

    @Transactional
    @Override
    public Optional<ShippingDetailsDTO> editShippingDetailsOfUser(ShippingDetailsDTO binding, String email) {
        Optional<UsersEntity> user = userRepository.findUsersEntityByEmail(email);
        if (user.isPresent() && user.get().getShippingDetails() != null) {
            ShippingDetailsEntity shippingDetails = user.get().getShippingDetails();
            mapShippingFromDTO(shippingDetails, binding);
            user.get().setShippingDetails(shippingDetails);
            userRepository.save(user.get());
            return Optional.of(modelMapper.map(shippingDetails, ShippingDetailsDTO.class));
        }
        ShippingDetailsEntity shippingDetailsEntity = mapShippingFromDTO(new ShippingDetailsEntity(), binding);
        user.get().setShippingDetails(shippingDetailsEntity);
        userRepository.save(user.get());
        return Optional.of(modelMapper.map(shippingDetailsEntity, ShippingDetailsDTO.class));
    }

    @Override
    public List<PendingUsersView> getAllPendingUsers() {
//        return userRepository.findAll()
//                .stream().filter(
//                        usersEntity -> usersEntity.getOrders().stream().map(e -> e.getOrderStatus()
//                                .equals(OrderStatusEnum.PENDING)).findAny().orElse(false)
//                ).map(e -> modelMapper.map(e, PendingUsersView.class))
//                .collect(Collectors.toList());
        return userRepository.findAllUsersWithOrderStatus(OrderStatusEnum.PENDING)
                .stream().map(user -> modelMapper.map(user, PendingUsersView.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PendingUsersView> getPendingUserByIdBasicInfo(Long id) {
        return userRepository.findById(id).map(user ->
                modelMapper.map(user, PendingUsersView.class));
    }

    @Override
    public Optional<UserDetailsDTO> getDetailedUserById(Long id) {
        Optional<UsersEntity> user = userRepository.findById(id);
        return user.map(usersEntity -> modelMapper.map(usersEntity, UserDetailsDTO.class));
    }


    private InvoiceDetailsEntity mapInvoiceFromDTO(InvoiceDetailsEntity invoice, InvoiceDetailsDTO binding) {
        return invoice.setCreated(LocalDateTime.now())
                .setCompanyName(binding.getCompanyName())
                .setCompanyAdress(binding.getCompanyAdress())
                .setNameIssuedTo(binding.getNameIssuedTo())
                .setCountry(binding.getCountry())
                .setCurrency(CurrencyEnum.EUR)
                .setVatNumber(binding.getVatNumber())
                .setNote(binding.getNote());
    }
    private ShippingDetailsEntity mapShippingFromDTO(ShippingDetailsEntity shipping, ShippingDetailsDTO binding) {
        return shipping.setCountry(binding.getCountry())
                .setCity(binding.getCity())
                .setPhoneNumber(binding.getPhoneNumber())
                .setPostCode(binding.getPostCode())
                .setCreated(LocalDateTime.now())
                .setFullname(binding.getFullname())
                .setDeliveryAddress(binding.getDeliveryAddress());


    }


}
