package id.ac.ui.cs.advprog.gametime.auth.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum UserRole {
    SELLER("SELLER"),
    BUYER("BUYER");
    private final String value;

    private UserRole(String value){
        this.value = value;
    }

    public static boolean contains(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

}