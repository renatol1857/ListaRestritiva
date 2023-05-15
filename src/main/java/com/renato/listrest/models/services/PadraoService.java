package com.renato.listrest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.listrest.models.repositories.PadraoRepository;

@Service
public class PadraoService {
	@Autowired
	PadraoRepository padraoGeralRepository;
	
}
