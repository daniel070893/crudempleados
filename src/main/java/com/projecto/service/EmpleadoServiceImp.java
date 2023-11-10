package com.projecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.dto.EmpleadoDTO;
import com.projecto.models.Empleado;
import com.projecto.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImp implements IEmpleadoService{

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public List<Empleado> getEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public Boolean registrarEmpleado(EmpleadoDTO empleadoDTO) {
		empleadoRepository.saveAll(empleadoDTO.getListaEmpleados());
		return true;
	}

	@Override
	public Boolean eliminarEmpleado(Long id) {
		empleadoRepository.deleteById(id);
		return true;
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado, Long id) {
		
		 Empleado empleadoBean = getEmpGuardados(id);
		 if (empleadoBean != null) {
	            empleadoBean.setNombre(empleado.getNombre());
	            empleadoBean.setSegundoNombre(empleado.getSegundoNombre());
	            empleadoBean.setApellidoPaterno(empleado.getApellidoPaterno());
	            empleadoBean.setApellidoMaterno(empleado.getApellidoMaterno());
	            empleadoBean.setEdad(empleado.getEdad());
	            empleadoBean.setSexo(empleado.getSexo());
	            empleadoBean.setPuesto(empleado.getPuesto());
	            empleadoBean.setFechaNacimiento(empleado.getFechaNacimiento());
	            empleadoRepository.save(empleadoBean);
	            return empleadoBean;
	        }
		return null;
	}
	
	/**
     * Metodo para ir por id por los empleados almacenados en la bd
     *
     * @param id
     * @return empleadoBean
     */
    private Empleado getEmpGuardados(long id) {
       Optional<Empleado> empleadoBean = empleadoRepository.findById(id);

			 if (empleadoBean.isPresent()) { return empleadoBean.get(); }
			 
        return null;
    }

}
