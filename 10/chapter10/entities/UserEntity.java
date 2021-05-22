package chapter10.entities;

public class UserEntity extends Object {

    private Long userId;
    private String username;
    private String password;

    public UserEntity(Long userId, String username, String password) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserEntity(UserEntity userEntity) {
        super();
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
    }

    public UserEntity() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userId = " + userId +
                ", username = " + username +
                ", password = " + password;
    }
}