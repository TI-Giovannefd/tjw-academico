package br.edu.ifce.tjw.controller;

import br.edu.ifce.tjw.model.Aluno;
import br.edu.ifce.tjw.model.Disciplina;
import br.edu.ifce.tjw.model.Matricula;
import br.edu.ifce.tjw.model.Matricula.Situacao;
import br.edu.ifce.tjw.repository.AlunoRepository;
import br.edu.ifce.tjw.repository.DisciplinaRepository;
import br.edu.ifce.tjw.repository.MatriculaRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaController(MatriculaRepository matriculaRepository,
                               AlunoRepository alunoRepository,
                               DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("matriculas", matriculaRepository.findAll());
        return "matriculas/list";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        Matricula matricula = new Matricula();
        carregarListas(model);
        model.addAttribute("matricula", matricula);
        return "matriculas/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Matricula matricula,
                         BindingResult result,
                         Authentication auth,
                         Model model) {

        if (result.hasErrors()) {
            carregarListas(model);
            return "matriculas/form";
        }

        // >>> BLOCO NOVO DE VALIDAÇÃO <<<
        if (matricula.getSituacao() == Situacao.CURSANDO) {
            Long alunoId = matricula.getAluno().getId();
            Long disciplinaId = matricula.getDisciplina().getId();

            boolean existeOutroCursando;

            if (matricula.getId() == null) {
                existeOutroCursando = matriculaRepository
                        .existsByAlunoIdAndDisciplinaIdAndSituacao(
                                alunoId, disciplinaId, Situacao.CURSANDO);
            } else {
                existeOutroCursando = matriculaRepository
                        .existsByAlunoIdAndDisciplinaIdAndSituacaoAndIdNot(
                                alunoId, disciplinaId, Situacao.CURSANDO, matricula.getId());
            }

            if (existeOutroCursando) {
                result.reject("duplicada",
                        "Este aluno já está CURSANDO esta disciplina.");
                carregarListas(model);
                return "matriculas/form";
            }
        }
        // >>> FIM DA VALIDAÇÃO <<<

        if (matricula.getId() == null && auth != null) {
            matricula.setCriadoPor(auth.getName());
        }

        matriculaRepository.save(matricula);
        return "redirect:/matriculas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada: " + id));

        carregarListas(model);
        model.addAttribute("matricula", matricula);
        return "matriculas/form";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
        return "redirect:/matriculas";
    }

    private void carregarListas(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
    }
}
