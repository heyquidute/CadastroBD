/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anaqu
 */
public class SequenceManager {

    public SequenceManager() {}
    
    public static int getValue(String sequenceName){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nextValue = -1;
        
        try{
            c = ConectorBD.getConnection();
            String sql = "SELECT NEXT VALUE FOR " + sequenceName + " AS next_value";
            ps = c.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                nextValue = rs.getInt("next_value");
            }
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally{
            ConectorBD.close(c);
            ConectorBD.close(ps);
            ConectorBD.close(rs);
        }
        return nextValue;
    }
    
}
