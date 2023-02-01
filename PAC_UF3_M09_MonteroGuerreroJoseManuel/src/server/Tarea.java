package server;

public class Tarea {
	private String descripcion;
	private String estado;

//Constructor vacío
	public Tarea() {
		super();
	}

//Constructor sobrecargado con paso de parámetros
	public Tarea(String descripcion, String estado) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
	}

//Getters y Setters
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

//toString para ver el resultado de la clase
	@Override
	public String toString() {
		return "Tarea [descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}
