package com.renato.listrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListaRestritivaApplication implements CommandLineRunner{
	
    private static final Logger logger = LoggerFactory.getLogger(ListaRestritivaApplication.class);

	public static void main(String[] args) {
        logger.info("Criando o Sistema - Lista Restitiva ....");
		SpringApplication.run(ListaRestritivaApplication.class, args);
        logger. info("Sistema Criado com sucesso!");
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		// System.out.println("Iniciou o Servidor!");
	}

}
