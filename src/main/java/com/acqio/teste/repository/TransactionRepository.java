package com.acqio.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acqio.teste.model.TransactionVO;

public interface TransactionRepository extends JpaRepository<TransactionVO, Long>{

}
