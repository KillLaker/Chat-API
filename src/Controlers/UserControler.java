package Controlers;

import Model.User;
import Service.UserService;

import java.util.List;

public class UserControler {
    private final UserService _userService;

    public UserControler(UserService _userService){
        this._userService = _userService;
    }

    public void createUser(String name, String username, String email, String password){
        this._userService.createUser(name, username, email, password);
    }

    public void deleteUser(String name, String username, String email, String password){
        this._userService.deleteUser(name, username, email, password);
    }

    public void updateUserName(String username, String newName){
        this._userService.updateName(username, newName);
    }
    public void updateUserUsername(String username, String newUsername){
        this._userService.updateUsername(username, newUsername);
    }

    public void updateUserEmail(String username, String newEmail){
        this._userService.updateEmail(username, newEmail);
    }

    public void updateUserPassword(String username, String newPassword){
        this._userService.updatePassword(username, newPassword);
    }

    public User getUser(String username){
        return this._userService.getUser(username);
    }

    public List<User> getUsers(){
        return this._userService.getUsers();
    }
}
