package ua.nure.silin.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;

    public UnitOfMeasure() {
    }

    public UnitOfMeasure(String description) {
        this.description = description;
    }

}
