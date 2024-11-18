package com.document_microservice.services;

import com.document_microservice.DTOS.CreditDTO;
import com.document_microservice.DTOS.DocumentDTO;
import com.document_microservice.entities.Document;
import com.document_microservice.repositories.DocumentRepository;
import com.document_microservice.utils.ToDTO;
import com.document_microservice.client.creditClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ToDTO toDTO; // Inyectar la clase de utilidades
    private final creditClient creditClient;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ToDTO toDTO, creditClient creditClient) {
        this.documentRepository = documentRepository;
        this.toDTO = toDTO; // Inyectar la instancia de ToDTO
        this.creditClient = creditClient;
    }

    public Document saveDocument(MultipartFile file, String typeCredit, UUID credit_id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<CreditDTO> creditOptional = creditClient.getCreditById(credit_id);

        if (creditOptional.isPresent()) {
            CreditDTO credit = creditOptional.get();
            Document document = Document.builder()
                    .documentName(fileName)
                    .documentType(file.getContentType())
                    .data(file.getBytes())
                    .typeCreditDocument(typeCredit)
                    .creditId(credit.getId())
                    .build();

            return documentRepository.save(document);
        }

        throw new RuntimeException("Credit not found with id: " + credit_id);
    }

    @Transactional
    public Optional<Document> getFile(UUID id) throws FileNotFoundException {
        Optional<Document> file = documentRepository.findById(id);
        if (file.isPresent()) {
            return file;
        }
        throw new FileNotFoundException();
    }

    // Método para obtener todos los documentos como DTOs
    public List<DocumentDTO> getAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        return documents.stream()
                .map(toDTO::convertToDocumentDTO) // Utilizar el método del servicio inyectado
                .collect(Collectors.toList());
    }

//    Feign services

    public List<DocumentDTO> getAllDocumentsByCreditId(UUID credit_id) {
        Optional<List<Document>> document = documentRepository.findAllByCreditId(credit_id);
        if (document.isPresent()) {
            List<Document> documents = documentRepository.findAll();
            return documents.stream()
                    .map(toDTO::convertToDocumentDTO)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
