package ua.nure.silin.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@EqualsAndHashCode(exclude = "recipes")
@ToString(exclude = "recipes")
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

    public Category() {}

    public Category(String description) {
        this.description = description;
    }

    public Category(String description, Set<Recipe> recipes) {
        this.description = description;
        this.recipes = recipes;
    }

}
