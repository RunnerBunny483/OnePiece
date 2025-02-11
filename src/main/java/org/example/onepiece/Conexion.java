package org.example.onepiece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Se establecen los parámetros de acceso a la BD
    private static String os;
    private static String usuario = "root";
    private static String passwd;
    private static final String URL = "jdbc:mariadb://localhost:3306/OnePiece";

    private static Connection conexion = null;

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

    //Al hacer getConexión comprobará si se ha creado o no, si no se había creado invoca el constructor JDBC
    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }

    //Obtenemos el SO de la máquina que ejecuta el programa
    private void setOS(){
        os = System.getProperty("os.name");
    }

    //La contraseña dependerá del SO (en los de sobremesa linux es "a", en los Windows suele ser "")
    private void setPasswd(){
        if(os.contains("Windows"))
            passwd = "";
        else if (os.contains("Linux") || os.contains("Unix"))
            passwd = "a";
    }

    public static void setUsuario(String usuario) {
        Conexion.usuario = usuario;
    }
}
