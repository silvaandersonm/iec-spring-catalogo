package pro.gsilva.catalogo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.gsilva.catalogo.model.Musica;
import pro.gsilva.catalogo.repository.CatalogoRepository;
import pro.gsilva.catalogo.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired 
    private CatalogoRepository catalogoRepository;

    @Override
    public List<Musica> findAll() {       
        return catalogoRepository.findAll();
    }

    @Override
    public Musica findById(Long id) {        
        return catalogoRepository.findById(id).get();
    }

    @Override
    public Musica save(Musica musica) {        
        return catalogoRepository.save(musica);
    }

    @Override
    public void delete(Long id) {
        catalogoRepository.deleteById(id);
    }

    @Override
    public List<Musica> findByTitulo(String titulo) {
        return catalogoRepository.findAllByTituloIsLike(titulo + "%");
    }

    @Override
    public List<Musica> findByTituloAndCategoria(String titulo, Long idCategoria) {
        String tituloLike = null;
        if (titulo != null) {
            tituloLike = titulo + "%";
        }
        return catalogoRepository.findAllByTituloLikeAndCategoria(tituloLike, idCategoria);
    }

}