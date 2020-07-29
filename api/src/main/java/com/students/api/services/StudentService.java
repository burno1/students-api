package com.students.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.students.api.domain.Student;
import com.students.api.repository.StudentsRepository;
import com.students.api.services.exception.ObjectNotFoundException;

@Service
public class StudentService {
	@Autowired
	private StudentsRepository repo;

	public Student find(Integer id) {
		Optional<Student> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Student.class.getName()));
	}

	public Student insert(Student obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Student update(Student obj) {
		find(obj.getId());

		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//throw new DataIntegrityException("Não é possível excluir um Student que possui dependentes");
		}
	}

	public List<Student> findAll() {
		return (List<Student>) repo.findAll();
	}
	/*
	 * public Student fromDTO(StudentDTO objDto) { return new (objDto.getId(),
	 * objDto.getNome(), objDto.getDataNasc(), objDto.getEmail()); }
	 */
}
