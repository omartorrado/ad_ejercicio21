package ad_ejercicio21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlManager {

    public static Connection conexion = null;
    
    public void setConexion() {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost.localdomain";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;
        try {
            conexion = DriverManager.getConnection(ulrjdbc);
        } catch (SQLException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] leerNombreTablas(){
        String[] resultado=null;
        try {            
            int contador=0;
            //hacemos es resultSet scroll insensitive para poder contar el numero de resultados y luego volver al principio
           Statement listaTablas=conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=listaTablas.executeQuery("select table_name from user_tables");
            //Get row count
            rs.last();
            resultado=new String[rs.getRow()];
            rs.beforeFirst();
            //guardamos los resultados en el array que vamos a devolver
            while(rs.next()){
                resultado[contador]=rs.getString(1);
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public void leerTablaMeta(String tabla){
        try {
            Statement selectTabla=conexion.createStatement();
            ResultSetMetaData datosTabla=selectTabla.executeQuery("select * from "+tabla).getMetaData();
            for(int i=1;i<=datosTabla.getColumnCount();i++){
                System.out.println(datosTabla.getColumnName(i)+", "+datosTabla.getColumnTypeName(i)+", "+datosTabla.getColumnDisplaySize(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void leerBaseDeDatos(){
        String [] listaTabla=leerNombreTablas();
        for(String s:listaTabla){
            System.out.println("Tabla "+s+":");
            leerTablaMeta(s);
            System.out.println("\n");
        }
    }
}
