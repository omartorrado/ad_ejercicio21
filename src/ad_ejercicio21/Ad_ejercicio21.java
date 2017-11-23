/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_ejercicio21;

/**
 *
 * @author oracle
 */
public class Ad_ejercicio21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SqlManager sql=new SqlManager();
        sql.setConexion();
        sql.leerBaseDeDatos();        
    }
    
}
