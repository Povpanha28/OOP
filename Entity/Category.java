package Entity;

public class Category {
    // Instance variables (Private for encapsulation, belong to an object)
    private static int categoryID = 0;
    private String categoryName;
    private String description;
    private int parentCategoryID;
    private int supplierID;


    // Constructor 
    public Category(String categoryName, String description, int parentCategoryId, int supplierID) {
        categoryID++;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryID = parentCategoryId;
        this.supplierID = supplierID;
    }

    // Getter methods
    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public int getParentCategoryID() {
        return parentCategoryID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }


}
