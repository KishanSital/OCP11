package mypackage.models;

public class UserModel extends Object {

    private Long userId;
    private String username;
    private char[] password;

    public UserModel(Long userId, String username, char[] password) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserModel(UserModel userModel) {
        super();
        this.userId = userModel.getUserId();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
    }

    public UserModel() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[]  getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userId = " + userId +
                ", username = " + username +
                ", password = " + String.valueOf(password);
    }
}