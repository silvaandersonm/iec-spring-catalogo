package pro.gsilva.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pro.gsilva.catalogo.model.Categoria;
import pro.gsilva.catalogo.service.CategoriaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getListaCategorias(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        ModelAndView mv = new ModelAndView("categoria/categoria-listagem");
        Page<Categoria> categorias = categoriaService.findAll(PageRequest.of(currentPage - 1, pageSize));
        mv.addObject("categorias", categorias);

        int totalPages = categorias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mv.addObject("pageNumbers", pageNumbers);
        }

        return mv;
    }

    @GetMapping("/incluir")
    public ModelAndView carregarFormInclusao() {
        ModelAndView mv = new ModelAndView("categoria/categoria-form");
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView carregarFormEdicao(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        ModelAndView mv = new ModelAndView("categoria/categoria-form");
        mv.addObject("categoria", categoria);
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model) {

        if (result.hasErrors()) {
            ModelAndView categoriaForm = new ModelAndView("categoria/categoria-form");
            categoriaForm.addObject("mensagem", "Verifique os erros do formulário!");
            return categoriaForm;
        }
        categoriaService.save(categoria);

        return new ModelAndView("redirect:/categorias");
    }

    @GetMapping(value="/excluir/{id}")
    public ModelAndView excluirCategoria(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return new ModelAndView("redirect:/categorias");
    }

}
