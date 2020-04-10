package br.com.bancopan.exam.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancopan.exam.persistence.entity.ClienteEntity;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {

	Optional<ClienteEntity> findByCpf(String cpf);
	
}
