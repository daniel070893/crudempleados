package com.projecto.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecto.controllers.EmpleadoController;
import com.projecto.dto.EmpleadoDTO;
import com.projecto.models.Empleado;
import com.projecto.repository.EmpleadoRepository;
import com.projecto.service.IEmpleadoService;

@ContextConfiguration(classes = {EmpleadoController.class})
@WebMvcTest(controllers = EmpleadoController.class)
public class EmpleadoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@MockBean
	private IEmpleadoService empleadoService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@DisplayName("Validar listado de empleados")
	@Test
	public void validaGetEmpleados() throws Exception {
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
		
		Mockito.when(empleadoService.getEmpleados()).thenReturn(empleados);
		mockMvc.perform(get("/api/empleados"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(1));
		
	}
	
	@DisplayName("Validar alta empleados")
	@Test
	public void validateRegistrarEmpleado() throws JsonProcessingException, Exception {
		Map<String, Object> retorno = new HashMap<>();
		retorno.put("mensaje", "Registro exitoso");
		
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
		
		EmpleadoDTO dto = new EmpleadoDTO();
		dto.setListaEmpleados(empleados);
		
		Boolean resp = true;
		Mockito.when(empleadoService.registrarEmpleado(any())).thenReturn(resp);
		mockMvc.perform(post("/api/empleados")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.Mensaje").value("El empleado se agrego correctamente"));
	}
	
	
	@DisplayName("Validar eliminar Empleado")
	@Test
	public void validateEliminarEmpleado() throws Exception {
		
		Mockito.when(empleadoService.eliminarEmpleado(anyLong())).thenReturn(true);
		
		mockMvc.perform(delete("/api/empleados/{id}", 1)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@DisplayName("Validar actualizar empleado")
	@Test
	public void validateActualizarEmpleado() throws JsonProcessingException, Exception {
		Empleado e1 = new Empleado();
		e1.setNombre("Juan");
		e1.setApellidoPaterno("Lopez");
		e1.setApellidoMaterno("Martinez");
		e1.setEdad(29);
		e1.setFechaNacimiento("25-09-1996");
		e1.setId(Long.parseLong("1".toString()));
		e1.setPuesto("gerente");
		e1.setSexo("hombre");
		
		Empleado e2 = new Empleado();
		e2.setNombre("Juan");
		e2.setApellidoPaterno("Lopez");
		e2.setApellidoMaterno("Reyes");
		e2.setEdad(29);
		e2.setFechaNacimiento("25-09-1996");
		e2.setId(Long.parseLong("1".toString()));
		e2.setPuesto("director");
		e2.setSexo("hombre");
		
		long id = 1l;
		Mockito.when(empleadoService.actualizarEmpleado(e1, id)).thenReturn(e2);
		
		mockMvc.perform(put("/api/empleados/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(e1)))
		.andExpect(status().isOk());
	}

}
