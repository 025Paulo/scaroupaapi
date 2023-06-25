package com.example.demo.Model.Entity;

import com.example.demo.Service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToMany
    @JoinTable(name = "pedido_cliente",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private List<Cliente> clientes;
}
