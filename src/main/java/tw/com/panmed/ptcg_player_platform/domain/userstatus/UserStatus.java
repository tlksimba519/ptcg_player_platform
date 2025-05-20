package tw.com.panmed.ptcg_player_platform.domain.userstatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.apt.NamedResource;
import com.github.wnameless.spring.boot.up.permission.role.Role;
import com.github.wnameless.spring.boot.up.web.RestfulItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@NamedResource
@Document
@EqualsAndHashCode(of = "id")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStatus implements RestfulItem<String> {

    @Id
    String id;

    @Indexed(unique = true)
    String username;

    Role role;

}
