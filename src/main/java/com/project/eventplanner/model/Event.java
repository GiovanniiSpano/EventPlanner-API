package com.project.eventplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Inserire titolo")
    @Size(min = 5, max = 20, message = "Il titolo deve contenere tra 5 e 20 caratteri")
    private String title;

    @NotBlank(message = "Inserire descrizione")
    @Size(min = 20, message = "La descrizione deve contenere almeno 20 caratteri")
    private String description;

}
