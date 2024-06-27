package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String description;
    private String ISBN;
    private String photoLink;
    private int pages;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
