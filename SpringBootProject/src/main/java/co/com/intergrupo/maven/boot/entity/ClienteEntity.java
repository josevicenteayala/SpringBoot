package co.com.intergrupo.maven.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	public ClienteEntity() {
		//constructor vacio
	}
	
	public ClienteEntity(int id, String nombre, String direccion, Date fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "ClienteEntity [id=" + id + ", " + (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (direccion != null ? "direccion=" + direccion + ", " : "")
				+ (fechaNacimiento != null ? "fechaNacimiento=" + fechaNacimiento : "") + "]";
	}
}
