package com.jdevtreinamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdevtreinamentos.model.UsuarioEntity;
import com.jdevtreinamentos.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@RequestMapping(value = "/mostrarnome/{name}", method =  RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String greetingText(@PathVariable String nome) {
//		return "Curso de Spring Boot API";
//	}
//	
//	@RequestMapping(value = "/olamundo/{nome}", method =  RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String retornaOlaMundo(@PathVariable String nome) {
//		return "Ola Mundo" + nome;
//		
//	}
	
	@GetMapping(value = "listartodos") 
	@ResponseBody
	public ResponseEntity<List<UsuarioEntity>> listarUsuario(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<UsuarioEntity>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscaruserid") 
	@ResponseBody
	public ResponseEntity<UsuarioEntity>buscaruserid(@RequestParam(name = "iduser") Long iduser) {
		
		UsuarioEntity usuario =  usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
	}
	
	//Em postman tem que passar o body ->form date
	//key name e value o nome a ser pesquisado no banco
	@GetMapping(value = "buscarPorNome") 
	@ResponseBody
	public ResponseEntity<List<UsuarioEntity>> buscarPorNome( @RequestParam(name = "name")String name) {
		
		List<UsuarioEntity> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		
		return new ResponseEntity<List<UsuarioEntity>>(usuario, HttpStatus.OK);
	}
	
	@PostMapping(value = "salvar") 
	@ResponseBody
	public ResponseEntity<UsuarioEntity>salvar(@RequestBody UsuarioEntity usuario) {
		
		UsuarioEntity user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<UsuarioEntity>(user, HttpStatus.CREATED);
	}
	
	//Em postman tem que passar o body (form date ou x-www-form-urlencode
	//key iduser e numero de id para deletar e bucaruserid
	@DeleteMapping(value = "delete") 
	@ResponseBody
	public ResponseEntity<String>delete(@RequestParam Long iduser) {
		 usuarioRepository.deleteById(iduser);
		 
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}
	
	@PutMapping(value = "atualizar") 
	@ResponseBody
	public ResponseEntity<?>atualizar(@RequestBody UsuarioEntity usuario) {
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id nao foi informado para atualização.", HttpStatus.OK);
		}
		UsuarioEntity user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<UsuarioEntity>(user, HttpStatus.OK);
	}
	
	
	
	
	

	
	
	

}
