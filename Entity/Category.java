package Entity;

public class Category {
    int categoryID;
    String categoryName;
    String description;
    int parentCategoryID;

    public Category(int categoryId, String categoryName, String description, int parentCategoryId) {
        this.categoryID = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryID = parentCategoryId;
    }
}
