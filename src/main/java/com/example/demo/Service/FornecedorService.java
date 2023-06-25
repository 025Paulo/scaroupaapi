package com.example.demo.Service;

import com.example.demo.Model.Entity.Fornecedor;
import com.example.demo.Model.Entity.Pedido;
import com.example.demo.Model.Entity.Produto;
import com.example.demo.Model.Repository.FornecedorRepository;
import com.example.demo.api.Exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FornecedorService {

    private FornecedorRepository repository;
    private final PedidoService pedidoService;



    public FornecedorService(FornecedorRepository repository, PedidoService pedidoService ) {
        this.repository = repository;
        this.pedidoService = pedidoService;
    }

    public List<Fornecedor> getFornecedores() {
        return repository.findAll();
    }

    public Optional<Fornecedor> getFornecedorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Fornecedor salvar(Fornecedor fornecedor) {
        validar(fornecedor);
        return repository.save(fornecedor);
    }

    @Transactional
    public void excluir(Fornecedor fornecedor) {
        Objects.requireNonNull(fornecedor.getId());
        for (Pedido pedido : fornecedor.getPedidos()) {
            pedido.setFornecedor(null);
            pedidoService.salvar(pedido);
        }
        repository.delete(fornecedor);
    }

    public void validar(Fornecedor fornecedor) {
        if (fornecedor.getNome() == null || fornecedor.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (fornecedor.getProduto() == null || fornecedor.getProduto().getId() == null || fornecedor.getProduto().getId() == 0) {
            throw new RegraNegocioException("Produto inválido");
        }
    }
}
