package com.example.demo.service;


import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
import jakarta.el.PropertyNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyService() {
    }

    public Property addProperty(Property property) {
        return (Property)this.propertyRepository.save(property);
    }

    public Page<Property> getProperties(int page, int size) {
        return this.propertyRepository.findAll(PageRequest.of(page, size));
    }

    public List<Property> getAllProperties() {
        return this.propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return this.propertyRepository.findById(id);
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = (Property)this.propertyRepository.findById(id).orElse((Property) null);
        if (property != null) {
            property.setTitle(propertyDetails.getTitle());
            property.setDescription(propertyDetails.getDescription());
            property.setAddress(propertyDetails.getAddress());
            property.setPrice(propertyDetails.getPrice());
            property.setBhks(propertyDetails.getBhks());
            property.setAvailableDate(propertyDetails.getAvailableDate());
//            property.setAmenities(propertyDetails.getAmenities());
            property.setAmenitiesList(propertyDetails.getAmenitiesList());
            return (Property)this.propertyRepository.save(property);
        } else {
            return null;
        }
    }

    public void deleteProperty(Long id) {
        if (this.propertyRepository.existsById(id)) {
            this.propertyRepository.deleteById(id);
        } else {
            throw new PropertyNotFoundException("Property not found with id: " + id);
        }
    }
}

