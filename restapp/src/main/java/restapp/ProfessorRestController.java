package restapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfessorRestController {
	private List <Professor> professores; 
	
	public ProfessorRestController () {
		professores = new ArrayList <> ();
		professores.add(new Professor(1, "Ana", 555555, "Biologia"));
		professores.add(new Professor(1, "Maria", 666666, "Portugues"));
		professores.add(new Professor(1, "Gabriela", 777777, "Matematica"));
		
	}
	
	@GetMapping("/api/professores")
	public List <Professor> lerTodosOsProfessores () {
		
		return professores;
		
	}
	
	@GetMapping("/api/professores/{id}")
	public Professor lerProfessor (long id) {
		for (Professor p:professores) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	@PostMapping("/api/professores")
	public Professor criarProfessor(Professor p) {
		Professor ultimo = professores.get(professores.size()-1);
		long novoId = ultimo.getId() + 1;
		Professor novo = new Professor (novoId, p.getNome(), p.getMatricula(), p.getArea());
		professores.add(novo);
		return novo;
	}
	
	
	@PutMapping("/api/professores/{id}")
	public Professor alterarProfessor (Professor p) {
		Professor professor = lerProfessor(p.getId());
		if (professor == null) {
			return null;
		}
		professor.setNome(p.getNome());
		professor.setMatricula(p.getMatricula());
		professor.setArea(p.getArea());
		return professor;
		
		
	}
	
	@DeleteMapping (value="/api/professores/{id}", produces = "application/json" )
	public String apagarProfessor (long id) {
		Professor professor = lerProfessor (id);
		boolean result = false;
		if (professor != null) {
			professores.remove(professor);
			result = true;
		}
		
		return "deu certo";
		
		
	}
	
	
}
