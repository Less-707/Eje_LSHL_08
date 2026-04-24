package com.upiiz.tienda.services;

import com.upiiz.tienda.models.Categoria;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private List<Categoria> categorias = new ArrayList<>();
    private Long idCounter = 1L;

    public CategoriaService() {
        // Datos de prueba iniciales
        categorias.add(new Categoria(idCounter++, "Deportes", "Artículos deportivos"));
        categorias.add(new Categoria(idCounter++, "Bebidas", "Todo tipo de bebidas"));
    }

    public List<Categoria> obtenerTodas() { return categorias; }

    public Categoria obtenerPorId(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void guardar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(idCounter++);
            categorias.add(categoria);
        } else {
            Categoria existente = obtenerPorId(categoria.getId());
            existente.setNombre(categoria.getNombre());
            existente.setDescripcion(categoria.getDescripcion());
        }
    }

    public void eliminar(Long id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }
}