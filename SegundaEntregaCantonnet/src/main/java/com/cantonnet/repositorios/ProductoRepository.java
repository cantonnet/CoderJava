package com.cantonnet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cantonnet.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}