package br.senai.sp.escolamvc.controller;

import br.senai.sp.escolamvc.model.Professor;
import br.senai.sp.escolamvc.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/professor")

public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    @PostMapping("/salvar")
    public String Salvar(@Valid Professor professor, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            if(professor.getId() !=null){
                return "professor/alterar";
            }
            return "professor/inserir";
        }
        professorRepository.save(professor);
        attributes.addFlashAttribute("mensagem","Aluno cadastrado com sucesso!");
        return "redirect:/aluno/novo";
    }
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor",new Professor());
        return "professor/inserir";
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("professores", professorRepository.findAll());
        return "professor/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o professor no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o professor do banco de dados
        professorRepository.delete(professor);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Professor excluído com sucesso!");

        // Redireciona para a página de listagem de professores
        return "redirect:/professor";
    }

    /*
     * Método que direciona para templates/professores/alterar.html
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o professor no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o professor no objeto model para ser carregado no formulário
        model.addAttribute("professor", professor);

        // Retorna o template professor/alterar.html
        return "professor/alterar";
    }
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, @Valid Professor professor,
                          BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template professores/alterar.html
        if (result.hasErrors()) {
            return "professor/alterar";
        }

        // Verifica se existe erros personalizados

        // Busca o professor no banco de dados
        Professor professorAtualizado = professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));


        // Seta os dados do professor
        /*professorAtualizado.setName(professor.getName());*/
        professorAtualizado.setEmail(professor.getEmail());
        professorAtualizado.setId(professor.getId());
        professorAtualizado.setCpf(professor.getCpf());


        // Salva o professor no banco de dados
        professorRepository.save(professorAtualizado);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Professor atualizado com sucesso!");

        // Redireciona para a página de listagem de professores
        return "redirect:/professor";
    }

}