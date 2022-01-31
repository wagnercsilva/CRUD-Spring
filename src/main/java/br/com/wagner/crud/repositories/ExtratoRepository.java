package br.com.wagner.crud.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wagner.crud.models.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato, Integer> {
    List<Extrato> findByDataAndConta(Date data, String conta);
}
