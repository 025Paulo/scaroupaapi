package com.example.demo.Service;

import com.example.demo.Model.Entity.Pedido;
import com.example.demo.Model.Entity.Produto;
import com.example.demo.Model.Repository.PedidoRepository;
import com.example.demo.api.Exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> getPedidos() {return repository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id)  {return repository.findById(id);
    }

    public List<Pedido> getPedidosByProduto(Optional<Produto> produto) {
        return repository.findByProduto(produto);
    }


    @Transactional
    public Pedido salvar(Pedido pedido) {
        validar(pedido);
        return repository.save(pedido);
    }

    @Transactional
    public void excluir(Pedido pedido) {
        Objects.requireNonNull(pedido.getId());
        repository.delete(pedido);
    }

    public void validar(Pedido pedido) {
        if (pedido.getNome() == null || pedido.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }

}


