package com.renato.listrest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.listrest.models.repositories.ListaFreeGeralRepository;

@Service
public class ListaFreeGeralService {
	@Autowired
	ListaFreeGeralRepository listaFreeGeralRepository;
	
}
