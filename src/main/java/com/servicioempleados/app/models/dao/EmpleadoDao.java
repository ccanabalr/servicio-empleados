package com.servicioempleados.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioempleados.app.models.entity.Empleado;

public interface EmpleadoDao extends JpaRepository<Empleado, Long> {

}
