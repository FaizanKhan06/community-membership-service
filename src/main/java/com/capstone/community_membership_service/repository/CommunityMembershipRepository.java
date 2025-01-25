package com.capstone.community_membership_service.repository;

import com.capstone.community_membership_service.entity.CommunityMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableDiscoveryClient
@Repository
public interface CommunityMembershipRepository extends JpaRepository<CommunityMembershipEntity,Integer> {
    List<CommunityMembershipEntity> findByCommunityId(int communityId);

    List<CommunityMembershipEntity> findByUsername(String username);

    List<CommunityMembershipEntity> findByCommunityIdAndIsLoanDefaulter(int communityId, Boolean isLoanDefaulter);

    @Query("SELECT SUM(c.amount) FROM CommunityMembershipEntity c WHERE c.communityId = :communityId")
    Optional<Double> getTotalAmountByCommunityId(@Param("communityId") int communityId);
}
