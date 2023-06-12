package com.example.demo.Service;

import com.example.demo.Model.Entity.Cliente;
import com.example.demo.Model.Repository.ClienteRepository;
import com.example.demo.api.Exception.RegraNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) { this.repository = repository; }

    public List<Cliente> getCliente() { return repository.findAll(); }
    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
    }


    public Cliente salvar(Cliente cliente) {
        validar(cliente);
        return repository.save(cliente);
    }

    private void validar(Cliente cliente) {
        if (cliente.getPedido() == null || cliente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }

    public void excluir(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        repository.delete(cliente);
    }
}
