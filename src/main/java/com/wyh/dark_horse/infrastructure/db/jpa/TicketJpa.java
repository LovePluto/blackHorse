package com.wyh.dark_horse.infrastructure.db.jpa;

import com.wyh.dark_horse.infrastructure.db.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketJpa extends JpaRepository<TicketEntity, String> {
}
