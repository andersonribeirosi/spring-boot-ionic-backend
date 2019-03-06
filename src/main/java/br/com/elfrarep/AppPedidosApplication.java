package br.com.elfrarep;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.elfrarep.domain.Categoria;
import br.com.elfrarep.repository.CategoriaRepository;

@SpringBootApplication
public class AppPedidosApplication implements CommandLineRunner {

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AppPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		
		Categoria cat2 = new Categoria(null, "Eletrônicos");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
	}

}
