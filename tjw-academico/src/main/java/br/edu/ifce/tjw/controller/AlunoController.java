package br.edu.ifce.tjw.controller;

import br.edu.ifce.tjw.model.Aluno;
import br.edu.ifce.tjw.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "alunos/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Aluno aluno, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "alunos/form";
        }

        if (aluno.getId() == null && auth != null) {
            aluno.setCriadoPor(auth.getName());
        }

        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + id));

        model.addAttribute("aluno", aluno);
        return "alunos/form";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }
}
