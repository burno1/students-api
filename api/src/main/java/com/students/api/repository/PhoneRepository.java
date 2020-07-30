package com.students.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.students.api.domain.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Integer>{

}
