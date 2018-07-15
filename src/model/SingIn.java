package model;

import java.io.Serializable;

public class SingIn extends Total implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickName;
    private String password;
    private String email;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
