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

import gestao.evento.api.domain.participante.DadosAtualizacaoParticipante;
import gestao.evento.api.domain.participante.DadosCadastroParticipante;
import gestao.evento.api.domain.participante.DadosDetalhamentoParticipante;
import gestao.evento.api.domain.participante.Participante;
import gestao.evento.api.domain.participante.ParticipanteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroParticipante dados, UriComponentsBuilder uriBuilder){
        var participante = new Participante(dados);
        repository.save(participante);

        var uri = uriBuilder.path("/participantes/{id}").buildAndExpand(participante.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoParticipante(participante));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoParticipante dados){
        var participante = repository.getReferenceById(dados.id());
        participante.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoParticipante(participante));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        var participante = repository.getReferenceById(id);
        participante.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
        var participante = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoParticipante(participante));
    }
    
} 