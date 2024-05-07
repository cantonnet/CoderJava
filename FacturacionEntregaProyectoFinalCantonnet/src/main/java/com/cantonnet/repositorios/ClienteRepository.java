package com.cantonnet.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cantonnet.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	//Optional <Cliente> findByDni(Integer dni);
}
