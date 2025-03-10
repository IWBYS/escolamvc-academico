package br.senai.sp.escolamvc.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import java.sql.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo",
        length = 1,
        discriminatorType = DiscriminatorType.STRING
)
public class Pessoa {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo deve ser preenchido!")
    private String nome;

    @NotEmpty(message = "O campo deve ser preenchido!")
    @CPF(message = "CPF invalido... ta devendo é?")
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    //private Object telefone;

    @NotEmpty(message = "O campo deve ser preenchido!")
    @Email(message = "Email invalido!")
    private String email;

    //private String foto;

    private String etinia;

    private String sexo;

    private String estadoCivil;

    //private String senha;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;

    @CreationTimestamp(source = SourceType.DB)
    private Date dataCadastro;

    @UpdateTimestamp(source = SourceType.DB)
    private Date dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco enderecoCidade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtinia() {
        return etinia;
    }

    public void setEtinia(String etinia) {
        this.etinia = etinia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}


