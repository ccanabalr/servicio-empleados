package com.servicioempleados.app.models.service;

import java.util.List;

import com.servicioempleados.app.models.entity.Empleado;

public interface EmpleadoService {

	public List<Empleado> findAll();
	public Empleado findById(Long id);
	public Empleado save(Empleado empleado);
	public Empleado update(Empleado empleado);
	public void deleteById(Long id);
}
