package com.example.finalse.repositories;

import com.example.finalse.entities.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
}
