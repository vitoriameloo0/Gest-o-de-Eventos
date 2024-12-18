package gestao.evento.api.domain.participante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroParticipante(
    
    @NotBlank
    String nome, 
    
    @NotBlank
    @Email
    String email, 

    @NotBlank
    String cpf) {
 
}
