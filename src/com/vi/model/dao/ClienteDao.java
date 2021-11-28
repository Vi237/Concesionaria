package com.vi.model.dao;

import com.vi.model.Cliente;
import com.vi.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    Connection connection;


    public ClienteDao() {
        connection = ConectorBD.getConnection();
    }

    //Create
    public void addCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO clinete (nif,nombre,ciudad,direccion,telefono) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, cliente.getNif());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getCiudad());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setInt(5, cliente.getTelefono());
            preparedStatement.executeUpdate(); //////
            System.out.println(cliente + "creado");

        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente \n" + e.getMessage());
        }

    }

    //edit
    public void updateCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE cliente SET nif=?,nombre=?,ciudad=?,direccion=?,telefono=? WHERE nif=?");

            preparedStatement.setString(1, cliente.getNif());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getCiudad());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setInt(5, cliente.getTelefono());

            preparedStatement.executeUpdate();////
            System.out.println(cliente + "Editado");
        } catch (SQLException e) {
            System.out.println("Error al editar cliente" + e);
        }

    }

    //delete
    public void deleteCliente(String nif) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM cliente WHERE nif=?");

            preparedStatement.setString(1, nif);
            preparedStatement.executeUpdate();////

            System.out.println(nif + "eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente" + e);
        }

    }

    //read
    public List<Cliente> getALLClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetn = statement.executeQuery("SELECT * FROM clientes");
            while (resultSetn.next()) {
                Cliente cliente = new Cliente();
                cliente.setNif(resultSetn.getString("nif"));
                cliente.setNombre(resultSetn.getString("nombre"));
                cliente.setCiudad(resultSetn.getString("ciudad"));
                cliente.setDireccion(resultSetn.getString("direccion"));
                cliente.setTelefono(resultSetn.getInt("telefono"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los clientes " + e.getMessage());
        }
        return clientes;
    }

}