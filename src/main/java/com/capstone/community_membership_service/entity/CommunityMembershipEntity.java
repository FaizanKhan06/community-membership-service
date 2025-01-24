package com.capstone.community_membership_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="community_membership_entity")
public class CommunityMembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="community_membership_id")
    private int communityMembershipId;
    @Column(name="community_id")
    private int communityId;
    @Column(name="username")
    private String username;
    @Column(name="amount")
    private double amount;
    @Column(name="is_loan_defaulter")
    private boolean isLoanDefaulter;

    public boolean isLoanDefaulter() {
        return isLoanDefaulter;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIsLoanDefaulter(boolean loanDefaulter) {
        isLoanDefaulter = loanDefaulter;
    }
}
