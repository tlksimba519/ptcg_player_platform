package tw.com.panmed.ptcg_player_platform.permission;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.github.wnameless.spring.boot.up.permission.WebPermissionManagerAdapter;
import com.github.wnameless.spring.boot.up.permission.role.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import tw.com.panmed.ptcg_player_platform.AppConfig;
import tw.com.panmed.ptcg_player_platform.permission.group.AppRole;
import tw.com.panmed.ptcg_player_platform.permission.group.PTCGRole;

@Component("webPerm")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppWebPermissionManager extends WebPermissionManagerAdapter<String> {

  @Override
  public Set<Role> findAllRolesByUsername(String username) {
    var roles = new LinkedHashSet<Role>();

    if (AppConfig.SYS_ADMIN_USERNAMES.contains(username)) {
      roles.add(AppRole.SYS_ADMIN.toRole());
    }
    roles.addAll(Arrays.asList(PTCGRole.values()).stream().map(PTCGRole::toRole).toList());



    return roles;
  }

}
