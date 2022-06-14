package com.example.demo.webservice.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.webservice.DTO.CursoDTO;
import com.example.demo.webservice.model.CargaHoraria;
import com.example.demo.webservice.model.Curso;
import com.example.demo.webservice.repository.ICursosRepository;

@Controller
public class CursosController {
	
	@Autowired // Faz a injeção de dependencias de forma automática, todas as vezes que cursoRepo for chamado;
	ICursosRepository cursoRepo;
	
	/*
	 * Método inicial da página, retornando todos os valores contidos no BD em uma página Html chamada index
	 */
	@GetMapping("/cursos")
	public ModelAndView index() {
		List<Curso> cursos = cursoRepo.findAll();
		
		ModelAndView mv = new ModelAndView("cursos/index");
		
		mv.addObject("cursos",cursos);
		return mv;
	}
	
	/*
	 * Método que redireciona o user à pagina de cadastro (new.html)
	 */
	
	@GetMapping("/cursos/new")
	public ModelAndView newCourse(CursoDTO requisicao) {
		ModelAndView mv = new ModelAndView("cursos/new");
		
		/*
		 * Adiciona um objeto "cargaHoraria" e envia os dados para a pagina HTML, esses serão utilizados na renderização pelo Thymeleaf
		 */
		mv.addObject("cargaHoraria",CargaHoraria.values());
		return mv;
	}
	
	/* ADICIONAR CURSOS
	 * Faz a requisição POST. Com a dependencia 'Validation' - @Valid é possivel receber um objeto 'bidingResult' onde é feita a verificação
	 * da existência de erros (campos preenchidos incorretamente) , caso encontre algum, a página sera 'redirecionada' novamente 
	 * para a pagina de criação de curso. Caso esteja sem erros um objeto 'curso' do tipo curso recebe os dados contidos no objeto
	 * de tipo 'CursoDTO', por meio do método 'toCurso()' e o usuário é redirecionado à pagina inicial, onde os novos dados serão renderizados
	 */
	
	@PostMapping("/cursos")
	public ModelAndView addNewCourse(@Valid CursoDTO requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Curso curso = requisicao.toCurso();
			System.out.println("ERROR\n"+curso);
			
			ModelAndView mv = new ModelAndView("/cursos/new");
			mv.addObject("cargaHoraria",CargaHoraria.values());
			return mv;
		}
		
		Curso curso = requisicao.toCurso();
		
		this.cursoRepo.save(curso); 
		System.out.println("PASSOU\n"+curso);
		
		return new ModelAndView("redirect:/cursos");
	}
	
	
	
	/* HABILITANDO EDIÇÃO DOS CURSOS
	 * Faz a requisição GET. Uma lista de objetos opcionais é criada, recebendo o curso de Id correspondente ao passado na URI pelo usuário.
	 * Uma verificação é feita para ver se o objeto está vazio, caso esteja significa que o ID passado não foi encontrado, logo o user é redi
	 * recionado para a página inicial. Caso tenha sido encontrado o id, um objeto cursos é criado, recebendo os dados, depois o método fromCurso
	 * é chamado passado o objeto curso, setando os novos dados no objeto de id correspondente. E a página edit.html é renderizada
	 *
	 */
	@GetMapping("/cursos/{id}/edit")
	public ModelAndView editCourse(@PathVariable Long id, CursoDTO requicisao) {
			
		Optional<Curso> cursos = cursoRepo.findById(id);
		
		if(cursos.isEmpty()) {
			System.out.println("NAO ENCONTROU O ID:"+ id);
			return new ModelAndView("redirect:/cursos");
		}else {
			Curso curso = cursos.get();
			requicisao.fromCurso(curso);
			
			
			ModelAndView mv = new ModelAndView("cursos/edit");
			mv.addObject("cursoId",curso.getId());
			mv.addObject("cargaHoraria",CargaHoraria.values());
			return mv;
		}
	}
	
	
	/*EDITANDO OS DADOS DO CURSO
	 * Faz a requisição POST. Na página de edição é feita a verificação se os dados foram preenchidos corretamente, caso não, o user será
	 * 'redirecionado' para a mesma tela de edição. Caso os dados estejam corretos, é feito um processo parecido com o de adicionar um novo
	 * curso, diferenciado apenas, pelo fato de que o Id será preservado, evitando assim que um novo Curso seja criado
	 */
	@PostMapping("/cursos/{id}")
	public ModelAndView updateCourse(@PathVariable Long id, @Valid CursoDTO requisicao, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			Curso curso = requisicao.toCurso();
			System.out.println("ERROR\n"+curso);
			
			ModelAndView mv = new ModelAndView("redirect:/cursos/{id}/edit");
			mv.addObject("cargaHoraria",CargaHoraria.values());
			return mv;
		}else {
			Optional<Curso> cursoOptional = cursoRepo.findById(id);
			
			if(cursoOptional.isEmpty()) {
				System.out.println("NAO ENCONTROU O ID:"+ id);
				return new ModelAndView("redirect:/cursos");
			}else {
				Curso curso = requisicao.toCurso(cursoOptional.get());
				
				cursoRepo.save(curso);
				System.out.println("CURSO ATUALIZADO\n"+curso);
				
				return new ModelAndView("redirect:/cursos");
			}
		}
		
		
	}
	
	
	/*Apaga o curso
	 * Método Get. Chama a url passando o ID, é feita a injeção de dependencias do objeto cursoRepo e utilizamos o metodo 
	 * deleteById passando o Id desejado. Por fim, o user é 'redirecionado' para a tela inicial.
	 */
	@GetMapping("/cursos/{id}/delete")
	public String deleteCourse(@PathVariable Long id, CursoDTO curso) {
		cursoRepo.deleteById(id);
		return "redirect:/cursos";
	}
	
	
	

	
}
