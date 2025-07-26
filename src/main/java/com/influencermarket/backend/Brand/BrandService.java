package com.influencermarket.backend.service;

import com.influencermarket.backend.entity.Brand;
import com.influencermarket.backend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
    
    private final BrandRepository brandRepository;
    
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
    
    public Optional<Brand> getBrandById(UUID id) {
        return brandRepository.findById(id);
    }
    
    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }
    
    public List<Brand> searchBrandsByName(String name) {
        return brandRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Brand> getBrandsWithWebsite() {
        return brandRepository.findBrandsWithWebsite();
    }
    
    public List<Brand> getBrandsByMobileNumber(String mobileNumber) {
        return brandRepository.findByMobileNumber(mobileNumber);
    }
    
    public Brand createBrand(Brand brand) {
        // Check if brand name already exists
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new RuntimeException("Brand with name " + brand.getName() + " already exists");
        }
        return brandRepository.save(brand);
    }
    
    public Brand updateBrand(UUID id, Brand brandDetails) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        // Update fields
        brand.setName(brandDetails.getName());
        brand.setLogoUrl(brandDetails.getLogoUrl());
        brand.setWebsiteLink(brandDetails.getWebsiteLink());
        brand.setCertificateUrls(brandDetails.getCertificateUrls());
        brand.setMobileNumber(brandDetails.getMobileNumber());
        brand.setAddress(brandDetails.getAddress());
        
        return brandRepository.save(brand);
    }
    
    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        brandRepository.delete(brand);
    }
} 