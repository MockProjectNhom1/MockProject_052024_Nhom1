package com.realeaste.MockProject_052024.repository;


import com.realeaste.MockProject_052024.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {


}
