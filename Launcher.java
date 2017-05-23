/**
 * 
 */
package proyecto;
import java.sql.*;
import java.util.Scanner;

/**
 * @author ALUMNO
 *@version 1.1
 *@param launcher Este conecta eclipse con nuestra base de datos
 */
public class Launcher {
	 private static Connection conexion = null;
	    private static String bd = "proyecto"; // Nombre de BD.
	    private static String user = "root"; // Usuario de BD.
	    private static String password = ""; // Password de BD.
	    // Driver para MySQL en este caso.
	    private static String driver = "com.mysql.jdbc.Driver";
	    // Ruta del servidor.
	    private static String server = "jdbc:mysql://localhost/" + bd;

	/**
	 * @param args
	 * @throws SQLException Excepcion para una conexion a una base de datos
	 */
//En el main se tiene un menu que llama a los metodos de alta,baja,modificar,listar y dos consultas.
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String opc;
		String respuesta;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("¿Desea ir a jugadores o a equipo?");
			 opc= teclado.next();
			switch (opc) {
			case "1":
				MenuJugador();
				break;
			case "2":
				MenuEquipo();
			break;
			default:
				 System.out.println("ERROR");
				break;
			}
			System.out.println("¿Quiere seguir haciendo algo?(si/no)");
			respuesta = teclado.next();
		} while (respuesta.equalsIgnoreCase("si"));
		System.out.println("ADIOS");
		System.out.println("FIN DE EJECUCIÓN.");
	}
	
	public static void MenuJugador() throws SQLException{
		String respuesta;
		String respuesta2;
		//Primero hay que conectar con la base de datos y establecer una conexion
        System.out.println("Inicio con la base de datos.");
        conectar();
        Statement st = conexion();
        //Se crea un menu que pregunta al usuario que hacer y cuando salir
        do {
			
			String cadena = "SELECT * FROM Jugador;";
	        ResultSet rs = consultaQuery(st, cadena);
	        System.out.println("Elige la opcion: 1-Listar,2-Alta,3-Modificar,4-baja,5-Consulta1,6-Consulta2");
			Scanner teclado = new Scanner(System.in);
			//Le damos al usuario la opcion de elegir un metodo.
			respuesta= teclado.next();
				
			switch (respuesta) {
			case "1":
				listar(rs);
				break;
			case "2":
				 AltaJugador(st);
				break;
			case "3":
				ModificarJugador(st,rs);
				break;
			case "4":
				BorrarJugador(st,rs);
				break;
			case "5":
				ConsultaJugador(st);
				break;
			case "6":
				ConsultaJugador2(st);
				break;
			default:
				System.out.println("ERROR");
				break;
			}
			System.out.println("¿Quiere seguir haciendo algo?(si/no)");
			respuesta2 = teclado.next();
		} while (respuesta2.equalsIgnoreCase("si"));
		System.out.println("ADIOS");
		//Se cierra la conexion con la base de datos para futuros problemas.
        cerrar(st);
        System.out.println("FIN DE EJECUCIÓN.");
	}
	
	public static void MenuEquipo() throws SQLException{
		String respuesta;
		String respuesta2;
		//Primero hay que conectar con la base de datos y establecer una conexion
        System.out.println("Inicio con la base de datos.");
        conectar();
        Statement st = conexion();
        //Se crea un menu que pregunta al usuario que hacer y cuando salir
        do {
			
			String cadena = "SELECT * FROM Equipo;";
	        ResultSet rs = consultaQuery(st, cadena);
	        System.out.println("Elige la opcion: 1-Listar,2-Alta,3-Modificar,4-baja,5-ModificarPuntos,6-Clasificacion");
			Scanner teclado = new Scanner(System.in);
			respuesta= teclado.next();
				
			switch (respuesta) {
			case "1":
				listarEquipo(rs);
				break;
			case "2":
				AltaEquipo(st);
				break;
			case "3":
				ModificarEquipo(st,rs);
				break;
			case "4":
				BorrarEquipo(st);
				break;
			case "5":
				ModifPuntos(st,rs);
				break;
			case "6":
				Clasificacion(st);
				break;
			default:
				System.out.println("ERROR");
				break;
			}
			System.out.println("¿Quiere seguir haciendo algo?(si/no)");
			respuesta2 = teclado.next();
		} while (respuesta2.equalsIgnoreCase("si"));
		//
       // str.getClass().getSimpleName();
        
        System.out.println("ADIOS");
		//Se cierra la conexion con la base de datos para futuros problemas.
        cerrar(st);
        System.out.println("FIN DE EJECUCIÓN.");
	}
	
	
	
	
/**
 * @param Método necesario para conectarse al Driver y poder usar MySQL.
 */
    public static void conectar() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(server, user, password);
            System.out.println("conectado con exito");
        } catch (Exception e) {
            System.out.println("Error: Imposible realizar la conexion a BD.");
            e.printStackTrace();
        }
    }
    /**
     * @param conexion Establece la conexion para poder trabajar con la base de datos
     * @return Statement Devuelve una declaración de la conexion si es correcta o da fallo.
     */
    private static Statement conexion() {
        Statement st = null;
        try {
            st = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: Conexión incorrecta.");
            e.printStackTrace();
        }
        return st;
    }
    /**
     * Método para realizar consultas que no modifican nada, estilo SELECT
     * @param st
     * @param cadena
     * @return 
     * @throws SQLException  Excepcion para una conexion a una base de datos
     */
    private static ResultSet consultaQuery(Statement st, String cadena) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * Método para realizar consultas de alta,baja y modificación.
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    private static int consultaActualiza(Statement st, String cadena) {
        int rs = -1;
        try {
            rs = st.executeUpdate(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * Metodo que lista todos los jugadores existentes
     * @param rs
     * @throws SQLException
     */
    public static void listar(ResultSet rs) throws SQLException{
        if (rs != null) {
            System.out.println("El listado de jugadores es:");
 
            while (rs.next()) {
                System.out.println("  cod_jugador: " + rs.getObject("cod_jugador"));
                System.out.println("  Datos del jugador: "
                        +"Nombre: "+rs.getObject("nombre") + " "
                        +"Apellido: " +rs.getObject("apellidos")+"Edad: "+" "+ rs.getObject("edad"));
 
                System.out.println("  En el juego: " + "Nick: "+rs.getObject("nick")
                        + " " +"Posicion del jugador: " +rs.getObject("posicion_jugador"));
 
                System.out.println("- ");
            }
            cerrar(rs);
        }

    }
    
   /**
    * Metodo para dar de alta a un jugador 
    *  Se necesita llamar a consultaActualiza para poder hacer este metodo.
    * @param st
    */
    
    public static void AltaJugador(Statement st){
    	try{
    		Scanner scan = new Scanner(System.in);
			 String nombre;
			 String apellidos;
			 String nick;
			 int edad;
			 String posicion_jugador;
			 String respuesta;
			 String cadena;
			 
			 //Pedimos el nombre del jugador
	   		        System.out.print("Introduce nombre: ");
	   		        nombre = scan.next();
	   		     //Pedimos el apellido del jugador
	   		        System.out.print("Introduce apellido: ");
	   		        apellidos = scan.next();
	   		     //Pedimos el nick del jugador
	   		        System.out.print("Introduce nick: ");
	   		        nick = scan.next();
	   		     //Pedimos la edad del jugador
	   		        System.out.print("Introduce edad: ");
	   		        edad = scan.nextInt();
	   		     //Pedimos la posicion del jugador del jugador
	   		        System.out.print("Introduce posicion jugador: ");
	   		        posicion_jugador = scan.next(); 
	   		        //Esta es la consulta que se hace con los datos que hemos pedido al usuario.
				 cadena="INSERT INTO Jugador (nombre,apellidos,nick,edad,posicion_jugador) VALUES ('"+nombre+"','"+apellidos+"','"+nick+"','"+edad+"','"+posicion_jugador+"')";
				 consultaActualiza(st, cadena);
			 System.out.println("Se ha dado de alta correctamente");
			 System.out.print("");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public static void AltaEquipo(Statement st){
    	try{
    		Scanner scan = new Scanner(System.in);
			 String nombre_equipo;
			 int puntos;
			 String respuesta;
			 String cadena;
			 
			 //Pedimos el nombre del equipo
	   		        System.out.print("Introduce nombre del equipo: ");
	   		     nombre_equipo = scan.next();
	   		     //Pedimos el nick del equipo
	   		        System.out.print("Introduce puntos del equipo: ");
	   		     puntos = scan.nextInt();
	   		        //Esta es la consulta que se hace con los datos que hemos pedido al usuario.
				 cadena="INSERT INTO equipo (nombre_equipo,puntos) VALUES ('"+nombre_equipo+"','"+puntos+"')";
				 consultaActualiza(st, cadena);
			 System.out.println("Se ha dado de alta correctamente");
			 System.out.print("");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public static void listarEquipo(ResultSet rs) throws SQLException{
        if (rs != null) {
            System.out.println("El listado de equipos es:");
 
            while (rs.next()) {
                System.out.println("  cod_equipo: " + rs.getObject("cod_equipo"));
                System.out.println("  Datos del equipo: "
                        +"Nombre del equipo: " +rs.getObject("nombre_equipo") + " "
                        +"Puntos: " +rs.getObject("puntos"));
 
                System.out.println("- ");
            }
            cerrar(rs);
        }
    }
    
    public static void ModificarEquipo(Statement st,ResultSet rs) throws SQLException{
    	Scanner scan = new Scanner(System.in);
    	String nombre_equipo;
    	int puntos;
    	String cadena ="";
    	int codigo;
    	String respuesta;
        if (rs != null) {
            System.out.println("El listado de equipos es:");
 
            while (rs.next()) {
                System.out.println("  Datos del equipo: "
                        + rs.getObject("cod_equipo") + " "
                        + rs.getObject("nombre_equipo"));
                System.out.println("- ");
            }
            cerrar(rs);
        }
      //Pedimos al usuario a quien desea modificar mediante su codigo
		 System.out.print("¿Que equipo desea modificar?(Introduce codigo del equipo)");
		 codigo = scan.nextInt();
		//Le preguntamos que campo desea modificar
		 System.out.print("¿Que desea modificar?-->1-Nombre, 2-Puntos");
		 respuesta = scan.next();
		 switch (respuesta) {
		case "1":
			//Pedimos al usuario un nuevo nombre para el equipo y lanzar la consulta
			 System.out.print("Introduce un nuevo nombre de equipo");
			 nombre_equipo = scan.next();
			cadena="UPDATE equipo SET nombre_equipo='"+nombre_equipo+"'WHERE cod_equipo='"+codigo+"'";
			break;
		case "2":
			//Pedimos al usuario nuevos puntos para el equipo y lanzar la consulta
			 System.out.print("Introduce nuevos puntos para el equipo");
			  puntos = scan.nextInt();
			cadena="UPDATE equipo SET puntos='"+puntos+"'WHERE cod_equipo='"+codigo+"'";
			break;
		default:
			System.out.print("ERROR");
			break;
		}
		 int  aux = consultaActualiza(st, cadena);
		 if (aux==0) {
			 System.out.print("No se ha encontrado al equipo");
		 }else{
			 System.out.println("Se ha modificado el equipo con exito");
			 System.out.print(" ");
		 }
    }
    
    /**
     * Metodo para modificar a un jugador
     * Se necesita llamar a consultaActualiza para poder hacer este metodo.
     * @param st
     * @param rs
     * @throws SQLException
     */
    public static void ModificarJugador(Statement st,ResultSet rs) throws SQLException{
    	Scanner scan = new Scanner(System.in);
		 String nombre;
		 String apellidos;
		 String nick;
		 int edad;
		 String posicion_jugador;
		 String cadena="";
		 int codigo;
		 String respuesta;
	        if (rs != null) {
	        	//Mostramos en primer lugar a todos los jugadores con su codigo y su nombre
	            System.out.println("El listado de jugadores es:");
	 
	            while (rs.next()) {
	                System.out.println("  Datos del jugador: "
	                        + rs.getObject("cod_jugador") + " "
	                        + rs.getObject("Nombre"));
	                System.out.println("- ");
	            }
	            cerrar(rs);
	        }
	        //Pedimos al usuario a quien desea modificar mediante su codigo
		 System.out.print("¿Que jugador desea modificar?(Introduce su codigo)");
		 codigo = scan.nextInt();
		 //Le preguntamos que campo desea modificar
			 System.out.print("¿Que desea modificar?-->1-Nombre, 2-Apellidos, 3-Nick, 4-Edad, 5-Posicion del jugador");
			 respuesta = scan.next();
			 switch (respuesta) {
			 //Modificamos el nombre del jugador
			case "1":
				 System.out.print("Introduce un nuevo Nombre");
				  nombre = scan.next();
				cadena="UPDATE Jugador SET nombre='"+nombre+"'WHERE cod_jugador='"+codigo+"'";
				break;
				 //Modificamos el apellido del jugador
			case "2":
				 System.out.print("Introduce un nuevo Apellido");
				  apellidos = scan.next();
				cadena="UPDATE Jugador SET apellidos='"+apellidos+"'WHERE cod_jugador='"+codigo+"'";
				break;
				 //Modificamos el nick del jugador
			case "3":
				 System.out.print("Introduce un nuevo Nick");
				  nick = scan.next();
				cadena="UPDATE Jugador SET nick='"+nick+"'WHERE cod_jugador='"+codigo+"'";
				break;
			case "4":
				 //Modificamos la edad del jugador
				 System.out.print("Introduce un nueva Edad");
				  edad = scan.nextInt();
				cadena="UPDATE Jugador SET apellidos='"+edad+"'WHERE cod_jugador='"+codigo+"'";
				break;
			case "5":
				 //Modificamos la posicion del jugador
				 System.out.print("Introduce una nueva posicion del jugador ");
				 posicion_jugador = scan.next();
				cadena="UPDATE Jugador SET apellidos='"+posicion_jugador+"'WHERE cod_jugador='"+codigo+"'";
				break;
			default:
				//En caso de meter otro numero da error
				System.out.print("ERROR");
				break;
			}//Aqui comprobamos si existe dicho jugador
			 int  aux = consultaActualiza(st, cadena);
			 if (aux==0) {
				 System.out.print("No se ha encontrado al jugador");
			 }else{
				 System.out.println("Se ha modificado el jugador con exito");
				 System.out.print(" ");
			 }
			 
		
    }
    /**
     * Metodo para borrar a un jugador
     * Se necesita llamar a consultaActualiza para poder hacer este metodo.
     * @param st
     */
    public static void BorrarJugador(Statement st,ResultSet rs){
    	try{
    		Scanner scan = new Scanner(System.in);
    		int codigo;
    		String cadena;
	        if (rs != null) {
	            System.out.println("El listado de jugadores es:");
	 
	            while (rs.next()) {
	                System.out.println("  Datos del jugador: "
	                        + rs.getObject("cod_jugador") + " "
	                        + rs.getObject("Nombre"));
	                System.out.println("- ");
	            }
	            cerrar(rs);
	        }
    		//Le pedimos al usuario el codigo del jugador que desea borrar
    		 System.out.print("¿Que jugador desea borrar?");
		        codigo = scan.nextInt();
    		//Esta es la sentencia de SQL que hacemos para borrar a un jugador
    			cadena="DELETE FROM Jugador WHERE cod_jugador='"+codigo+"'";
    			//Aqui comprobamos si existe dicho jugador
    		 if(consultaActualiza(st, cadena)==0){
    			 System.out.print("No se ha encontrado al jugador");
    		}else{
    		 System.out.print("Se ha eliminado el jugador correctamente");
    		 System.out.print(" ");
    		}
    		 
    	}catch(Exception e){
    		 System.out.print("Error: No es posible borrar al jugador.");
    	}
    }
    
    /**
     * Metodo para borrar a un equipo
     * Se necesita llamar a consultaActualiza para poder hacer este metodo.
     * @param st
     */
    public static void BorrarEquipo(Statement st){
    	try{
    		Scanner scan = new Scanner(System.in);
    		int codigo;
    		String cadena;
    		//Le pedimos al usuario el codigo del equipo que desea borrar
    		 System.out.print("¿Que equipo desea borrar?");
		        codigo = scan.nextInt();
    		//Esta es la sentencia de SQL que hacemos para borrar a un equipo
    			cadena="DELETE FROM equipo WHERE cod_equipo='"+codigo+"'";
    			//Aqui comprobamos si existe dicho equipo
    		 if(consultaActualiza(st, cadena)==0){
    			 System.out.print("No se ha encontrado al equipo");
    		}else{
    		 System.out.print("Se ha eliminado al equipo correctamente");
    		 System.out.print(" ");
    		}
    		 
    	}catch(Exception e){
    		 System.out.print("Error: No es posible borrar al equipo.");
    	}
    }
    
    
    /**
     * Metodo que hace una consulta tipo SELECT.
     * Se llama al metodo consultaQuery para poder hacer esta consulta.
     * @param st
     */
    public static void ConsultaJugador(Statement st){

    	try{	
    		String cadena;
    		//Esta es la consulta que hacemos.
        	cadena="SELECT * FROM Jugador WHERE posicion_jugador IN ('mid','top') AND edad BETWEEN 18 AND 20";
            
                ResultSet rs = consultaQuery(st, cadena);
                
                if (rs != null) {
                    System.out.println("La consulta es:");
                  //Mostramos los datos de la tabla con la consulta echa anteriormente.
	                while (rs.next()) {
	                    System.out.println("  cod_jugador: " + rs.getObject("cod_jugador"));
	                    System.out.println("  Datos del jugador: "
	                            + rs.getObject("nombre") + " "
	                            + rs.getObject("apellidos")+ " "
	                            +rs.getObject("edad"));
	     
	                    System.out.println("  En el juego: " + " Nick" +" "+rs.getObject("nick")
	                            + " " +"Posicion" +" "+rs.getObject("posicion_jugador"));
	     
	                    System.out.println("- ");
                }
                cerrar(rs);
            }
           			
    	}catch(Exception e){
    		 System.out.print("Error: No es posible hacer la consulta.");
    	}
    	
    }
    /**
     * Metodo que hace una consulta tipo SELECT donde el codigo es menor de 7 y agrupa el nombre descendentemente
     * Se llama al metodo consultaQuery para poder hacer esta consulta
     * @param st
     */
    public static void ConsultaJugador2(Statement st){
    	try{
        	String cadena;
        	//Esta es la consulta que hacemos.
        	cadena="SELECT * FROM jugador WHERE cod_jugador<=7  GROUP BY nombre DESC";
        	 ResultSet rs = consultaQuery(st, cadena);
            if (rs != null) {
                System.out.println("La consulta es:");
                //Mostramos los datos de la tabla con la consulta echa anteriormente.
                while (rs.next()) {
                    System.out.println("  cod_jugador: " + rs.getObject("cod_jugador"));
                    System.out.println("  Datos del jugador: "
                            + rs.getObject("nombre") + " "
                            + rs.getObject("apellidos")+ " "
                            +rs.getObject("edad"));
     
                    System.out.println("  En el juego: " + " Nick" +" "+rs.getObject("nick")
                            + " " +"Posicion" +" "+rs.getObject("posicion_jugador"));
     
                    System.out.println("- ");
            }
            cerrar(rs);
        }
    	}catch(Exception e){
    		System.out.print("Error: No es posible cerrar la consulta.");
    	}

    }
    
    /**
     * Metodo para sumar +3 o +1 a un equipo
     *
     * @param st
     * @param rs
     * @throws SQLException
     */
    public static void ModifPuntos(Statement st,ResultSet rs)throws SQLException{
    	Scanner scan = new Scanner(System.in);
    	String cadena ="";
    	int codigo;
    	int respuesta;
    	//Se declara un ResultSet
    	//Se muestra a los equipos con su codigo y sus puntos.
        if (rs != null) {
            System.out.println("El listado de equipos es:");
 
            while (rs.next()) {
                System.out.println("  Datos del equipo: "
                        + rs.getObject("cod_equipo") + " "
                        + rs.getObject("puntos"));
                System.out.println("- ");
            }
            cerrar(rs);
        }
        //Se le pide al usuario que equipo desea modificar
		 System.out.print("¿Que equipo desea modificar?(Introduce codigo del equipo)");
        codigo = scan.nextInt();
        //Se le pide al usuario si el equipo ha ganado o ha empatado para sumar +3 o +1
			 System.out.print("Introduce 1-Gana, 2-Empata");
			 respuesta = scan.nextInt();
			 int numEntero=0;
			 //Se declara un ResultSet para guardar la primera consulta con la llamada a ConsultaQuery
			 switch (respuesta) {
			case 1:
				cadena="SELECT * FROM equipo WHERE cod_equipo='"+codigo+"'";
				ResultSet rs1 = consultaQuery(st, cadena);
				//extrae de rs 
	            while (rs1.next()) {
	            	//Se pasa a entero la cadena anterior
	            	numEntero = (Integer) rs1.getObject("puntos");
	                
	            }
	            cerrar(rs1);
				 //Se declara la segunda cadena para sumar los puntos al equipo 
				 numEntero=numEntero+3;
				cadena="UPDATE equipo SET puntos='"+numEntero+"'WHERE cod_equipo='"+codigo+"'";
				break;
			case 2:
				cadena="SELECT * FROM equipo WHERE cod_equipo='"+codigo+"'";
				ResultSet rs2 = consultaQuery(st, cadena);
				//extrae de rs 
	            while (rs2.next()) {
	            	//Se pasa a entero la cadena anterior
	            	numEntero = (Integer) rs2.getObject("puntos");    
	            }
	            cerrar(rs2);
	            //Se declara la segunda cadena para empatar los puntos al equipo 
				 numEntero=numEntero+1;
				cadena="UPDATE equipo SET puntos='"+numEntero+"'WHERE cod_equipo='"+codigo+"'";
				break;
			default:
				 System.out.print("ERROR. Codigo no válido");
				break;			 
		 };
		if(consultaActualiza(st, cadena)==0){
			 System.out.print("No se ha encontrado al equipo");
		}else{
		 System.out.print("Se han modificado los puntos correctamente");
		 System.out.print(" ");
		}
    }
    
   
    /**
     * Metodo para clasificar todos los equipos por puntos, de mayor puntuación a menos.
     * @param st
     */
    public static void Clasificacion(Statement st){
    	try{
        	String cadena;
        	//Esta es la consulta que hacemos.
        	cadena="SELECT * FROM equipo  GROUP BY puntos DESC";
        	 ResultSet rs = consultaQuery(st, cadena);
            if (rs != null) {
                System.out.println("La consulta es:");
                //Mostramos los datos de la tabla con la consulta echa anteriormente.
                while (rs.next()) {
                    System.out.println("  Datos del equipo: "
                            + rs.getObject("cod_equipo") + " "
                            + rs.getObject("nombre_equipo")+" "
                            + rs.getObject("puntos")+" ");
                    System.out.println("- ");
            }
            cerrar(rs);
        }
            
    	}catch(Exception e){
    		System.out.print("Error: No es posible cerrar la consulta.");
    	}
    }
    /**
     * Método para cerrar la consulta.
     * @param rs
     */
    private static void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la consulta.");
            }
        }

    }
    /**
     * Método para cerrar la conexión con la base de datos.
     * @param st
     */
    private static void cerrar(java.sql.Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la conexión.");
            }
        }
    }
}
