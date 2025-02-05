package com.example.demo.repository;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package com.example.login.repository;

import com.example.demo.model.Property;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByTitle(String title);

    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Property> findByAddressContaining(String address);

    @Query("SELECT p FROM Property p WHERE p.price > ?1")
    List<Property> findPropertiesByPriceGreaterThan(Double price);

    Page<Property> findAll(Pageable pageable);

    List<Property> findAll(Sort sort);
}
