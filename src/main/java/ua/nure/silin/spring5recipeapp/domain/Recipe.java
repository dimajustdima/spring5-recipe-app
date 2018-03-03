package ua.nure.silin.spring5recipeapp.domain;

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

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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

        public RecipeBuilder withImage(Byte[] image) {
            this.image = image;
            return this;
        }

        public RecipeBuilder withNotes(Notes notes) {
            this.notes = notes;
            return this;
        }

        public RecipeBuilder ofDifficulty(Difficulty difficulty) {
            this.notes = notes;
            return this;
        }

        public RecipeBuilder ofCategories(Set<Category> categories) {
            this.categories.addAll(categories);
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
