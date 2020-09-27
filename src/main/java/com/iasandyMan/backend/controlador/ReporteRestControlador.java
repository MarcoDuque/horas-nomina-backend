package com.iasandyMan.backend.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iasandyMan.backend.domain.Reporte;
import com.iasandyMan.backend.services.contracts.IReporteServicio;

@RestController
@RequestMapping("/api")
public class ReporteRestControlador {

	private IReporteServicio reporteServicio;
	
	@Autowired
	public ReporteRestControlador(IReporteServicio reporteServicio) {
		this.reporteServicio = reporteServicio;
	}

	@GetMapping("/reportes")
	public List<Reporte> index() {
		return reporteServicio.findAll();
	}

	@GetMapping("/reporte")
	public Object show(@RequestParam String idTecnico, @RequestParam int semana) {
		return reporteServicio.findById(idTecnico, semana);
	}

	@PostMapping("/reportes")
	public Reporte create(@Valid @RequestBody Reporte reporte) {
		return reporteServicio.save(reporte);
	}

}
