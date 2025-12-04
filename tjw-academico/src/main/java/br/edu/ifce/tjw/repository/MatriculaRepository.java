package br.edu.ifce.tjw.repository;

import br.edu.ifce.tjw.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAlunoIdAndDisciplinaIdAndSituacao(
            Long alunoId,
            Long disciplinaId,
            Matricula.Situacao situacao
    );

    // NOVO: verifica se existe outra matrícula CURSANDO com ID diferente
    boolean existsByAlunoIdAndDisciplinaIdAndSituacaoAndIdNot(
            Long alunoId,
            Long disciplinaId,
            Matricula.Situacao situacao,
            Long id
    );
}
