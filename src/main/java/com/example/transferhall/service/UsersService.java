package com.example.transferhall.service;

import com.example.transferhall.models.dto.InvoiceDetailsDTO;
import com.example.transferhall.models.dto.ShippingDetailsDTO;
import com.example.transferhall.models.dto.UserDetailsDTO;
import com.example.transferhall.models.serviceModels.UserRegisterServiceModel;
import com.example.transferhall.models.views.PendingUsersView;

import java.util.List;
import java.util.Optional;


public interface UsersService {
    void register(UserRegisterServiceModel userRegisterServiceModel);
    boolean isEmailAlreadyTaken(String email);
    Optional<InvoiceDetailsDTO> getInvoiceDetails(String email);
    Optional<ShippingDetailsDTO> getShippingDetails(String email);
    Optional<InvoiceDetailsDTO> editInvoiceDetailsOfUser(InvoiceDetailsDTO binding, String email);
    Optional<ShippingDetailsDTO> editShippingDetailsOfUser(ShippingDetailsDTO shippingDetailsDTO, String email);
    List<PendingUsersView> getAllPendingUsers();
    Optional<PendingUsersView> getPendingUserByIdBasicInfo(Long id);
    Optional<UserDetailsDTO> getDetailedUserById(Long id);
}
