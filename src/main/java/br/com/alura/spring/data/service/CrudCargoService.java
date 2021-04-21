package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system = true;
	
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação de cargo você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Listar");
			
			int acao = scanner.nextInt();
			switch (acao) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				excluir(scanner);
				break;
			case 4:
				listar();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descricao do cargo");
		String descrição = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descrição);
		cargoRepository.save(cargo);
		System.out.println("Salvo com sucesso!");
		System.out.println(descrição);
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id do cargo");
		Integer id = scanner.nextInt();
		System.out.println("Descricao do cargo");
		String descrição = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descrição);
		cargoRepository.save(cargo);
		System.out.println("Salvo com sucesso!");
	}
	
	private void listar() {
		Iterable<Cargo> cargos =  cargoRepository.findAll();
		    cargos.forEach(cargo -> System.out.println(cargo));
		
	}
	
	private void excluir(Scanner scanner) {
		System.out.println("Id do cargo");
		Integer id = scanner.nextInt();
		cargoRepository.deleteById(id);
	}
}
