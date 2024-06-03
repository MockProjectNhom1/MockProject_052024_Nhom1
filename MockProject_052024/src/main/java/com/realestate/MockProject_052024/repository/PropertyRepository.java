package com.realestate.MockProject_052024.repository;

import com.realestate.MockProject_052024.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
