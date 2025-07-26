-- Create Brand table
CREATE TABLE brand (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    logo_url VARCHAR(500),
    website_link VARCHAR(500),
    certificate_urls TEXT,
    mobile_number VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on brand name for faster searches
CREATE INDEX idx_brand_name ON brand(name);

-- Create index on mobile number for faster lookups
CREATE INDEX idx_brand_mobile ON brand(mobile_number);

-- Create unique constraint on name to prevent duplicates
ALTER TABLE brand ADD CONSTRAINT uk_brand_name UNIQUE (name);

--rollback drop table brand cascade; 