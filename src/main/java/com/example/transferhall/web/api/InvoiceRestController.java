package com.example.transferhall.web.api;

import com.example.transferhall.models.dto.InvoiceDTO;
import com.example.transferhall.models.dto.UserDetailsDTO;
import com.example.transferhall.service.InvoiceService;
import com.example.transferhall.service.UsersService;
import com.example.transferhall.util.exceptions.UnauthorizedRequest;
import com.example.transferhall.util.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {
    private final UsersService usersService;
    private final InvoiceService invoiceService;

    public InvoiceRestController(UsersService usersService, InvoiceService invoiceService) {
        this.usersService = usersService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{userId}/invoices")
    public ResponseEntity<List<InvoiceDTO>> getInvoices(@PathVariable Long userId, Principal principal){
        UserDetailsDTO user = usersService.getDetailedUserById(userId)
                .orElseThrow(()-> new UserNotFoundException("User with this id not found"));
        if (!user.getEmail().equals(principal.getName())){
            throw new UnauthorizedRequest("Forbidden access");
        }
        List<InvoiceDTO> allInvoicesOfUser = invoiceService.getAllInvoicesOfUser(userId);
        return ResponseEntity.ok(allInvoicesOfUser);
    }
}
