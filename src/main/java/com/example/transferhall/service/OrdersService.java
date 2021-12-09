package com.example.transferhall.service;

import com.example.transferhall.models.bindingModels.admin.binding.OrderDetailsWrapper;
import com.example.transferhall.models.dto.AddInquiryOrOrderDTO;
import com.example.transferhall.models.dto.InvoiceDTO;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.dto.OrderDetailsDTO;
import com.example.transferhall.models.views.ProfileOrderView;
import com.example.transferhall.models.views.UserStatisticsView;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<ProfileOrderView> showOrdersInfo(String email);
    List<OrderDetailsDTO> showAllOrderDetailsByStatus(String email, OrderStatusEnum status);
    Boolean addInquiryOrOrder(AddInquiryOrOrderDTO orderBind);
    OrderDetailsDTO getOrderById(Long id);
    boolean isOwner(String email, Long id);
    UserStatisticsView getUserStatistics();
    List<OrderDetailsDTO> getSelectedInvoiceById(List<Long> ids);
    Optional<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO, Long userId, List<OrderDetailsDTO> markedOrders);
    Optional<InvoiceDTO> createNewInvoice(OrderDetailsWrapper selectedOrders, Long userId);

}
