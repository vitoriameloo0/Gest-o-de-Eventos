package gestao.evento.api.domain.organizador;

public record DadosDetalhamentoOrganizador(Long id, String nome, String email, String cpf) {
    public DadosDetalhamentoOrganizador(Organizador organizador){
        this(organizador.getId(), organizador.getNome(), organizador.getEmail(), organizador.getCpf());
    }
}
