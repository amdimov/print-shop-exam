package com.example.transferhall.web.controllers;

import com.example.transferhall.models.SecurityUser;
import com.example.transferhall.models.dto.*;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.views.ProfileOrderView;
import com.example.transferhall.service.InvoiceService;
import com.example.transferhall.service.OrdersService;
import com.example.transferhall.service.UsersService;
import com.example.transferhall.util.exceptions.InvoiceNotFound;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final InvoiceService invoiceService;

    public UserController(OrdersService ordersService, UsersService usersService,
                          InvoiceService invoiceService) {
        this.ordersService = ordersService;
        this.usersService = usersService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model,
                              @AuthenticationPrincipal SecurityUser user){
        List<ProfileOrderView> orderViews =
                ordersService.showOrdersInfo(user.getUsername());
        model.addAttribute("order", orderViews);
        return "/profile/user_profile";
    }

    @PostMapping("/profile")
    public String addInquiryOrOrder(@Valid AddInquiryOrOrderDTO addInquiryBind,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addInquiry", addInquiryBind);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addInquiry",
                    bindingResult);
            return "redirect:/user/profile#tab-quote";
        }
        ordersService.addInquiryOrOrder(addInquiryBind);
        redirectAttributes.addFlashAttribute("orderSuccessful", true);
        return "redirect:/user/profile#tab-quote";
    }

    @ModelAttribute("addInquiry")
    public AddInquiryOrOrderDTO addInquiryOrOrderBinding(){
        return new AddInquiryOrOrderDTO();
    }


    @PreAuthorize("@ordersServiceImpl.isOwner(#principal.name, #id)")
    @GetMapping("/profile/order/{id}")
    public String orderDetails(@PathVariable Long id, Model model, Principal principal){
        OrderDetailsDTO order = ordersService.getOrderById(id);
        model.addAttribute("order", order);

        if (!order.getOrderStatus().equals(OrderStatusEnum.PENDING)){
            Optional<InvoiceDTO> invoice = invoiceService.findInvoiceByOrderId(id);
            invoice.ifPresent(invoiceDTO -> model.addAttribute("invoiceNumber",
                invoiceDTO.getInvoiceNumber()));
        }
        return "profile/order_details";
    }

    @GetMapping("/profile/invoice/{invoiceNumber}")
    private String invoice(
                           @PathVariable String invoiceNumber,
                           Model model){
        if (invoiceNumber.isBlank() || invoiceNumber.isEmpty()){
            throw new InvoiceNotFound("Invoice for this order hasn't been created yet");
        }

//        OrderDetailsDTO order = ordersService.getOrderById(id);
//        Optional<InvoiceDTO> invoice = invoiceService.findInvoiceByOrderId(order.getId());
        InvoiceDTO invoice = invoiceService.findInvoiceByInvoiceNumber(invoiceNumber);
        model.addAttribute("invoice", invoice);
        return "profile/invoice";
    }


    @GetMapping("/company-details")
    public String companyDetails(){
        return "profile/company_details";
    }

    @GetMapping("/shipping-address")
    public String shippingAddress(){
        return "profile/shipping_address";
    }

    @PatchMapping("/company-details")
    public String companyDetailsEdit(InvoiceDetailsDTO invoiceDetailsDTO, Principal principal,
                                     RedirectAttributes redirectAttributes){
        Optional<InvoiceDetailsDTO> invoiceDetails =
                usersService.editInvoiceDetailsOfUser(invoiceDetailsDTO, principal.getName());
        redirectAttributes.addFlashAttribute("invoiceDetails", invoiceDetails.get());
        return "redirect:/user/company-details";
    }
    @PatchMapping("/shipping-address")
    public String shippingDetailsEdit(ShippingDetailsDTO shippingDetailsDTO, Principal principal,
                                     RedirectAttributes redirectAttributes){
        Optional<ShippingDetailsDTO> shippingDetails =
                usersService.editShippingDetailsOfUser(shippingDetailsDTO, principal.getName());
        redirectAttributes.addFlashAttribute("shippingAddress", shippingDetails.get());
        return "redirect:/user/company-details";
    }

    @ModelAttribute(name = "invoiceDetails")
    public InvoiceDetailsDTO invoiceDetailsDTO(Principal principal){
        return usersService.getInvoiceDetails(principal.getName()).orElse(new InvoiceDetailsDTO());
    }
    @ModelAttribute(name = "shippingAddress")
    public ShippingDetailsDTO shippingDetailsDTO(Principal principal){
        return usersService.getShippingDetails(principal.getName()).orElse(new ShippingDetailsDTO());
    }


}
