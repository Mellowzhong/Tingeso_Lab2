package com.utils_microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    private UUID id;

    private String typeCreditDocument;

    private String documentName;

    private String documentType;

    private byte[] data;

    private Credit credit;
}
