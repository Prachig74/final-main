//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controller;

import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;
import com.example.demo.util.JwtUtil;
import jakarta.el.PropertyNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/properties"})
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private JwtUtil jwtUtil;

    public PropertyController() {
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ") ? header.substring(7) : null;
    }

    @GetMapping
    public Page<Property> getProperties(@RequestParam int page, @RequestParam int size) {
        return this.propertyService.getProperties(page, size);
    }

    @PostMapping({"/add"})
    public ResponseEntity<Property> addProperty(@RequestBody Property property, HttpServletRequest request) {
        String token = this.getTokenFromRequest(request);
        if (token != null && this.jwtUtil.validateToken(token)) {
            Property newProperty = this.propertyService.addProperty(property);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProperty);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((Property) null);
        }
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = this.propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return (ResponseEntity)this.propertyService.getPropertyById(id).map((property) -> ResponseEntity.ok(property)).orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        this.propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails, HttpServletRequest request) {
        String token = this.getTokenFromRequest(request);
        if (token != null && this.jwtUtil.validateToken(token)) {
            Property updatedProperty = this.propertyService.updateProperty(id, propertyDetails);
            return updatedProperty == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found") : ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
