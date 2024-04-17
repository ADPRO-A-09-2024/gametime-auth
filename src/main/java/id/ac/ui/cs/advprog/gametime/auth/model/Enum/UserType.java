package id.ac.ui.cs.advprog.gametime.auth.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
    SELLER("SELLER"),
    BUYER("BUYER");
    private final String value;

    private UserType(String value){
        this.value = value;
    }

    public static  boolean contains(String param){
        for (UserType userType : UserType.values()){
            if (userType.name().equals(param)){
                return true;
            }
        }
        return false;
    }

    public static List<String> getAll(){
        List<String> types = new ArrayList<>();
        for (UserType userType : UserType.values()){
            types.add(userType.name());
        }
        return types;
    }

}