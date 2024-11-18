package com.document_microservice.controllers;

import com.document_microservice.DTOS.DocumentDTO;
import com.document_microservice.entities.Document;
import com.document_microservice.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    private DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/post/{credit_id}")
    public ResponseEntity<String> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("typeCredit") String doc,
            @PathVariable("credit_id") UUID creditId // Especifica el nombre del parámetro aquí
    ) throws IOException {
        Document savedDocument = documentService.saveDocument(file, doc, creditId);
        return ResponseEntity.ok("Document uploaded successfully. ID: " + savedDocument.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") UUID id) throws FileNotFoundException {
        Document fileEntity = documentService.getFile(id).orElseThrow(FileNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, fileEntity.getDocumentType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getDocumentName() + "\"")
                .body(fileEntity.getData());
    }

//    Feign controllers
    @GetMapping("/get/{creditId}")
    public List<DocumentDTO> getAllDocumentsByCreditId(@PathVariable("creditId") UUID creditId) {
        return documentService.getAllDocumentsByCreditId(creditId);
    }
}
