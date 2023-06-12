package com.example.demo.Model.Entity;

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
public class Fornecedor extends Pessoa {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToOne
    private Produto produto;

    @JsonIgnore
    @OneToMany (mappedBy = "fornecedor")
    private List<Pedido> pedidos;
}

