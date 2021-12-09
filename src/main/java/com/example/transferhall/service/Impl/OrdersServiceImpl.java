package com.example.transferhall.service.Impl;

import com.example.transferhall.models.InvoicesEntity;
import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.UserRolesEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.bindingModels.admin.binding.OrderDetailsWrapper;
import com.example.transferhall.models.dto.AddInquiryOrOrderDTO;
import com.example.transferhall.models.dto.InvoiceDTO;
import com.example.transferhall.models.enums.*;
import com.example.transferhall.models.dto.OrderDetailsDTO;
import com.example.transferhall.models.views.ProfileOrderView;
import com.example.transferhall.models.views.UserStatisticsView;
import com.example.transferhall.repository.InvoiceRepository;
import com.example.transferhall.repository.OrdersRepository;
import com.example.transferhall.repository.UserRepository;
import com.example.transferhall.service.OrdersService;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryFile;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryUploadIfc;
import com.example.transferhall.util.exceptions.PageNotFoundException;
import com.example.transferhall.util.exceptions.UserNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final CloudinaryUploadIfc cloudinaryUpload;
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);


    public OrdersServiceImpl(CloudinaryUploadIfc cloudinaryUpload,
                             UserRepository userRepository,
                             OrdersRepository ordersRepository,
                             InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.cloudinaryUpload = cloudinaryUpload;
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<ProfileOrderView> showOrdersInfo(String email) {
        Optional<UsersEntity> usersEntity = userRepository.findUsersEntityByEmail(email);
        List<ProfileOrderView> profileOrderViews = usersEntity.map(entity -> entity.getOrders()
                .stream()
                .map(e -> modelMapper.map(e, ProfileOrderView.class))
                .sorted(Comparator.comparing(ProfileOrderView::getCreated).reversed())
                .collect(Collectors.toList())).orElse(null);
        return profileOrderViews;
    }

    @Transactional
    @Override
    public List<OrderDetailsDTO> showAllOrderDetailsByStatus(String email, OrderStatusEnum status) {
        Optional<UsersEntity> usersEntity = userRepository.findUsersEntityByEmail(email);
        List<OrderDetailsDTO> profileOrderViews = usersEntity.map(entity -> entity.getOrders()
                .stream()
                .filter(order -> order.getOrderStatus().equals(status))
                .map(e -> modelMapper.map(e, OrderDetailsDTO.class))
                .sorted(Comparator.comparing(OrderDetailsDTO::getCreated).reversed())
                .collect(Collectors.toList())).orElse(null);
        return profileOrderViews;
    }

    @Override
    public Boolean addInquiryOrOrder(AddInquiryOrOrderDTO orderBind) {
        if (orderBind == null) {
            return false;
        }
        try {
            CloudinaryFile cloudinaryFile =
                    cloudinaryUpload.addFileToCloudinary(orderBind.getOrderFile());
            OrdersEntity order = modelMapper.map(orderBind, OrdersEntity.class);
            Principal currentUser = SecurityContextHolder.getContext().getAuthentication();
            order.setOrderCode(RandomStringUtils.random(11, true, true))
                    .setOrderDescription(orderBind.getOrderDescription())
                    .setOrderStatus(OrderStatusEnum.PENDING)
                    .setFileUrl(cloudinaryFile.getUrl())
                    .setPublicId(cloudinaryFile.getPublicId())
                    .setDiscountPercentage(0)
                    .setConfirmedOrder(false)
                    .setUnitTypeEnum(UnitTypeEnum.PCS)
                    .setCurrency(CurrencyEnum.EUR).setCreated(LocalDateTime.now())
                    .setUsers(userRepository.findUsersEntityByEmail(currentUser.getName()).get())
                    .setTransferCategory(TransferCategoryEnum.valueOf(orderBind.getTransferCategory()));

            ordersRepository.save(order);
            LOGGER.info(orderBind.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public OrderDetailsDTO getOrderById(Long id) {
        Optional<OrdersEntity> order = ordersRepository.findById(id);
        if (order.isEmpty()) {
            throw new PageNotFoundException();
        }
        return modelMapper.map(order.get(), OrderDetailsDTO.class);
    }

    @Override
    public boolean isOwner(String email, Long id) {
        Optional<OrdersEntity> orderOpt = ordersRepository.findById(id);
        Optional<UsersEntity> userOpt = userRepository.findUsersEntityByEmail(email);
        if (orderOpt.isEmpty() || userOpt.isEmpty()) {
            return false;
        } else {
            OrdersEntity order = orderOpt.get();
            return isAdmin(userOpt.get()) ||
                    order.getUsers().getEmail().equals(email);
        }
    }

    @Override
    public UserStatisticsView getUserStatistics() {
        return new UserStatisticsView().setNumberOfUsers(userRepository.count())
                .setTotalNumberOfOrders(ordersRepository.count())
                .setNumberOfPendingOrders(ordersRepository.findAllByOrderStatus(OrderStatusEnum.PENDING).size());

    }

    @Override
    public List<OrderDetailsDTO> getSelectedInvoiceById(List<Long> ids) {
        return ordersRepository.findAllById(ids)
                .stream().map(order -> modelMapper.map(order, OrderDetailsDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO, Long userId, List<OrderDetailsDTO> markedOrders) {
        UsersEntity user = userRepository.getById(userId);
        InvoicesEntity invoice = modelMapper.map(invoiceDTO, InvoicesEntity.class);
        List<OrdersEntity> orders = markedOrders.stream().map(order -> modelMapper.
                map(order, OrdersEntity.class)).collect(Collectors.toList());
        invoice.setUsers(user).setIssuedDate(LocalDate.now())
                .setInvoiceNumber(RandomStringUtils.random(11, true, true))
                .setPayed(false);
        if (invoiceDTO.getAddVat() != null) {
            BigDecimal totalAmount = invoice.getTotalAmount();
            BigDecimal vatTax = BigDecimal.valueOf(0.2).multiply(totalAmount);
            invoice.setTotalAmount(totalAmount.subtract(vatTax));
        }
        invoice.setOrders(orders);
        invoiceRepository.save(invoice);
        return Optional.of(modelMapper.map(InvoicesEntity.class, InvoiceDTO.class));
    }

    @Transactional
    @Override
    public Optional<InvoiceDTO> createNewInvoice(OrderDetailsWrapper selectedOrders, Long userId) {
        UsersEntity user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("User with provided id not found"));
        List<OrdersEntity> orders = ordersRepository.findAllById(selectedOrders.getOrders().stream().map(OrderDetailsDTO::getId).
                collect(Collectors.toList()));
        BigDecimal totalAmountOfInvoice = BigDecimal.valueOf(0);
        totalAmountOfInvoice = mapOrdersAndGetTotalAmount(selectedOrders, orders, totalAmountOfInvoice);
        ordersRepository.saveAll(orders);

        if (userHasNoInvoiceOrNoOtherPayed(user)) {
            InvoicesEntity invoice = makeNewInvoice(user, totalAmountOfInvoice, orders);
            invoiceRepository.save(invoice);
            return Optional.of(modelMapper.map(invoice, InvoiceDTO.class));
        }else {
            Optional<InvoicesEntity> editInvoice = invoiceRepository
                    .findByPayedIsFalse();
            if (editInvoice.isPresent()){
                editInvoice(user, totalAmountOfInvoice, editInvoice);
                orders.forEach(order -> order.setInvoices(editInvoice.get()));
                invoiceRepository.save(editInvoice.get());
                return Optional.of(modelMapper.map(editInvoice.get(), InvoiceDTO.class));
            }
            return Optional.empty();
        }
    }

    private void editInvoice(UsersEntity user, BigDecimal totalAmountOfInvoice, Optional<InvoicesEntity> editInvoice) {
        editInvoice.get().setUsers(user)
                .setPayed(false).setIssuedDate(LocalDate.now())
                .setTotalAmount(totalAmountOfInvoice);
    }

    private BigDecimal mapOrdersAndGetTotalAmount(OrderDetailsWrapper selectedOrders, List<OrdersEntity> orders, BigDecimal totalAmountOfInvoice) {
        for (OrdersEntity order : orders)
            for (OrderDetailsDTO orderDTO : selectedOrders.getOrders())
                if (Objects.equals(order.getId(), orderDTO.getId())) {
                    BigDecimal currentOrderValue = orderDTO.getPricePerUnit()
                            .multiply(BigDecimal.valueOf(orderDTO.getQuantity()));
                    totalAmountOfInvoice = totalAmountOfInvoice.add(currentOrderValue);
                    order.setOrderName(orderDTO.getOrderName())
                            .setQuantity(orderDTO.getQuantity())
                            .setNumOfColors(orderDTO.getNumOfColors())
                            .setPricePerUnit(orderDTO.getPricePerUnit())
                            .setTotalPrice(currentOrderValue)
                            .setMessageFromAdmin(orderDTO.getMessageFromAdmin());
                }
        return totalAmountOfInvoice;
    }

    private InvoicesEntity makeNewInvoice(UsersEntity user, BigDecimal totalAmountOfInvoice,
                                          List<OrdersEntity> orders) {
        InvoicesEntity invoice = new InvoicesEntity()
                .setUsers(user)
                .setInvoiceNumber(RandomStringUtils.random(11, false, true))
                .setPayed(false).setIssuedDate(LocalDate.now())
                .setTotalAmount(totalAmountOfInvoice);
        orders.forEach(order -> order.setInvoices(invoice));
        invoice.setOrders(orders);
        return invoice;
    }

    private boolean userHasNoInvoiceOrNoOtherPayed(UsersEntity user){
        return user.getInvoices().stream().allMatch(InvoicesEntity::getPayed);
    }

    private boolean isAdmin(UsersEntity usersEntity) {
        return usersEntity.getRoles()
                .stream().map(UserRolesEntity::getRole)
                .anyMatch(r -> r.equals(UserRoleEnum.ADMIN));
    }

}
