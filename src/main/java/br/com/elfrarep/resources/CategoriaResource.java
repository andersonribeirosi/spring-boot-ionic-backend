package br.com.elfrarep.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.elfrarep.domain.Categoria;
import br.com.elfrarep.service.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResource {
	
	@Autowired
	public CategoriaService service;
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	//@RequestMapping(value="/{id}", method = RequestMethod.GET)
	//public ResponseEntity<?> find(@PathVariable Integer id){
		//Categoria obj = service.buscar(id);
		//return ResponseEntity.ok().body(obj);
	//}
	
	//@CrossOrigin
	//@RequestMapping(value="/{id}", method = RequestMethod.GET)
	//public ResponseEntity<?> find(@PathVariable Integer id){
		//List<Categoria> list = service.buscar(id);
		//List<CategoriaDTO> listDto = list.stream().
		//return ResponseEntity.ok().body(obj);
		//Categoria obj = service.buscar(id);
		//return ResponseEntity.ok().body(obj);
	
	}


