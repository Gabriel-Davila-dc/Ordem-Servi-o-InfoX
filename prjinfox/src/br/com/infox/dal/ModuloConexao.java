/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.infox.dal;

import java.sql.*;
import javax.swing.JOptionPane;



public class ModuloConexao {
    
public static Connection conector() throws ClassNotFoundException{

     Connection conexao = null;
     
     //metodo para chamar o driver
     String driver = "com.mysql.cj.jdbc.Driver";
     String url = "jdbc:mysql://127.0.0.1:3306/dbinfox?characterEncoding=utf-8";
     String user = "dba";
     String password = "infox@123456";
    
        try {
                        
       // var url = "jdbc:mysql://127.0.0.1:3306/dbinfox?user=root&password=ganhar";    
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, user, password);
       // conexao = DriverManager.getConnection(url);
        }  
        catch (SQLException e) {
            
           conexao = null;
        }
        
        return conexao;  
}
    
}
