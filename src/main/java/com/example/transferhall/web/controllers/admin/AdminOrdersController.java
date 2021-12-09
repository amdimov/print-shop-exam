package com.example.transferhall.web.controllers.admin;

import com.example.transferhall.events.ChangeOrderStatusEvent;
import com.example.transferhall.models.bindingModels.admin.binding.OrderDetailsWrapper;
import com.example.transferhall.models.dto.InvoiceDTO;
import com.example.transferhall.models.dto.UserDetailsDTO;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.dto.OrderDetailsDTO;
import com.example.transferhall.models.views.PendingUsersView;
import com.example.transferhall.service.InvoiceService;
import com.example.transferhall.service.OrdersService;
import com.example.transferhall.service.UsersService;
import com.example.transferhall.util.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class AdminOrdersController {
    private final UsersService usersService;
    private final OrdersService ordersService;
    private final InvoiceService invoiceService;
    private final ApplicationEventPublisher applicationPublisher;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminOrdersController.class);

    public AdminOrdersController(UsersService usersService, OrdersService ordersService,
                                 InvoiceService invoiceService, ApplicationEventPublisher applicationPublisher) {
        this.usersService = usersService;
        this.ordersService = ordersService;
        this.invoiceService = invoiceService;
        this.applicationPublisher = applicationPublisher;
    }

    @GetMapping("/manage-orders")
    private String manageOrders(Model model){
        List<PendingUsersView> allPendingUsers = usersService.getAllPendingUsers();
        model.addAttribute("pendingUsers", allPendingUsers);
        return "admin/manage_orders";
    }
    @GetMapping("/manage-orders/order/{id}")
    private String quoteOrder(@PathVariable Long id, Model model){
        Optional<PendingUsersView> user = usersService.getPendingUserByIdBasicInfo(id);
        if (user.isPresent()){
            List<OrderDetailsDTO> orderViews = ordersService.
                    showAllOrderDetailsByStatus(user.get().getEmail(), OrderStatusEnum.PENDING);
            model.addAttribute("user", user.get());
            model.addAttribute("orderView", orderViews);
        }else {
            throw new UserNotFoundException("User with provided id not found");
        }
        return "admin/quote_order";
    }


    @GetMapping("/manage-orders/invoice-creator")
    private String invoiceCreator(@RequestParam(value = "order[]")Optional<List<Long>> ids,
                                  @RequestParam(value = "userId") String userId,
                                  Model model,
                                  RedirectAttributes redirectAttributes){
        if (ids.isEmpty()){
            redirectAttributes.addFlashAttribute("orderEmpty", true);
            return "redirect:/admin/users/manage-orders/order/"+userId;
        }
        List<OrderDetailsDTO> markedOrders = ordersService.getSelectedInvoiceById(ids.get());
        Optional<UserDetailsDTO> userDetails = usersService.getDetailedUserById(Long.parseLong(userId));
        OrderDetailsWrapper orderDetailsWrapper = new OrderDetailsWrapper();
        orderDetailsWrapper.setOrders(markedOrders);
        userDetails.ifPresent(userDetailsDTO -> model.addAttribute("userDetails", userDetailsDTO));
        model.addAttribute("markedOrders", orderDetailsWrapper);
        return "admin/invoice_creator";
    }

    @PatchMapping("/manage-orders/invoice")
    private String createNewInvoice(@ModelAttribute("markedOrders") OrderDetailsWrapper markedOrders,
                                    @RequestParam(value = "userId") Long userId,
                                    RedirectAttributes redirectAttributes){
        System.out.println(markedOrders.getOrders());
        LOGGER.info(markedOrders.getOrders().get(0).getOrderName());
        Optional<InvoiceDTO> newInvoice = ordersService.createNewInvoice(markedOrders, userId);
        ChangeOrderStatusEvent statusEvent = new ChangeOrderStatusEvent(this,
                newInvoice.get().getInvoiceNumber());
        applicationPublisher.publishEvent(statusEvent);
        return "redirect:/admin/users/manage-orders/invoice/"+newInvoice.get().getInvoiceNumber();
    }

    @GetMapping("/manage-orders/invoice/{invoiceNumber}")
    private String invoice(@PathVariable("invoiceNumber") String invoiceNumber, Model model){
        InvoiceDTO invoice = invoiceService.findInvoiceByInvoiceNumber(invoiceNumber);
        model.addAttribute("invoice", invoice);
        return "admin/invoice";
    }



}
