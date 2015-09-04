package br.edu.ifg.formosa.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
        	return DriverManager.getConnection("jdbc:postgresql://10.8.230.9:5432/SiDIF", "postgres", "IFG9469");
//        	return DriverManager.getConnection("jdbc:postgresql://localhost:5433/SiDIFG", "postgres", "hamerski");
        	
            /*
             * if(=ADM){
             * 		 return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbTeste", "administrador", "1349237590");
             * }
             * else{REC
             * 		 return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbTeste", "usuario", "Ákajlkdsnf");
             * }
             */
        } catch (Exception e) {
            throw new RuntimeException("falha ao tentar acessar o BD. Verifique sua conex√£o"+e.getMessage());
        }
    }
    
    
}
