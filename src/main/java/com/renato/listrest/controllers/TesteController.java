package com.renato.listrest.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.IPLiberados;
import com.renato.listrest.models.repositories.IPLiberadosRepository;

@RestController
@RequestMapping(path = "/teste")
public class TesteController {
	@Autowired
	IPLiberadosRepository repo;
	
	@GetMapping("/dniservice")
	public String dniService() {
		return "dniService";
	}
	
	@GetMapping("/generic-exception")
	public ResponseEntity<Void> test4() {
	    throw new RuntimeException("Generic Exception");
	}
	
    @GetMapping("/custom-error-exception")
    public ResponseEntity<Void> test3() {
        //throw new CustomErrorException( 2,HttpStatus.BAD_REQUEST, "Parameters not passed" );
    	throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Parameters not passed" );
    }	

    @PostMapping("/cargaip")
	public String cargaDeIPs() throws IOException {
		String nomeArq = "C:\\projetos\\sts\\ListaRestritiva\\doc\\ip.txt";

		BufferedReader buffRead = new BufferedReader(new FileReader(nomeArq));
		String linha = "";
		while (true) {
			if (linha != null)
				repo.save(new IPLiberados(linha.trim(), ""));
			else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		return "Sucesso";
    }

    
}
