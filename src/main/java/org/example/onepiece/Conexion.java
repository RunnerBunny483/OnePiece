package org.example.onepiece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase Conexion establece y gestiona la conexión con una base de datos MariaDB.
 * La conexión se configura automáticamente con un usuario y contraseña dependiendo del sistema operativo.
 * La base de datos utilizada en este ejemplo se llama "OnePiece" y se ejecuta en localhost en el puerto 3306.
 *
 * @author Marcos Perez Aceituno
 * @Version 1.0
 */

public class Conexion {
    //Se establecen los parámetros de acceso a la BD
    private static String os;
    private static String usuario = "root";
    private static String passwd;
    private static final String URL = "jdbc:mariadb://localhost:3306/OnePiece";

    private static Connection conexion = null;

    /**
     * Constructor de la clase Conexion.
     * Establece la conexión a la base de datos MariaDB con los parámetros proporcionados.
     *
     * @throws RuntimeException Si ocurre un error al establecer la conexión.
     */
    //Constructor de la clase JDBC:
    public Conexion() {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            setOS();
            setPasswd();
            conexion =  DriverManager.getConnection(URL, usuario, passwd); //Conexión a la BD
        } catch (ClassNotFoundException | SQLException ex){
            throw new RuntimeException("Error en la conexión " + ex.getMessage());
        }
    }

    /**
     * Obtiene la instancia de la conexión a la base de datos.
     * Si la conexión aún no se ha establecido, se crea una nueva instancia.
     *
     * @return La conexión activa la base de datos.
     */
    //Al hacer getConexión comprobará si se ha creado o no, si no se había creado invoca el constructor JDBC
    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }

    /**
     * Comprueba el nombre del sistema operativo ya que algunos miembros del trabajo usan windows y otros linux
     */
    //Obtenemos el SO de la máquina que ejecuta el programa
    private void setOS(){
        os = System.getProperty("os.name");
    }

    /**
     * Establece la contraseña para la conexión con la base de datos según el sistema operativo.
     * En windows sin contraseña.
     * En los linus de clase la contraseña es "a".
     */
    //La contraseña dependerá del SO (en los de sobremesa linux es "a", en los Windows suele ser "")
    private void setPasswd(){
        if(os.contains("Windows"))
            passwd = "";
        else if (os.contains("Linux") || os.contains("Unix"))
            passwd = "a";
    }

    /**
     * Establece el nombre de usuario para la conexión a la base de datos.
     *
     * @param usuario El nuevo nombre de usuario para la conexión.
     */
    public static void setUsuario(String usuario) {
        Conexion.usuario = usuario;
    }
}
