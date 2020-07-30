package com.students.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.students.api.domain.Phone;
import com.students.api.repository.PhoneRepository;
import com.students.api.services.exception.ObjectNotFoundException;

@Service
public class PhoneService {

	@Autowired
	private PhoneRepository repo;

	public Phone find(Integer id) {
		Optional<Phone> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Phone.class.getName()));
	}

	public Phone insert(Phone obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Phone update(Phone obj) {
		find(obj.getId());

		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//throw new DataIntegrityException("Não é possível excluir um Phone que possui dependentes");
		}
	}

	public List<Phone> findAll() {
		return (List<Phone>) repo.findAll();
	}
	
	public List<Phone> saveAll(List<Phone> phones){
		return (List<Phone>) repo.saveAll(phones);
	}

}
