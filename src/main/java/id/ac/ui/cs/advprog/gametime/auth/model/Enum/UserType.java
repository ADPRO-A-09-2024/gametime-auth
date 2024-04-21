package id.ac.ui.cs.advprog.gametime.auth.model.Enum;

public enum UserType {
    PENJUAL("PENJUAL"),
    PEMBELI("PEMBELI");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        for (UserType type : UserType.values()) {
            if (type.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}