package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;

	private final FuncionarioRepository funcionarioRepository; 
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual relatório você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario por nome");
			System.out.println("2 - Busca funcionario por nome, maior salário e data de contratação");
			System.out.println("3 - Busca funcionario por data de contratação");
			System.out.println("4 - Lista funcionarios com salário");

			int acao = scanner.nextInt();
			switch (acao) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataMaior(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(System.out::println);
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		System.out.println("Qual salario deseja pesquisar?");
		BigDecimal salario = scanner.nextBigDecimal();
		System.out.println("Qual data deseja pesquisar?");
		String data = scanner.next();
		LocalDate localdate = LocalDate.parse(data,formatter);
		
		List<Funcionario> lista = funcionarioRepository.findNomeSalarioMaiorDataComtratacao(nome,salario,localdate);
		lista.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataMaior(Scanner scanner) {
		System.out.println("Qual data deseja pesquisar?");
		String data = scanner.next();
		LocalDate localdate = LocalDate.parse(data,formatter);
		
		List<Funcionario> lista = funcionarioRepository.findDataComtratacaoMaior(localdate);
		lista.forEach(System.out::println);
	}

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
		lista.forEach(f -> System.out.println("Funcionario: " + f.getNome() + " - Salario: " + f.getSalario()));
	}
	
}
