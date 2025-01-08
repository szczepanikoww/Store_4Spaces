package store;

import javax.security.auth.login.LoginException;

abstract class User {
    private int UserID;
    private String UserName;
    private String UserPassword;

    public User(int userID, String userName, String userPassword) {
        this.UserID = userID;
        this.UserName = userName;
        this.UserPassword = userPassword;
    }

    //getters
    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    //other metohods
    public boolean Login(String LoginName, String LoginPassword) throws LoginException {
        if (!this.UserName.equals(LoginName)) {
            throw new LoginException("Invalid username");
        }else if (!this.UserPassword.equals(LoginPassword)) {
            throw new LoginException("Invalid password");
        }
        return this.UserName.equals(LoginName) && this.UserPassword.equals(LoginPassword);
    }
}
