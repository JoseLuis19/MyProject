/**
 * 
 */
package proyecto;

/**
 * @author ALUMNO
 *
 */
public class Equipo {
	public int codigo_equipo;
	public String nombre_equipo;
	public int puntos;
	/**
	 * @return the codigo_equipo
	 */
	protected int getCodigo_equipo() {
		return codigo_equipo;
	}
	/**
	 * @param codigo_equipo the codigo_equipo to set
	 */
	protected void setCodigo_equipo(int codigo_equipo) {
		this.codigo_equipo = codigo_equipo;
	}
	/**
	 * @return the nombre_equipo
	 */
	protected String getNombre_equipo() {
		return nombre_equipo;
	}
	/**
	 * @param nombre_equipo the nombre_equipo to set
	 */
	protected void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}
	/**
	 * @return the puntos
	 */
	protected int getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	protected void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Equipo [codigo_equipo=" + codigo_equipo + ", "
				+ (nombre_equipo != null ? "nombre_equipo=" + nombre_equipo + ", " : "") + "puntos=" + puntos + "]";
	}
}
