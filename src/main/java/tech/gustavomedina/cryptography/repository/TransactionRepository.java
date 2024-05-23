package tech.gustavomedina.cryptography.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.gustavomedina.cryptography.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
