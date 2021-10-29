package com.vi.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectorBD {
    private static Connection connection=null;
    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }else {
            try {
                Properties properties=new Properties();
                properties.load(new FileInputStream("bd.properties"));
                //
                String driver=properties.getProperty("driver");
                String url=properties.getProperty("url");
                String user=properties.getProperty("user");
                String password=properties.getProperty("password");
                //carga driver
                Class.forName(driver);
                //
                connection= DriverManager.getConnection(url,user,password);
            } catch (IOException e) {
                System.out.println("Error en el archivo de propiedades:"+e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar driver:"+e.getMessage());
            } catch (SQLException throwables) {
                System.out.println("Error en la conexion a la BD:"+throwables.getMessage());
            }
        }
        return connection;
    }
}
