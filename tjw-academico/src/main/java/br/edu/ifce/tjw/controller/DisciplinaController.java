package br.edu.ifce.tjw.controller;

import br.edu.ifce.tjw.model.Disciplina;
import br.edu.ifce.tjw.repository.DisciplinaRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "disciplinas/list";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplinas/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Disciplina disciplina,
                         BindingResult result,
                         Authentication auth) {

        if (result.hasErrors()) {
            return "disciplinas/form";
        }

        if (disciplina.getId() == null && auth != null) {
            disciplina.setCriadoPor(auth.getName());
        }

        disciplinaRepository.save(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada: " + id));
        model.addAttribute("disciplina", disciplina);
        return "disciplinas/form";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        disciplinaRepository.deleteById(id);
        return "redirect:/disciplinas";
    }
}
