package co.com.intergrupo.maven.boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.intergrupo.maven.boot.model.Persona;
import co.com.intergrupo.maven.boot.service.ExampleService;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {

	@Override
	public List<Persona> getListaPersonas() {
		List<Persona> listaDePersonas = new ArrayList<>();
		Persona persona = new Persona("Jose Vicente",42);
		Persona persona2 = new Persona("Martha Cristina",44);
		Persona persona3 = new Persona("Isabel",8);
		listaDePersonas.add(persona);
		listaDePersonas.add(persona2);
		listaDePersonas.add(persona3);
		
		return listaDePersonas;
	}

}
