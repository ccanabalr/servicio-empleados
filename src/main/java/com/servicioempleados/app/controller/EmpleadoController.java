package com.servicioempleados.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicioempleados.app.models.entity.Empleado;
import com.servicioempleados.app.models.service.EmpleadoService;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping("/empleados")
	public List<Empleado> findAll() {
		return empleadoService.findAll();
	}

	@GetMapping("/empleado/{id}")
	public ResponseEntity<?> findAllById(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();

		try {
			empleado = empleadoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos!");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (empleado == null) {
			response.put("mensaje", "El Cliente ID: ".concat(id.toString().concat(" NO existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}

	@PostMapping("/empleados")
	public ResponseEntity<?> save(@RequestBody Empleado empleado) {
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			empleadoNew = empleadoService.save(empleado);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al crear un nuevo empleado");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El empleado ha sido creado con exito!");
		response.put("Cliente", empleadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/empleados")
	public ResponseEntity<?> update(@RequestBody Empleado empleado) {
		Empleado empleadoActual = empleadoService.findById(empleado.getId());
		Empleado empleadoUpdate = null;

		Map<String, Object> response = new HashMap<>();

		try {
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setApellido(empleado.getApellido());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setFecha_ingreso(empleado.getFecha_ingreso());

			empleadoUpdate = empleadoService.update(empleadoActual);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar un nuevo empleado");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El empleado ha sido actualizado con exito!");
		response.put("Cliente", empleadoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			empleadoService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar empleado en la base de datos!");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El empleado ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
