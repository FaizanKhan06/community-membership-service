package com.capstone.community_membership_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityMembershipUpdateAmountPojo {

    private int communityId;
    private String email;
    private double amount;

}
