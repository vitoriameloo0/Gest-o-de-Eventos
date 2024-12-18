package gestao.evento.api.domain.organizador;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoOrganizador(

    @NotNull
    Long id,
    String nome, 
    String email) {}
