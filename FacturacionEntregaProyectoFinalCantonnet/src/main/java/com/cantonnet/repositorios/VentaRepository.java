package com.cantonnet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cantonnet.modelo.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}