package ci.workshop.test.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DateObject {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
	
}
