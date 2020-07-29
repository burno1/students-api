/**
 * 
 */
package com.students.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.students.api.domain.Student;

/**
 * @author Bruno Fernandes
 *
 */
@Repository
public interface StudentsRepository extends CrudRepository<Student, Integer>{

}
