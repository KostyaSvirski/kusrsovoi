package org.kursovoi.server.repository;

import org.kursovoi.server.model.LoanOrder;
import org.kursovoi.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanOrderRepository extends JpaRepository<LoanOrder, Long> {
    List<LoanOrder> findByUser(User user);

}
