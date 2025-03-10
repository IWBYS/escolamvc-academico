package br.senai.sp.escolamvc.repository;

import br.senai.sp.escolamvc.model.Professor;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findProfessoresByNomeContaining(@NotEmpty(message = "O campo deve ser preenchido!") String nome);

    Professor findProfessorByCpf(String cpf);
}
