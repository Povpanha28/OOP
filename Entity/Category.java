package Entity;

public class Category {
    // Instance variables (Private for encapsulation, belong to an object)
    private int categoryID;
    private String categoryName;
    private String description;
    private int parentCategoryID;
    private int supplierID;

    private static int categoryCount = 0;

    // Constructor 
    public Category(int categoryId, String categoryName, String description, int parentCategoryId, int supplierID) {
        this.categoryID = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryID = parentCategoryId;
        this.supplierID = supplierID;
        categoryCount++;
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

    // Static method to get category count
    public static int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    // Method with local variable scope
    public void displayCategoryInfo() {
        String info = "Category: " + categoryName + " (ID: " + categoryID + ")"; // Local variable
        System.out.println(info);
    }


}
