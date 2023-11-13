/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caio
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO 
{
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto)
    {    
        conn = new conectaDAO().connectDB();
        try 
        {
            prep = conn.prepareStatement("insert into Produtos (nome, valor, status) values (?,?,?)");
            //prep.setInt(1, produto.getId());
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public ArrayList<ProdutosDTO> listarVendidos()
    {
        conn = new conectaDAO().connectDB();
        try 
        { 
            prep = conn.prepareStatement("select * from Produtos where status = ?");
            prep.setString(1, "Vendido");
            resultset = prep.executeQuery();
            
            while(resultset.next())
            {  
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            System.out.println(listagem);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao acessar dados");
        }
        return listagem;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos()
    {
        conn = new conectaDAO().connectDB();
        try 
        { 
            prep = conn.prepareStatement("select * from Produtos");
            resultset = prep.executeQuery();
            
            while(resultset.next())
            {  
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            System.out.println(listagem);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao acessar dados");
        }
        return listagem;
    }
    
    public void venderProduto(int i)
    {
        conn = new conectaDAO().connectDB();
        try 
        {
            prep = conn.prepareStatement("update Produtos set status = ? where id = ?");
            prep.setString(1, "Vendido");
            prep.setInt(2, i);
            prep.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados");
        }
    }
    
    
    
        
}

