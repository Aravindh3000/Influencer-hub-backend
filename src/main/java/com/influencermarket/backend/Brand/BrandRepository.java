package com.influencermarket.backend.Brand;

import com.influencermarket.backend.Brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    
    Optional<Brand> findByName(String name);
    
    List<Brand> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT b FROM Brand b WHERE b.websiteLink IS NOT NULL")
    List<Brand> findBrandsWithWebsite();
    
    List<Brand> findByMobileNumber(String mobileNumber);
} 