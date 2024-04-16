package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderhouse.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}