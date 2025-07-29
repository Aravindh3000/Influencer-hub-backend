package com.influencermarket.backend.controller;

import com.influencermarket.backend.entity.Brand;
import com.influencermarket.backend.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Brand Management", description = "APIs for managing brands in the influencer marketing platform")
public class BrandController {
    
    private final BrandService brandService;
    
    @GetMapping
    @Operation(summary = "Get all brands", description = "Retrieve a list of all brands in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brands",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class)))
    })
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search brands by name", description = "Search for brands by their name (partial match)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved matching brands",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class)))
    })
    public ResponseEntity<List<Brand>> searchBrandsByName(
            @Parameter(description = "Brand name to search for", required = true) 
            @RequestParam String name) {
        return ResponseEntity.ok(brandService.searchBrandsByName(name));
    }
    
    @GetMapping("/with-website")
    @Operation(summary = "Get brands with website", description = "Retrieve brands that have a website link")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brands with website",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class)))
    })
    public ResponseEntity<List<Brand>> getBrandsWithWebsite() {
        return ResponseEntity.ok(brandService.getBrandsWithWebsite());
    }
    
    @GetMapping("/name/{name}")
    @Operation(summary = "Get brand by name", description = "Retrieve a specific brand by its exact name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brand",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class))),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Brand> getBrandByName(
            @Parameter(description = "Exact brand name", required = true) 
            @PathVariable String name) {
        return brandService.getBrandByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/mobile/{mobileNumber}")
    @Operation(summary = "Get brands by mobile number", description = "Retrieve brands associated with a specific mobile number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brands",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class)))
    })
    public ResponseEntity<List<Brand>> getBrandsByMobileNumber(
            @Parameter(description = "Mobile number to search for", required = true) 
            @PathVariable String mobileNumber) {
        return ResponseEntity.ok(brandService.getBrandsByMobileNumber(mobileNumber));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Retrieve a specific brand by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brand",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class))),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Brand> getBrandById(
            @Parameter(description = "Brand UUID", required = true) 
            @PathVariable UUID id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create a new brand", description = "Create a new brand in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created brand",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class))),
        @ApiResponse(responseCode = "400", description = "Invalid brand data")
    })
    public ResponseEntity<Brand> createBrand(
            @Parameter(description = "Brand object to create", required = true) 
            @RequestBody Brand brand) {
        try {
            return ResponseEntity.ok(brandService.createBrand(brand));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a brand", description = "Update an existing brand by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated brand",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Brand.class))),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Brand> updateBrand(
            @Parameter(description = "Brand UUID", required = true) 
            @PathVariable UUID id,
            @Parameter(description = "Updated brand data", required = true) 
            @RequestBody Brand brandDetails) {
        try {
            return ResponseEntity.ok(brandService.updateBrand(id, brandDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a brand", description = "Delete a brand by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted brand"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Void> deleteBrand(
            @Parameter(description = "Brand UUID", required = true) 
            @PathVariable UUID id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 