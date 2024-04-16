package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderhouse.modelo.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
