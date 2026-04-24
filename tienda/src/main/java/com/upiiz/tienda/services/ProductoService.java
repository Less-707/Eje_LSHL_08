package com.upiiz.tienda.services;

import com.upiiz.tienda.models.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    private Long idCounter = 1L;

    public ProductoService() {
        productos.add(new Producto(idCounter++, "Raqueta", 450.00, "Deportes"));
        productos.add(new Producto(idCounter++, "Coca-Cola", 25.00, "Bebidas"));
    }

    public List<Producto> obtenerTodos() { return productos; }

    public Producto obtenerPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idCounter++);
            productos.add(producto);
        } else {
            Producto existente = obtenerPorId(producto.getId());
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setCategoria(producto.getCategoria());
        }
    }

    public void eliminar(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }
}