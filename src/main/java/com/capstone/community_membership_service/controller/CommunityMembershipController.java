package com.capstone.community_membership_service.controller;

import com.capstone.community_membership_service.entity.CommunityMembershipEntity;
import com.capstone.community_membership_service.pojo.CommunityMembershipPojo;
import com.capstone.community_membership_service.service.CommunityMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommunityMembershipController {

    @Autowired
    private CommunityMembershipService communityMembershipService;

    // Add a new membership
    @PostMapping("/communitymemberships")
    public ResponseEntity<CommunityMembershipPojo> addMembership(@RequestBody CommunityMembershipEntity newMembership) {
        CommunityMembershipPojo createdMembership = communityMembershipService.addMembership(newMembership);
        return ResponseEntity.ok(createdMembership);
    }

    // Get memberships by community ID
    @GetMapping("/communitymemberships/community/{communityId}")
    public ResponseEntity<List<CommunityMembershipPojo>> getMembershipsByCommunityId(@PathVariable int communityId) {
        List<CommunityMembershipPojo> memberships = communityMembershipService.getMembershipsByCommunityId(communityId);
        return ResponseEntity.ok(memberships);
    }

    // Get memberships by username
    @GetMapping("/communitymemberships/user/{username}")
    public ResponseEntity<List<CommunityMembershipPojo>> getMembershipsByUsername(@PathVariable String username) {
        List<CommunityMembershipPojo> memberships = communityMembershipService.getMembershipsByUsername(username);
        return ResponseEntity.ok(memberships);
    }


    // Mark loan defaulter
    @PutMapping("/communitymemberships/{membershipId}/loan-defaulter")
    public ResponseEntity<CommunityMembershipPojo> markLoanDefaulter(
            @PathVariable int membershipId,
            @RequestParam Boolean isLoanDefaulter) {
        CommunityMembershipPojo updatedMembership = communityMembershipService.markLoanDefaulter(membershipId, isLoanDefaulter);
        return ResponseEntity.ok(updatedMembership);
    }

    // Remove membership
    @DeleteMapping("/communitymemberships/{membershipId}")
    public ResponseEntity<Void> removeMembership(@PathVariable int membershipId) {
        communityMembershipService.removeMembership(membershipId);
        return ResponseEntity.noContent().build();
    }

    // Get all loan defaulters in a community
    @GetMapping("/communitymemberships/community/{communityId}/loan-defaulters")
    public ResponseEntity<List<CommunityMembershipPojo>> getLoanDefaultersByCommunityId(@PathVariable int communityId) {
        List<CommunityMembershipPojo> defaulters = communityMembershipService.getLoanDefaultersByCommunityId(communityId);
        return ResponseEntity.ok(defaulters);
    }

    // Get total amount contributed by a community
    @GetMapping("/communitymemberships/community/{communityId}/total-amount")
    public ResponseEntity<Double> getTotalAmountByCommunityId(@PathVariable int communityId) {
        Double totalAmount = communityMembershipService.getTotalAmountByCommunityId(communityId);
        return ResponseEntity.ok(totalAmount);
    }

    // Get membership details by ID
    @GetMapping("/communitymemberships/{membershipId}")
    public ResponseEntity<CommunityMembershipPojo> getMembershipById(@PathVariable int membershipId) {
        CommunityMembershipPojo membership = communityMembershipService.getMembershipById(membershipId);
        return ResponseEntity.ok(membership);
    }
}
