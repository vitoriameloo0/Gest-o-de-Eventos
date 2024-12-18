package gestao.evento.api.domain.organizador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "organizadores")
@Entity(name = "Organizador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Organizador {
   
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    
    private String cpf;

    private Boolean ativo;

    public Organizador(DadosCadastroOrganizador dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
    }

    public void atualizarInformacoes(DadosAtualizacaoOrganizador dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.email() != null){
            this.email = dados.email();    
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
