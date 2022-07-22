package webtaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
