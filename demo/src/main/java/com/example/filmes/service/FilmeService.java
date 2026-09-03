package com.example.filmes.service;

import com.example.filmes.model.Filme;
import com.example.filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public Filme salvar(Filme filme) {
        return repository.save(filme);
    }

    public List<Filme> listarTodos() {
        return repository.findAll();
    }

    public Optional<Filme> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Filme> atualizar(Long id, Filme filmeAtualizado) {
        return repository.findById(id).map(filmeExistente -> {
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
            filmeExistente.setDiretor(filmeAtualizado.getDiretor());
            filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filmeExistente.setGenero(filmeAtualizado.getGenero());
            return repository.save(filmeExistente);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}