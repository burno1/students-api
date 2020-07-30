package com.students.api.resources;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.students.api.domain.Phone;
import com.students.api.domain.Student;
import com.students.api.services.PhoneService;
import com.students.api.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

	@Autowired
	private StudentService service;

	@Autowired
	private PhoneService phoneService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> find(@PathVariable Integer id) {
		Student obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody Student obj){
		String regex = "\\(?\\d{2,}\\)?[ -]?\\d{4,5}[\\-\\s]?\\d{4}";
		Pattern patternPhone = Pattern.compile(regex);
		List<Phone> phones = obj.getTelefones();
		
		for (Phone phone : phones) {
			 Matcher mat = patternPhone.matcher (phone.getTelefone());
			if(!mat.matches()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		
		
		obj.setTelefones(phoneService.saveAll(phones));
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody Student obj,@PathVariable Integer id){
		obj.setId(id);
		List<Phone> phones = obj.getTelefones();
		if(obj.getTelefones() != null) {
			String regex = "\\(?\\d{2,}\\)?[ -]?\\d{4,5}[\\-\\s]?\\d{4}";
			Pattern patternPhone = Pattern.compile(regex);
			for (Phone phone : phones) {
				 Matcher mat = patternPhone.matcher (phone.getTelefone());
				if(!mat.matches()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
			}
		}
		obj.setTelefones(phoneService.saveAll(phones));
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Student>> findAll() {
		List<Student> list = service.findAll();
		  
		return ResponseEntity.ok().body(list);
	}
}
