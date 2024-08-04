/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataObjects;

/**
 *
 * @author JiaHong
 */
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String location;

    public UserDTO(){}
    public UserDTO(String name, String email,
            String password, String userType, String location) {
            this.email = email;
            this.location = location;
            this.name = name;
            this.password = password;
            this.userType = userType;
    }

    public UserDTO(int id, String name, String email, String password, String userType, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.location = location;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getLocation() {
        return location;
    }
    
}
