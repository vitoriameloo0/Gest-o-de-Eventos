package gestao.evento.api.domain.participante;

// metodo para retornar apos a requisição ser feita
public record DadosDetalhamentoParticipante(Long id, String nome, String email, String cpf) {

    public DadosDetalhamentoParticipante (Participante participante){
        this(participante.getId(), participante.getNome(), participante.getEmail(), participante.getCpf());
    }
}
