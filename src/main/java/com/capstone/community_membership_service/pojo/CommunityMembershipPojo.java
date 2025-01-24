package com.capstone.community_membership_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityMembershipPojo {
    private int communityMembershipId;
    private int communityId;
    private String username;
    private double amount;
    private boolean isLoanDefaulter;

    public boolean isLoanDefaulter() {
        return isLoanDefaulter;
    }

    public void setLoanDefaulter(boolean loanDefaulter) {
        isLoanDefaulter = loanDefaulter;
    }
}
