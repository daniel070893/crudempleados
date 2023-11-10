package com.projecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.dto.EmpleadoDTO;
import com.projecto.models.Empleado;
import com.projecto.service.IEmpleadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "Api de empleados")
public class EmpleadoController {
	
	
	@Autowired
	private IEmpleadoService empleadoServiceImp;

	
	/**
	 * Muestra todos los empleados almacenados en la bd
	 *
	 * @return ResponseEntity
	 */
	@GetMapping(value="/empleados")
	@ApiOperation(value = "getEmpleados", notes = "Permite recuperar un listado de los empleados guardados")
	public ResponseEntity<List<Empleado>> getEmpleados(){
		 try {
	            return new ResponseEntity<>(empleadoServiceImp.getEmpleados(), HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	/**
	 * Crea un nuevo empleado
	 *
	 * @param empleadoDTO
	 * @return ResponseEntity
	 */
	@PostMapping(value="/empleados")
	@ApiOperation(value = "registrarEmpleado", notes = "Permite registrar uno o mas empleados")
	public ResponseEntity<Map> registrarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		Boolean bandera = empleadoServiceImp.registrarEmpleado(empleadoDTO);
		Map<String, Object> mapEmpleado = new HashMap<String, Object>();
		mapEmpleado.put("Mensaje", "El empleado se agrego correctamente");
		return new ResponseEntity<Map>(mapEmpleado,HttpStatus.CREATED);
	}
	
	/**
	 * Elimina empleados por Id
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping(value="/empleados/{id}")
	@ApiOperation(value = "eliminarEmpleadosById", notes = "Permite eliminar un empleado por medio de su id")
	   public ResponseEntity<HttpStatus> eliminarEmpleadoById(@PathVariable("id") long id) {
        try {
            //Valida si el empleado existe en la base de datos.
            Boolean empleado = empleadoServiceImp.eliminarEmpleado(id);

            if (empleado) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * Actualizar empleado usando el id
	 *
	 * @param id
	 * @param empleado
	 * @return ResponseEntity
	 */
	@PutMapping(value="/empleados/{id}")
	@ApiOperation(value = "actualizarEmpleado", notes = "Permite editar un empleado por medio de su Id")
	 public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") long id, @RequestBody Empleado empleado) {

		//Valida si el empleado existe en la base de datos.
        Empleado empleadoBean = empleadoServiceImp.actualizarEmpleado(empleado, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
