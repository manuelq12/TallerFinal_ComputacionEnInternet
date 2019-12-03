package ci.workshop.test.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateObject {

	public DateObject(LocalDate fechaInicio2, LocalDate fechaFin2) {
		// TODO Auto-generated constructor stub
		this.fechaInicio = fechaInicio2;
		this.fechaFin = fechaFin2;
	}
	public DateObject() {
		// TODO Auto-generated constructor stub
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
	
}
