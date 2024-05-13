/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author anaqu
 */
public class ConectorBD {
    
    private Connection c;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    public PreparedStatement getPrepared(String sql) throws SQLException{
        c = getConnection();
        return c.prepareStatement(sql);
    }
    
    public ResultSet getSelect(String sql)throws SQLException{
        PreparedStatement ps = getPrepared(sql);
        rs = ps.executeQuery();
        return rs;
    }
    
    // Métodos close sobrecarregados
    
    public static void close(Connection c){
        if(c != null){
            try {
                c.close();
            } catch (SQLException excep){
                excep.printStackTrace();
            }
        }
    }
    
    public static void close (PreparedStatement ps){
        if(ps != null){
            try{
                ps.close();
            } catch(SQLException excep){
                excep.printStackTrace();
            }
            
        }
    }
    
    public static void close (ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch(SQLException excep){
                excep.printStackTrace();
            }
            
        }
    }
    
    
}
