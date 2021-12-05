package com.example.transferhall.web.controllers;

import com.example.transferhall.models.SecurityUser;
import com.example.transferhall.models.dto.AddInquiryOrOrderDTO;
import com.example.transferhall.models.dto.InvoiceDetailsDTO;
import com.example.transferhall.models.dto.ShippingDetailsDTO;
import com.example.transferhall.models.dto.OrderDetailsDTO;
import com.example.transferhall.models.views.ProfileOrderView;
import com.example.transferhall.service.OrdersService;
import com.example.transferhall.service.UsersService;
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
    public UserController(OrdersService ordersService, UsersService usersService) {
        this.ordersService = ordersService;
        this.usersService = usersService;
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
        return "profile/order_details";
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
