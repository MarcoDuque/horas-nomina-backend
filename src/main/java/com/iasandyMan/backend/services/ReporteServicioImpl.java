package com.iasandyMan.backend.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iasandyMan.backend.domain.Reporte;
import com.iasandyMan.backend.domain.ReporteFinal;
import com.iasandyMan.backend.repositories.IReporteDao;
import com.iasandyMan.backend.services.contracts.IReporteServicio;

@Service
public class ReporteServicioImpl implements IReporteServicio {

	private IReporteDao reporteDao;

	@Autowired
	public ReporteServicioImpl(IReporteDao reporteDao) {
		this.reporteDao = reporteDao;
	}

	@Override
	public List<Reporte> findAll() {
		return (List<Reporte>) reporteDao.findAll();
	}

	@Override
	public ReporteFinal findById(String idTecnico, int semana) {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR, 0);
		today.clear(Calendar.MINUTE);
		today.clear(Calendar.SECOND);
		today.clear(Calendar.MILLISECOND);

		today.set(Calendar.WEEK_OF_YEAR, semana);
		today.set(Calendar.DAY_OF_WEEK, today.getFirstDayOfWeek());

		Date minDate = today.getTime();
		today.add(Calendar.DAY_OF_WEEK, 7);
		Date maxDate = today.getTime();

		List<Reporte> reporte = reporteDao.findAllByIdTecnico(idTecnico, minDate, maxDate);

		ReporteFinal reporteFinal = new ReporteFinal();
		reporteFinal.setHorasNormales(horasNormales(reporte));
		reporteFinal.setHorasNocturnas(horasNocturnas(reporte));
		reporteFinal.setHorasDominicales(horasDominicales(reporte));
		reporteFinal.setHorasNormalesExtras(horasNormalesExtras(reporte, reporteFinal));
		reporteFinal.setHorasNocturnasExtras(horasNocturnasExtras(reporte, reporteFinal));
		reporteFinal.setHorasDominicalesExtras(horasDominicalesExtras(reporte, reporteFinal));

		return reporteFinal;
	}

	private int getDifferenceBetwenDates(Date dateInicio, Date dateFinal) {
		long milliseconds = dateFinal.getTime() - dateInicio.getTime();
		int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
		return hours;
	}

	private int horasNormales(List<Reporte> reportes) {

		int horas = 0;
		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();

		for (Reporte reporte : reportes) {
			Date inicio = reporte.getFechaInicio();
			Date fin = reporte.getFechaFin();

			calendarInicio.setTime(inicio);
			calendarFin.setTime(fin);

			if (calendarInicio.get(Calendar.DAY_OF_MONTH) != Calendar.SATURDAY
					&& calendarInicio.get(Calendar.DAY_OF_MONTH) != Calendar.SUNDAY) {
				if (calendarInicio.get(Calendar.HOUR_OF_DAY) >= 7 && calendarFin.get(Calendar.HOUR_OF_DAY) <= 20) {
					horas += getDifferenceBetwenDates(inicio, fin);
				}
			}
		}

		return horas;
	}

	private int horasNocturnas(List<Reporte> reportes) {
		int horas = 0;
		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();

		for (Reporte reporte : reportes) {
			Date inicio = reporte.getFechaInicio();
			Date fin = reporte.getFechaFin();

			calendarInicio.setTime(inicio);
			calendarFin.setTime(fin);

			if (calendarInicio.get(Calendar.DAY_OF_MONTH) != Calendar.SATURDAY
					&& calendarInicio.get(Calendar.DAY_OF_MONTH) != Calendar.SUNDAY) {
				if (calendarInicio.get(Calendar.HOUR_OF_DAY) >= 20 && calendarFin.get(Calendar.HOUR_OF_DAY) <= 7) {
					horas += getDifferenceBetwenDates(inicio, fin);
				}
			}
		}

		return horas;
	}

	private int horasDominicales(List<Reporte> reportes) {
		int horas = 0;
		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();

		for (Reporte reporte : reportes) {
			Date inicio = reporte.getFechaInicio();
			Date fin = reporte.getFechaFin();

			calendarInicio.setTime(inicio);
			calendarFin.setTime(fin);

			if (calendarInicio.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY) {
				horas += getDifferenceBetwenDates(inicio, fin);
			}
		}

		return horas;
	}

	private int horasNormalesExtras(List<Reporte> reportes, ReporteFinal reporteFinal) {
		int horasNormales = reporteFinal.getHorasNormales();
		if (horasNormales > 48) {
			return horasNormales - 48;
		}
		return 0;
	}

	private int horasNocturnasExtras(List<Reporte> reportes, ReporteFinal reporteFinal) {
		int horasNocturnas = reporteFinal.getHorasNocturnas();
		if (horasNocturnas > 48) {
			return horasNocturnas - 48;
		}
		return 0;
	}

	private int horasDominicalesExtras(List<Reporte> reportes, ReporteFinal reporteFinal) {
		int horasDominicales = reporteFinal.getHorasDominicales();
		if (horasDominicales > 48) {
			return horasDominicales - 48;
		}
		return 0;
	}

	@Override
	public Reporte save(Reporte reporte) {
		return reporteDao.save(reporte);
	}

}
