package com.example.roomcompleto;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOCliente {
    @Query("SELECT * FROM cliente")
    List<Cliente> devolverClientes();

    @Insert
    void insertarCliente(Cliente c);
}
