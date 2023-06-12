package com.example.demo.Service;

import com.example.demo.Model.Entity.Pedido;
import com.example.demo.Model.Entity.Produto;
import com.example.demo.Model.Repository.ProdutoRepository;
import com.example.demo.api.Exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {
    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> getProdutos() {
        return repository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Produto salvar(Produto produto) {
        validar(produto);
        return repository.save(produto);
    }

    @Transactional
    public void excluir(Produto produto) {
        Objects.requireNonNull(produto.getId());
        repository.delete(produto);
    }

    public void validar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }
}
