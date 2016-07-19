package crudsample.domain;

/**
 * Created by Cepro on 16.07.2016.
 */
public enum Role {
    USER("Пользователь"), ADMIN("Администратор");
    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return getRole();
    }
}
