package gestao.evento.api.domain.organizador;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroOrganizador (
 
    @NotBlank
    String nome, 
    
    @NotBlank
    @Email
    String email, 

    @NotBlank
    String cpf) {
    
}
