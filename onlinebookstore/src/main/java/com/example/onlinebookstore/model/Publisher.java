package com.example.onlinebookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "publishers")
@Data
public class Publisher {

    @Id
    private String id;
    private String publisherId;
    private String name;
}
