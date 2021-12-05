package com.example.transferhall.web.controllers.admin;

import com.example.transferhall.models.views.UserStatisticsView;
import com.example.transferhall.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrdersService ordersService;

    public AdminController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public String adminIndex(Model model){
        UserStatisticsView userStatistics = ordersService.getUserStatistics();
        model.addAttribute("userStatistics", userStatistics);
        return "admin/index";
    }


}
