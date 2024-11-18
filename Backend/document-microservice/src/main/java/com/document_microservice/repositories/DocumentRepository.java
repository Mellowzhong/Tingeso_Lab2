package com.document_microservice.repositories;

import com.document_microservice.DTOS.CreditDTO;
import com.document_microservice.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<List<Document>> findAllByCreditId(UUID creditId);
}
