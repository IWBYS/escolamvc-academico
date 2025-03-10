package br.senai.sp.escolamvc.controller;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/aluno")

public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;
    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if (nome == null) {
            model.addAttribute("alunos", new ArrayList<Aluno>());
            return "/aluno/listagem";
        }

        List<Aluno> alunosPesquisados = alunoRepository.findAlunosByNomeContaining(nome);
        model.addAttribute("alunos", alunosPesquisados);
        return "/aluno/listagem";
    }
    @PostMapping("/salvar")
    public String Salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
        if(aluno.getId() !=null){
            return "aluno/alterar";
        }
        return "aluno/inserir";
        }
        alunoRepository.save(aluno);
        attributes.addFlashAttribute("mensagem","Aluno cadastrado com sucesso!");
        return "redirect:/aluno/novo";
    }
    @GetMapping("/novo")
    public String novo(Model model) {
    model.addAttribute("aluno",new Aluno());
    return "aluno/inserir";
}

    @GetMapping
    public String listar(Model model){
        model.addAttribute("alunos", alunoRepository.findAll());
        return "aluno/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o aluno no banco de dados
        Aluno aluno = alunoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o aluno do banco de dados
        alunoRepository.delete(aluno);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Aluno excluído com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/aluno";
    }

    /*
     * Método que direciona para templates/alunos/alterar.html
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Aluno aluno = alunoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser carregado no formulário
        model.addAttribute("aluno", aluno);

        // Retorna o template aluno/alterar.html
        return "aluno/alterar";
    }

}