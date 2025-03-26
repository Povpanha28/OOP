package Entity.Sales;

public class Membership {

    private int membershipID;
    private String membershipCode;
    private String name;
    private String phone;
    private String address;

    // Constructor for creating a new membership (without membershipID, as it is auto-incremented)
    public Membership(String membershipCode, String name, String phone, String address) {
        this.membershipCode = membershipCode;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters for all fields
    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public String getMembershipCode() {
        return membershipCode;
    }

    public void setMembershipCode(String membershipCode) {
        this.membershipCode = membershipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

