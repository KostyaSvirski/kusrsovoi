package org.kursovoi.server.repository;

import org.kursovoi.server.model.DepositOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositOrderRepository extends JpaRepository<DepositOrder, Long> {
}
