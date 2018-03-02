package co.com.intergrupo.maven.boot.dto;

import java.util.Date;

public class ClienteDto {
	
	private Integer id;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	
	public ClienteDto() {
		//constructor vacio
	}
	
	public ClienteDto(Integer id, String nombre, String direccion, Date fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "ClienteDto [" + (id != null ? "id=" + id + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (direccion != null ? "direccion=" + direccion + ", " : "")
				+ (fechaNacimiento != null ? "fechaNacimiento=" + fechaNacimiento : "") + "]";
	}	
	
}
