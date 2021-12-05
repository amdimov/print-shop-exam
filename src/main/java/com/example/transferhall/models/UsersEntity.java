package com.example.transferhall.models;

import com.example.transferhall.models.enums.UserRoleEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedEntityGraph(
        name="user-orders",
        attributeNodes = {
                @NamedAttributeNode("orders")
        }
)
public class UsersEntity extends BaseEntity {
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRolesEntity> roles;

    @Column
    private LocalDateTime registered;

    @Column(name = "is_it_deleted", nullable = false)
    private Boolean isItDelated;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<OrdersEntity> orders;

    @OneToOne(cascade = CascadeType.PERSIST)
    private InvoiceDetailsEntity invoiceDetails;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ShippingDetailsEntity shippingDetails;

    public void setShippingDetails(ShippingDetailsEntity shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public ShippingDetailsEntity getShippingDetails() {
        return shippingDetails;
    }

    public void setInvoiceDetails(InvoiceDetailsEntity invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public InvoiceDetailsEntity getInvoiceDetails() {
        return invoiceDetails;
    }

    public UsersEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UsersEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public UsersEntity setRegistered(LocalDateTime registered) {
        this.registered = registered;
        return this;
    }

    @PrePersist
    private void dateCreatedAndIsItDelatedFalse(){
        setItDelated(false);
        setRegistered(LocalDateTime.now());
    }

    public Boolean getItDelated() {
        return isItDelated;
    }

    public UsersEntity setItDelated(Boolean itDelated) {
        this.isItDelated = itDelated;
        return this;
    }




    public String getCompanyName() {
        return companyName;
    }

    public UsersEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public UsersEntity setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public Set<UserRolesEntity> getRoles() {
        return roles;
    }

    public UsersEntity setRoles(Set<UserRolesEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<OrdersEntity> getOrders() {
        return orders;
    }

    public UsersEntity setOrders(List<OrdersEntity> orders) {
        this.orders = orders;
        return this;
    }
}
