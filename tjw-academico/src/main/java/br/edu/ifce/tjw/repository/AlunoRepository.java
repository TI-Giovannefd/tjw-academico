package br.edu.ifce.tjw.repository;

import br.edu.ifce.tjw.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
