package com.projecto.service;

import java.util.List;

import com.projecto.dto.EmpleadoDTO;
import com.projecto.models.Empleado;

public interface IEmpleadoService {
	
	List<Empleado> getEmpleados();
	
	Boolean registrarEmpleado (EmpleadoDTO empleadoDTO);
	
	Boolean eliminarEmpleado (Long id);
	
	Empleado actualizarEmpleado (Empleado empleado, Long id);
	
	
}
