package br.senai.sp.escolamvc.model;

import jakarta.persistence.Entity;
/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
*/
@Entity
public class Professor extends Pessoa{
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF(message = "CPF inválido")
    private String cpf;

    @NotEmpty(message = "O campo é obrigatorio")
    private String name;


    @NotEmpty(message = "O campo é obrigatorio")
    @Email(message = "Email inválido!")
    private String email;


    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/


}
