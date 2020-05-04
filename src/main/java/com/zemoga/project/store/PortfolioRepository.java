package com.zemoga.project.store;

import com.zemoga.project.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class to store portfolio data
 */
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
}
