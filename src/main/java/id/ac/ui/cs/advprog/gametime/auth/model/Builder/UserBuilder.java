package id.ac.ui.cs.advprog.gametime.auth.model.Builder;


import id.ac.ui.cs.advprog.gametime.auth.model.Enum.UserType;
import id.ac.ui.cs.advprog.gametime.auth.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {
    private UserEntity currentUser;
    public UserBuilder(){
        this.reset();
    }

    public UserBuilder reset(){
        currentUser = new UserEntity();
        return this;
    }

    public UserBuilder addUsername(String username){
        currentUser.setUsername(username);
        return this;
    }

    public UserBuilder addEmail(String email){
        currentUser.setEmail(email);
        return this;
    }

    public UserBuilder addPassword(String password){
        currentUser.setPassword(password);
        return this;
    }

    public UserBuilder addType(String type){
        if (UserType.contains(type)){
            currentUser.setType(type);
            return this;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public UserBuilder setCurrent(UserEntity user){
        currentUser = user;
        return this;
    }

    public UserEntity build(){
        return currentUser;
    }
}