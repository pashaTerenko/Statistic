package terenko.statistic.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserDTO {

    String id;
    String nickname;
    String name;
    String lastname;
    String avatarURL;

    public UserDTO() {
    }

    public UserDTO(String id, String nickname, String name, String lastname,String Avatar) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.avatarURL=Avatar;
    }

}
