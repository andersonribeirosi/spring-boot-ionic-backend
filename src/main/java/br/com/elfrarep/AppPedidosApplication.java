package br.com.elfrarep;

import java.text.SimpleDateFormat;
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
import br.com.elfrarep.domain.Pagamento;
import br.com.elfrarep.domain.PagamentoComBoleto;
import br.com.elfrarep.domain.PagamentoComCartao;
import br.com.elfrarep.domain.Pedido;
import br.com.elfrarep.domain.Produto;
import br.com.elfrarep.domain.enums.EstadoPagamento;
import br.com.elfrarep.domain.enums.TipoCliente;
import br.com.elfrarep.repository.CategoriaRepository;
import br.com.elfrarep.repository.CidadeRepository;
import br.com.elfrarep.repository.ClienteRepository;
import br.com.elfrarep.repository.EnderecoRepository;
import br.com.elfrarep.repository.EstadoRepository;
import br.com.elfrarep.repository.PagamentoRepository;
import br.com.elfrarep.repository.PedidoRepository;
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
	
	@Autowired
	private PagamentoRepository pagamentoReposittory;
	
	@Autowired
	private PedidoRepository pedidoRepository;

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 14:32"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);;
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoReposittory.save(Arrays.asList(pgto1, pgto2));
	}

}
