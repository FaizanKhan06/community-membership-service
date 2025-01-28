package com.capstone.community_membership_service.controller;

import com.capstone.community_membership_service.pojo.CommunityMembershipAddPojo;
import com.capstone.community_membership_service.pojo.CommunityMembershipPojo;
import com.capstone.community_membership_service.pojo.CommunityMembershipWithUserDetailsPojo;
import com.capstone.community_membership_service.service.CommunityMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CommunityMembership")
public class CommunityMembershipController {

    @Autowired
    private CommunityMembershipService communityMembershipService;

    // Add a new membership
    @PostMapping("")
    public ResponseEntity<CommunityMembershipPojo> addMembership(
            @RequestBody CommunityMembershipAddPojo newMembership) {
        CommunityMembershipPojo createdMembership = communityMembershipService.addMembership(newMembership);
        return ResponseEntity.ok(createdMembership);
    }

    // Get memberships by community ID
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<CommunityMembershipWithUserDetailsPojo>> getMembershipsByCommunityIdPending(
            @PathVariable int communityId) {
        List<CommunityMembershipWithUserDetailsPojo> memberships = communityMembershipService
                .getMembershipsByCommunityId(communityId, false);
        return ResponseEntity.ok(memberships);
    }

    @GetMapping("/community/accepted/{communityId}")
    public ResponseEntity<List<CommunityMembershipWithUserDetailsPojo>> getMembershipsByCommunityIdAccepted(
            @PathVariable int communityId) {
        List<CommunityMembershipWithUserDetailsPojo> memberships = communityMembershipService
                .getMembershipsByCommunityId(communityId, true);
        return ResponseEntity.ok(memberships);
    }

    // Get memberships by username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommunityMembershipWithUserDetailsPojo>> getMembershipsByUsername(
            @PathVariable String username) {
        List<CommunityMembershipWithUserDetailsPojo> memberships = communityMembershipService
                .getMembershipsByEmail(username);
        return ResponseEntity.ok(memberships);
    }

    @GetMapping("/community/user")
    public ResponseEntity<List<CommunityMembershipPojo>> getMembershipsByCommunityIdEmail(
            @RequestBody CommunityMembershipAddPojo details) {
        List<CommunityMembershipPojo> memberships = communityMembershipService
                .getMembershipsByCommunityIdEmail(details.getCommunityId(), details.getEmail());
        return ResponseEntity.ok(memberships);
    }

    // Get membership details by ID
    @GetMapping("/{membershipId}")
    public ResponseEntity<CommunityMembershipPojo> getMembershipById(@PathVariable int membershipId) {
        CommunityMembershipPojo membership = communityMembershipService.getMembershipById(membershipId);
        return ResponseEntity.ok(membership);
    }

    @DeleteMapping("/{membershipId}")
    public ResponseEntity<Void> deleteMembershipById(@PathVariable int membershipId) {
        communityMembershipService.deleteMembershipById(membershipId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/accepted")
    public ResponseEntity<CommunityMembershipPojo> setToAccepted(@RequestBody CommunityMembershipAddPojo details) {
        CommunityMembershipPojo membership = communityMembershipService.setToAccepted(details);
        return ResponseEntity.ok(membership);
    }
}
