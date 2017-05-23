/**
 * 
 */
package proyecto;


/**
 * @author ALUMNO
 *
 */
public class Jugador {
	public int cod_jugador;
	public String nombre;
	public String apellidos;
	public String nick;
	public int edad;
	public String posicion_jugador;
	/**
	 * @return the cod_jugador
	 */
	protected int getCod_jugador() {
		return cod_jugador;
	}
	/**
	 * @param cod_jugador the cod_jugador to set
	 */
	protected void setCod_jugador(int cod_jugador) {
		this.cod_jugador = cod_jugador;
	}
	/**
	 * @return the nombre
	 */
	protected String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	protected String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	protected void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the nick
	 */
	protected String getNick() {
		return nick;
	}
	/**
	 * @param nick the nick to set
	 */
	protected void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @return the edad
	 */
	protected int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	protected void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return the posicion_jugador
	 */
	protected String getPosicion_jugador() {
		return posicion_jugador;
	}
	/**
	 * @param posicion_jugador the posicion_jugador to set
	 */
	protected void setPosicion_jugador(String posicion_jugador) {
		this.posicion_jugador = posicion_jugador;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Jugador [cod_jugador=" + cod_jugador + ", " + (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (apellidos != null ? "apellidos=" + apellidos + ", " : "")
				+ (nick != null ? "nick=" + nick + ", " : "") + "edad=" + edad + ", "
				+ (posicion_jugador != null ? "posicion_jugador=" + posicion_jugador : "") + "]";
	}

}
