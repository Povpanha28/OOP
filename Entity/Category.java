package Entity;

public class Category {
    int categoryID;
    String categoryName;
    String description;
    int parentCategoryID;
    int supplierID;

    public Category(int categoryId, String categoryName, String description, int parentCategoryId, int supplierID) {
        this.categoryID = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryID = parentCategoryId;
        this.supplierID = supplierID;
    }
}
