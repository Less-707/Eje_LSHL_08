package com.upiiz.tienda.controllers;

import com.upiiz.tienda.models.Producto;
import com.upiiz.tienda.services.CategoriaService;
import com.upiiz.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "listado-productos";
    }

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.obtenerTodas()); // Para el select
        return "formulario-agregar-producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/actualizar/{id}")
    public String formularioActualizar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "formulario-actualizar-producto";
    }

    @GetMapping("/eliminar/{id}")
    public String formularioEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        return "formulario-eliminar-producto";
    }

    @PostMapping("/eliminar")
    public String confirmarEliminar(@RequestParam Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}