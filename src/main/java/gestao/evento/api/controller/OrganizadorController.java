package gestao.evento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gestao.evento.api.domain.organizador.DadosAtualizacaoOrganizador;
import gestao.evento.api.domain.organizador.DadosCadastroOrganizador;
import gestao.evento.api.domain.organizador.DadosDetalhamentoOrganizador;
import gestao.evento.api.domain.organizador.Organizador;
import gestao.evento.api.domain.organizador.OrganizadorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorRepository repository;
    
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroOrganizador dados, UriComponentsBuilder uriBuilder){
        var organizador = new Organizador(dados);
        repository.save(organizador);

        var uri = uriBuilder.path("organizadores/{id}").buildAndExpand(organizador.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoOrganizador(organizador));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoOrganizador dados){
        var organizador = repository.getReferenceById(dados.id());
        organizador.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoOrganizador(organizador));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        var organizador = repository.getReferenceById(id);
        organizador.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
        var organizador = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoOrganizador(organizador));
    }
}
