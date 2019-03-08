package br.com.elfrarep;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.elfrarep.domain.Categoria;
import br.com.elfrarep.domain.Cidade;
import br.com.elfrarep.domain.Cliente;
import br.com.elfrarep.domain.Endereco;
import br.com.elfrarep.domain.Estado;
import br.com.elfrarep.domain.Produto;
import br.com.elfrarep.domain.enums.TipoCliente;
import br.com.elfrarep.repository.CategoriaRepository;
import br.com.elfrarep.repository.CidadeRepository;
import br.com.elfrarep.repository.ClienteRepository;
import br.com.elfrarep.repository.EnderecoRepository;
import br.com.elfrarep.repository.EstadoRepository;
import br.com.elfrarep.repository.ProdutoRepository;

@SpringBootApplication
public class AppPedidosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Impressora", 2000.00);
		Produto p2 = new Produto(null, "Computador", 4000.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "Campinas", est2);
		Cidade cid3 = new Cidade(null, "São Paulo", est2);

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));

		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));

		Cliente cli1 = new Cliente(null, "anderson@gmail.com", "Anderson Ribeiro", "043.171.844-00",
				TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("3321-0250", "99158-0658"));

		Endereco e1 = new Endereco(null, "Rua Iraildo Gomes de Abreu", "45", "Nova Brasilia", "Santo Antonio",
				"58.406-830", cli1, cid1);
		
		Endereco e2 = new Endereco(null, "Rua Olga de Azevedo", "55", "Nova Brasilia", "Santo Antonio",
				"58.406-830", cli1, cid2);

		est1.getCidades().addAll(Arrays.asList(cid1));

		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
	}

}
