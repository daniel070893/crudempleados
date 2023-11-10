package com.projecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecto.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
