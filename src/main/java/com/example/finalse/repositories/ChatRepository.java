package com.example.finalse.repositories;

import com.example.finalse.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
