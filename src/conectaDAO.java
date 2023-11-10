
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caio
 */
public class conectaDAO {
    
    public Connection connectDB()
    {
        
        Connection conn = null;
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "password");
            
        } 
        catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro jdbc driver");
        }
        
        return conn;
    }
    
}
