package com.example.demo.service;
import com.example.demo.dto.employeedto;
import com.example.demo.entities.employeeentity;
import com.example.demo.repository.Employeerepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Employeeservice {
    @Autowired
    private Employeerepository employeerepository;

    // Your other code
    private final ModelMapper modalMapper;

    public Optional<employeedto> getEmployeeById(Long id) {
        return employeerepository.findById(id).map(employeeentity -> modalMapper.map(employeeentity,employeedto.class));
    }


    public List<employeedto> getAllEmployees() {
        List<employeeentity> employeeEntities = employeerepository.findAll();

        return employeeEntities.stream()
                .map(employeeEntity -> modalMapper.map(employeeEntity, employeedto.class))
                .collect(Collectors.toList());
    }

    public employeedto createNewEmployee(employeedto inputemployee) {
        employeeentity tosaveentity = modalMapper.map(inputemployee, employeeentity.class);
        employeeentity savedEmployeeEntity = employeerepository.save(tosaveentity);
        return modalMapper.map(savedEmployeeEntity, employeedto.class);
    }

    public employeedto updateemployeeid(Long employeeid, employeedto employeedto) {
        employeeentity Employeeentity = modalMapper.map(employeedto, employeeentity.class);
        Employeeentity.setId(employeeid);
        employeeentity saveEmployeeEntity = employeerepository.save(Employeeentity);
        return modalMapper.map(saveEmployeeEntity, com.example.demo.dto.employeedto.class);
    }
    public boolean isExistsByEmployeeid(Long employeeid){
    return employeerepository.existsById(employeeid);
    }

    public boolean Deleteemployeebyid(Long employeeid) {
        boolean exist = employeerepository.existsById(employeeid);
        if (!exist) {
            return false;
        }
        employeerepository.deleteById(employeeid);
        return true;
    }
    public employeedto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = employeerepository.existsById(employeeId);
        if (!exists) {
            return null;
        }

        employeeentity employeeEntity = employeerepository.findById(employeeId).orElseThrow();

        updates.forEach((fieldName, value) -> {
            Field fieldToUpdate = ReflectionUtils.findField(employeeentity.class, fieldName);
            if (fieldToUpdate != null) {
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
            }
        });

        employeeentity updatedEntity = employeerepository.save(employeeEntity);
        return modalMapper.map( employeerepository.save(updatedEntity),employeedto.class);
    }
}



















