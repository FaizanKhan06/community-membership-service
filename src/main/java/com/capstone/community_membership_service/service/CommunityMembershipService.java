package com.capstone.community_membership_service.service;

import com.capstone.community_membership_service.entity.CommunityMembershipEntity;
import com.capstone.community_membership_service.exception.ResourceNotFoundException;
import com.capstone.community_membership_service.pojo.CommunityMembershipPojo;
import com.capstone.community_membership_service.repository.CommunityMembershipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityMembershipService {

    @Autowired
    CommunityMembershipRepository communityMembershipRepository;

    private CommunityMembershipPojo convertEntityToPojo(CommunityMembershipEntity entity) {
        CommunityMembershipPojo pojo = new CommunityMembershipPojo();
        BeanUtils.copyProperties(entity, pojo);
        return pojo;
    }
    // Add a new membership
    public CommunityMembershipPojo addMembership(CommunityMembershipEntity newMembership) {
        CommunityMembershipEntity savedEntity = communityMembershipRepository.save(newMembership);
        return convertEntityToPojo(savedEntity);
    }

    // Get memberships by community ID
    public List<CommunityMembershipPojo> getMembershipsByCommunityId(int communityId) {
        List<CommunityMembershipEntity> entities = communityMembershipRepository.findByCommunityId(communityId);
        List<CommunityMembershipPojo> pojos = new ArrayList<>();
        for (CommunityMembershipEntity entity : entities) {
            pojos.add(convertEntityToPojo(entity));
        }
        return pojos;
    }

    // Get memberships by username
    public List<CommunityMembershipPojo> getMembershipsByUsername(String username) {
        List<CommunityMembershipEntity> entities = communityMembershipRepository.findByUsername(username);
        List<CommunityMembershipPojo> pojos = new ArrayList<>();
        for (CommunityMembershipEntity entity : entities) {
            pojos.add(convertEntityToPojo(entity));
        }
        return pojos;
    }
    // Mark loan defaulter
    public CommunityMembershipPojo markLoanDefaulter(int communityMembershipId, Boolean isLoanDefaulter) {
        CommunityMembershipEntity entity = communityMembershipRepository.findById(communityMembershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with ID: " + communityMembershipId));
        entity.setIsLoanDefaulter(isLoanDefaulter);
        CommunityMembershipEntity updatedEntity = communityMembershipRepository.save(entity);
        return convertEntityToPojo(updatedEntity);
    }

    // Remove membership
    public void removeMembership(int communityMembershipId) {
        if (!communityMembershipRepository.existsById(communityMembershipId)) {
            throw new ResourceNotFoundException("Membership not found with ID: " + communityMembershipId);
        }
        communityMembershipRepository.deleteById(communityMembershipId);
    }

    // Get all loan defaulters in a community
    public List<CommunityMembershipPojo> getLoanDefaultersByCommunityId(int communityId) {
        List<CommunityMembershipEntity> entities = communityMembershipRepository.findByCommunityIdAndIsLoanDefaulter(communityId, true);
        List<CommunityMembershipPojo> pojos = new ArrayList<>();
        for (CommunityMembershipEntity entity : entities) {
            pojos.add(convertEntityToPojo(entity));
        }
        return pojos;
    }

    // Get total amount contributed by a community
    public Double getTotalAmountByCommunityId(int communityId) {
        return communityMembershipRepository.getTotalAmountByCommunityId(communityId)
                .orElse(0.0); // Default to 0 if no contributions found
    }

    // Get membership details by ID
    public CommunityMembershipPojo getMembershipById(int communityMembershipId) {
        CommunityMembershipEntity entity = communityMembershipRepository.findById(communityMembershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with ID: " + communityMembershipId));
        return convertEntityToPojo(entity);
    }


}
