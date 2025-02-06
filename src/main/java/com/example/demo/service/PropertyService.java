package com.example.demo.service;


import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
import jakarta.el.PropertyNotFoundException;
import java.util.List;
import java.util.Optional;


import org.springframework.cache.annotation.Cacheable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
//import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyService() {
    }

//    public Property addProperty(Property property) {
//        return (Property)this.propertyRepository.save(property);
//    }
//
//    public Page<Property> getProperties(int page, int size) {
//        return this.propertyRepository.findAll(PageRequest.of(page, size));
//    }

//    public List<Property> getAllProperties() {
//        return this.propertyRepository.findAll();
//    }




//    public Optional<Property> getPropertyById(Long id) {
//        return this.propertyRepository.findById(id);
//    }




//    public Property updateProperty(Long id, Property propertyDetails) {
//        Property property = (Property)this.propertyRepository.findById(id).orElse((Property) null);
//        if (property != null) {
//            property.setTitle(propertyDetails.getTitle());
//            property.setDescription(propertyDetails.getDescription());
//            property.setAddress(propertyDetails.getAddress());
//            property.setPrice(propertyDetails.getPrice());
//            property.setBhks(propertyDetails.getBhks());
//            property.setAvailableDate(propertyDetails.getAvailableDate());
    ////            property.setAmenities(propertyDetails.getAmenities());
//            property.setAmenitiesList(propertyDetails.getAmenitiesList());
//            return (Property)this.propertyRepository.save(property);
//        } else {
//            return null;
//        }
//    }
//
//    public void deleteProperty(Long id) {
//        if (this.propertyRepository.existsById(id)) {
//            this.propertyRepository.deleteById(id);
//        } else {
//            throw new PropertyNotFoundException("Property not found with id: " + id);
//        }
//    }




    @Cacheable(value = "paginatedProperties", key = "#page + '-' + #size")
    public Page<Property> getProperties(int page, int size) {
        System.out.println("Fetching paginated properties from DB...");
        return propertyRepository.findAll(PageRequest.of(page, size));
    }



    // ✅ Cache property details by ID (Avoid duplicate DB queries)
    @Cacheable(value = "properties", key = "#id")
    public Optional<Property> getPropertyById(Long id) {
        System.out.println("Fetching property from DB for ID: " + id);
        return propertyRepository.findById(id);
    }

    // ✅ Cache all properties (Useful for list-based retrieval)
    @Cacheable(value = "allProperties")
    public List<Property> getAllProperties() {
        System.out.println("Fetching all properties from DB...");
        return propertyRepository.findAll();
    }

    // ✅ Remove cache when adding a new property (because the list updates)
//        @CacheEvict(value = "allProperties", allEntries = true)
//
//

//    @Cacheable(value = "paginatedProperties", key = "#page + '-' + #size")
//    public Page<Property> getProperties(int page, int size) {
//        logger.info("Fetching paginated properties from DB...");
//        return propertyRepository.findAll(PageRequest.of(page, size));
//}

@Caching(evict = {
        @CacheEvict(value = "allProperties", allEntries = true), // Remove all properties cache
        @CacheEvict(value = "paginatedProperties", allEntries = true) // Remove paginated data cache
})
public Property addProperty(Property property) {
    return propertyRepository.save(property);
}

// ✅ Remove cache when updating a property
@Caching(evict = {
        @CacheEvict(value = "properties", key = "#id"),
        @CacheEvict(value = "allProperties", allEntries = true),
        @CacheEvict(value = "paginatedProperties", allEntries = true) // Clear paginated cache
})
public Property updateProperty(Long id, Property propertyDetails) {
    Property property = propertyRepository.findById(id).orElse(null);
    if (property != null) {
        property.setTitle(propertyDetails.getTitle());
        property.setDescription(propertyDetails.getDescription());
        property.setAddress(propertyDetails.getAddress());
        property.setPrice(propertyDetails.getPrice());
        property.setBhks(propertyDetails.getBhks());
        property.setAvailableDate(propertyDetails.getAvailableDate());
        property.setAmenitiesList(propertyDetails.getAmenitiesList());
        return propertyRepository.save(property);
    }
    return null;
}

@Caching(evict = {
        @CacheEvict(value = "properties", key = "#id"),
        @CacheEvict(value = "allProperties", allEntries = true),
        @CacheEvict(value = "paginatedProperties", allEntries = true) // Clear paginated cache
})
public void deleteProperty(Long id) {
    if (propertyRepository.existsById(id)) {
        propertyRepository.deleteById(id);
    } else {
        throw new RuntimeException("Property not found with id: " + id);
    }
}
    }



