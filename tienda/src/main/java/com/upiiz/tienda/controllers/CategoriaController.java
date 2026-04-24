package com.upiiz.tienda.controllers;

import com.upiiz.tienda.models.Categoria;
import com.upiiz.tienda.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "listado-categorias";
    }

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formulario-agregar-categoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/actualizar/{id}")
    public String formularioActualizar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerPorId(id));
        return "formulario-actualizar-categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String formularioEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerPorId(id));
        return "formulario-eliminar-categoria";
    }

    @PostMapping("/eliminar")
    public String confirmarEliminar(@RequestParam Long id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}