package com.example.roomcompleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEmail;
    private Button btnMostrar;
    private Button btnGrabar;
    private RecyclerView clientesRv;
    private ClientListAdapter clientListAdapter;
    private List<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        btnGrabar = findViewById(R.id.btnGrabar);
        btnMostrar = findViewById(R.id.btnMostrar);

        clientesRv = findViewById(R.id.recycler_view);
        clientListAdapter = new ClientListAdapter();

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();
                Cliente c = new Cliente(nombre, email);
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        insertarCliente(c);
                    }
                });
                hilo.start();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mostrarClientes();
                    }
                });
                hilo.start();
                try {
                    hilo.join();

                    clientListAdapter.setClientes(listaClientes);
                    clientesRv.setAdapter(clientListAdapter);
                    clientesRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    clientesRv.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void insertarCliente(Cliente c) {
        MiDatabase db = Room.databaseBuilder(this, MiDatabase.class, "bd_contactos").build();
        DAOCliente miDAO = db.devolverDAO();

        miDAO.insertarCliente(c);
    }

    private void mostrarClientes() {
        MiDatabase db = Room.databaseBuilder(this, MiDatabase.class, "bd_contactos").build();
        DAOCliente miDAO = db.devolverDAO();

        listaClientes = miDAO.devolverClientes();

        Log.d("CLIENTES", listaClientes.toString());
    }
}