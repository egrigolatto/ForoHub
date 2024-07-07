package forohub.api.controller;

import forohub.api.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> crearTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico) {
        Topico topico = new Topico(datosCrearTopico);
        topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer year) {

        Page<Topico> topicos;
        if (curso != null && year != null) {
            LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0);
            LocalDateTime endOfYear = startOfYear.plusYears(1).minusNanos(1);
            topicos = topicoRepository.findByCursoAndFechaCreacionBetween(Curso.valueOf(curso), startOfYear, endOfYear, paginacion);
        } else if (curso != null) {
            topicos = topicoRepository.findByCurso(Curso.valueOf(curso), paginacion);
        } else if (year != null) {
            LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0);
            LocalDateTime endOfYear = startOfYear.plusYears(1).minusNanos(1);
            topicos = topicoRepository.findByFechaCreacionBetween(startOfYear, endOfYear, paginacion);
        } else {
            topicos = topicoRepository.findByStatusTrue(paginacion);
        }
        Page<DatosListadoTopico> result = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findByIdAndStatusTrue(id);
        return optionalTopico.map(topico -> ResponseEntity.ok(new DatosListadoTopico(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    @PutMapping()
    @Transactional
    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
    }
    */

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(datosActualizarTopico);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }
    */

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
