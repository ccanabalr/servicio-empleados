package com.servicioempleados.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicioempleados.app.exception.RegistroNoEncontradoException;
import com.servicioempleados.app.models.dao.EmpleadoDao;
import com.servicioempleados.app.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	
	@Autowired
	private EmpleadoDao dao;

	@Override
	public List<Empleado> findAll() {
		return dao.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		return dao.findById(id).orElseThrow(()-> new  RegistroNoEncontradoException());
	}

	@Override
	public Empleado save(Empleado empleado) {
		return dao.save(empleado);
	}

	@Override
	public Empleado update(Empleado empleado) {
		this.findById(empleado.getId());
		return dao.save(empleado);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		dao.deleteById(id);
		
	}

}
