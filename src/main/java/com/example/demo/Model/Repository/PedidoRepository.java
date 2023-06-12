package com.example.demo.Model.Repository;

import com.example.demo.Model.Entity.Pedido;
import com.example.demo.Model.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByProduto(Optional<Produto> produto);
}
