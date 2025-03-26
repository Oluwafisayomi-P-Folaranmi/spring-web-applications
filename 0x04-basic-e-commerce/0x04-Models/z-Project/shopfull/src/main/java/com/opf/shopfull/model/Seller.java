package com.opf.shopfull.model;

import com.opf.shopfull.domain.AccountStatus;
import com.opf.shopfull.helper.BankDetails;
import com.opf.shopfull.helper.BusinessDetails;
import com.opf.shopfull.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();

    @Column(name = "gstin")
    private String GSTIN;

    @Column(name = "role")
    private USER_ROLE role = USER_ROLE.ROLE_SELLER;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified = false;

    @Column(name = "account_status")
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
