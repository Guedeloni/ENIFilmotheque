package fr.eni.filmotheque.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class Membre {
    @Id
    @NotBlank               private String  login;
    @NotBlank @Size(min=8)  private String  password;
                            private String  avatar;
                            private boolean admin;

    public Membre(String login, String password, boolean admin) {
        this.login      = login;
        this.password   = password;
        this.admin      = admin;
    }

    public Membre(String login, String password, String avatar, boolean admin) {
        this(login, password, admin);
        this.avatar     = avatar;
    }
}
