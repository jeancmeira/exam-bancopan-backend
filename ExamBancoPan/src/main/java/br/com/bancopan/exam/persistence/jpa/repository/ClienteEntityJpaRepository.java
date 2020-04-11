package br.com.bancopan.exam.persistence.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancopan.exam.persistence.jpa.entity.ClienteEntity;

/**
 * 
 * @author Jean
 *
 *Classe de repositorio da entidade de clientes
 *
 */
@Repository
public interface ClienteEntityJpaRepository extends JpaRepository<ClienteEntity, String> {

	Optional<ClienteEntity> findByCpf(String cpf);
	
}
