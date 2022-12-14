package org.kursovoi.server.repository;

import org.kursovoi.server.model.LoanOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanOrderRepository extends JpaRepository<LoanOrder, Long> {
}
