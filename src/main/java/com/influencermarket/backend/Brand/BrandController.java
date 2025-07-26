package com.influencermarket.backend.controller;

import com.influencermarket.backend.entity.Brand;
import com.influencermarket.backend.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BrandController {
    
    private final BrandService brandService;
    
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Brand>> searchBrandsByName(@RequestParam String name) {
        return ResponseEntity.ok(brandService.searchBrandsByName(name));
    }
    
    @GetMapping("/with-website")
    public ResponseEntity<List<Brand>> getBrandsWithWebsite() {
        return ResponseEntity.ok(brandService.getBrandsWithWebsite());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Brand> getBrandByName(@PathVariable String name) {
        return brandService.getBrandByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<List<Brand>> getBrandsByMobileNumber(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(brandService.getBrandsByMobileNumber(mobileNumber));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable UUID id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        try {
            return ResponseEntity.ok(brandService.createBrand(brand));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable UUID id, @RequestBody Brand brandDetails) {
        try {
            return ResponseEntity.ok(brandService.updateBrand(id, brandDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 