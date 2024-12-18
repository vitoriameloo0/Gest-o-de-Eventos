package gestao.evento.api.domain.participante;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoParticipante(
    
    @NotNull
    Long id,
    String nome, 
    String email) {}
