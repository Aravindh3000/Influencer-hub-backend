package com.influencermarket.backend.Brand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @NotBlank(message = "Brand name is required")
    @Size(min = 2, max = 255, message = "Brand name must be between 2 and 255 characters")
    @Column(name = "name", nullable = false, length = 255, unique = true)
    private String name;
    
    @Size(max = 500, message = "Logo URL must not exceed 500 characters")
    @Column(name = "logo_url", length = 500)
    private String logoUrl;
    
    @Size(max = 500, message = "Website link must not exceed 500 characters")
    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$", 
             message = "Invalid website URL format")
    @Column(name = "website_link", length = 500)
    private String websiteLink;
    
    @Column(name = "certificate_urls", columnDefinition = "TEXT")
    private String certificateUrls; // JSON array as string
    
    @Pattern(regexp = "^[+]?[0-9\\s\\-()]{10,20}$", 
             message = "Invalid mobile number format")
    @Column(name = "mobile_number", length = 20)
    private String mobileNumber;
    
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 