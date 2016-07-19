package crudsample.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Cepro on 15.07.2016.
 */
@Entity
public class User {
    private int id;
    private String name;
    private Integer age;

    private Role isAdmin;
    private Timestamp createDate;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        createDate = new Timestamp(System.currentTimeMillis());
        isAdmin = Role.USER;
    }

    public User() {
        createDate = new Timestamp(System.currentTimeMillis());
        isAdmin = Role.USER;
    }

    public void updateDate() {
        createDate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    @NotNull(message = "Необходимо ввести имя!")
    @Size(min = 3, max = 25, message = "Длина имени должна быть от 3 до 25 символов!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    @Min(value = 16, message = "Возраст должен быть от 16 до 65 лет!")
    @Max(value = 65, message = "Возраст должен быть от 16 до 65 лет!")
    @NotNull(message = "Возраст должен быть от 16 до 65 лет!")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = (age != null) ? age : 0;
    }

    @Column(name = "isAdmin")
    @Enumerated(EnumType.ORDINAL)
    public Role getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Role admin) {
        isAdmin = admin;
    }

    @Basic
    @Column(name = "createDate")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (isAdmin != user.isAdmin) return false;
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
