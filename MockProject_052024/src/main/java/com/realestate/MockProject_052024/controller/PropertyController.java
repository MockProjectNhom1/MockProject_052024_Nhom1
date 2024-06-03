package com.realestate.MockProject_052024.controller;

import com.realestate.MockProject_052024.repository.PropertyRepository;
import com.realestate.MockProject_052024.entities.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PropertyController {
    @Autowired
    PropertyRepository propertyRepository;
    @GetMapping("AddProperty")
    public String addProperty() {
        return "addProperty";
    }
    @PostMapping("Addproperty")
    public String addProperty(@ModelAttribute("property") Property property) {
        propertyRepository.save(property);
        return "AddpropertySuccess";
    }

}
