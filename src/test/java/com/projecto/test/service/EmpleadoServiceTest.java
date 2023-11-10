package com.projecto.test.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projecto.dto.EmpleadoDTO;
import com.projecto.models.Empleado;
import com.projecto.repository.EmpleadoRepository;
import com.projecto.service.EmpleadoServiceImp;

@ExtendWith(SpringExtension.class)
public class EmpleadoServiceTest {
	
	@InjectMocks
	private EmpleadoServiceImp empleadoService;
	
	@Mock
	private EmpleadoRepository empleadoRepository;
	
	@DisplayName("validar listar empleados")
	@Test
	public void getEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		Empleado e1 = new Empleado();
		e1.setNombre("Juan");
		e1.setApellidoPaterno("Lopez");
		e1.setApellidoMaterno("Reyes");
		e1.setEdad(29);
		e1.setFechaNacimiento("25-09-1996");
		e1.setId(Long.parseLong("1".toString()));
		e1.setPuesto("director");
		e1.setSexo("hombre");
		empleados.add(e1);
		
		Mockito.when(empleadoRepository.findAll()).thenReturn(empleados);
		
		List<Empleado> empleadosList = empleadoService.getEmpleados();
		Assertions.assertEquals(1, empleadosList.size());
		Assertions.assertNotNull(empleadosList);
	}
	
	
	@DisplayName("validar registrarEmpleado")
	@Test
	public void validateRegistrarEmpleado() {
		List<Empleado> empleados = new ArrayList<>();
		Empleado e1 = new Empleado();
		e1.setNombre("Juan");
		e1.setApellidoPaterno("Lopez");
		e1.setApellidoMaterno("Reyes");
		e1.setEdad(29);
		e1.setFechaNacimiento("25-09-1996");
		e1.setId(Long.parseLong("1".toString()));
		e1.setPuesto("director");
		e1.setSexo("hombre");
		empleados.add(e1);
		
		List<Empleado> empleadosG = new ArrayList<>();
		Empleado e2 = new Empleado();
		e2.setNombre("Juan");
		e2.setApellidoPaterno("Lopez");
		e2.setApellidoMaterno("Reyes");
		e2.setEdad(29);
		e2.setFechaNacimiento("25-09-1996");
		e2.setId(Long.parseLong("1".toString()));
		e2.setPuesto("director");
		e2.setSexo("hombre");
		empleadosG.add(e2);
		
		EmpleadoDTO dto = new EmpleadoDTO();
		dto.setListaEmpleados(empleadosG);
		
		Mockito.when(empleadoRepository.saveAll(any())).thenReturn(empleados);
		
		Boolean guardado = empleadoService.registrarEmpleado(dto);
		Assertions.assertSame(true, guardado);
		Assertions.assertNotNull(guardado);
	}
	
	
	@DisplayName("validar eliminar empleado")
	@Test
	public void validateEliminarEmpleado() {
		
		Boolean eliminado = empleadoService.eliminarEmpleado(1l);
		Assertions.assertSame(true, eliminado);
		Assertions.assertNotNull(eliminado);
	}
	
	
	@DisplayName("validar actualizar Empleado")
	@Test
	public void validateActualizarEmpleado() {
		
		Empleado e1 = new Empleado();
		e1.setNombre("Juan");
		e1.setApellidoPaterno("Lopez");
		e1.setApellidoMaterno("Reyes");
		e1.setEdad(29);
		e1.setFechaNacimiento("25-09-1996");
		e1.setId(Long.parseLong("1".toString()));
		e1.setPuesto("director");
		e1.setSexo("hombre");
		Optional<Empleado> emp = Optional.of(e1);
		
		Empleado e2 = new Empleado();
		e2.setNombre("Juan");
		e2.setApellidoPaterno("Lopez");
		e2.setApellidoMaterno("Reyes");
		e2.setEdad(29);
		e2.setFechaNacimiento("25-09-1996");
		e2.setId(Long.parseLong("1".toString()));
		e2.setPuesto("director");
		e2.setSexo("hombre");
		
		Mockito.when(empleadoRepository.findById(any())).thenReturn(emp);
		Mockito.when(empleadoRepository.save(any())).thenReturn(e2);
		
		Empleado guardado = empleadoService.actualizarEmpleado(e1, 1l);
		Assertions.assertNotNull(guardado);
		Assertions.assertEquals(1l, guardado.getId());
		
		
	}
	

}
