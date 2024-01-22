package com.example.onlinebookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
@Data
public class Author {

    @Id
    private String id;
    private String authorId;
    private String name;
    private List<String> booksWritten;
}
