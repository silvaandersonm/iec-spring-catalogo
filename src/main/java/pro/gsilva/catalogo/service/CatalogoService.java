package pro.gsilva.catalogo.service;

import java.util.List;

import pro.gsilva.catalogo.model.Musica;

public interface CatalogoService {
    List<Musica> findAll();
    Musica findById(Long id);
    Musica save(Musica musica);
    void delete(Long id);
    List<Musica> findByTitulo(String titulo);
}
