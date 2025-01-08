package store;

abstract class User {
    private int UserID;
    private String UserName;
    private String UserPassword;

    public User(int userID, String userName, String userPassword) {
        this.UserID = userID;
        this.UserName = userName;
        this.UserPassword = userPassword;
    }

    public boolean Login(String LoginName, String LoginPassword) {
        return this.UserName.equals(LoginName) && this.UserPassword.equals(LoginPassword);
    }
}
