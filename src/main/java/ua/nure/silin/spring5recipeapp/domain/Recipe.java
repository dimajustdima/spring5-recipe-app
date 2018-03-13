package ua.nure.silin.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @OneToMany(cascade = ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @Lob
    private Byte[] image;
    @OneToOne(cascade = ALL)
    private Notes notes;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    private Recipe(RecipeBuilder builder) {
        this.description = builder.description;
        this.prepTime = builder.prepTime;
        this.cookTime = builder.cookTime;
        this.servings = builder.servings;
        this.source = builder.source;
        this.url = builder.url;
        this.directions = builder.directions;
        this.ingredients = builder.ingredients;
        this.image = builder.image;
        this.notes = builder.notes;
        this.difficulty = builder.difficulty;
        this.categories = builder.categories;
    }


    public static class RecipeBuilder {

        private String description;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String source;
        private String url;
        private String directions;
        private Set<Ingredient> ingredients = new HashSet<>();
        private Byte[] image;
        private Notes notes;
        private Difficulty difficulty;
        private Set<Category> categories = new HashSet<>();

        public RecipeBuilder(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String directions) {
            this.description = description;
            this.prepTime = prepTime;
            this.cookTime = cookTime;
            this.servings = servings;
            this.source = source;
            this.directions = directions;
        }

        public RecipeBuilder fromIngredients(Set<Ingredient> ingredients) {
            this.ingredients.addAll(ingredients);
            return this;
        }

        public RecipeBuilder fromIngredients(Ingredient ...ingredients) {
            this.ingredients.addAll(asList(ingredients));
            return this;
        }

        public RecipeBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public RecipeBuilder withImage(Byte[] image) {
            this.image = image;
            return this;
        }

        public RecipeBuilder withNotes(Notes notes) {
            this.notes = notes;
            return this;
        }

        public RecipeBuilder ofDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public RecipeBuilder ofCategories(Set<Category> categories) {
            this.categories.addAll(categories);
            return this;
        }

        public RecipeBuilder ofCategories(Category ...categories) {
            this.categories.addAll(asList(categories));
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
