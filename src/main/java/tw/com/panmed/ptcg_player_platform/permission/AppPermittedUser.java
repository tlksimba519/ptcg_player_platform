package tw.com.panmed.ptcg_player_platform.permission;

import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import com.github.wnameless.spring.boot.up.permission.AbstractPermittedUser;
import com.github.wnameless.spring.boot.up.permission.ability.ResourceAbility;
import com.github.wnameless.spring.boot.up.permission.role.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import tw.com.panmed.ptcg_player_platform.domain.userstatus.UserStatus;
import tw.com.panmed.ptcg_player_platform.domain.userstatus.UserStatusService;

@RequestScope
@Component("user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppPermittedUser extends AbstractPermittedUser<String> {

  @Autowired
  AppWebPermissionManager permissionManager;

  @Autowired
  UserStatusService userStatusService;

  @Override
  public Set<Role> getUserRoles() {
    return permissionManager.getUserRoles();
  }

  @Override
  protected Set<ResourceAbility> getUserResourceAbilities() {
    return permissionManager.getUserResourceAbilities();
  }

  @Override
  public Map<String, Set<String>> getUserMetadata() {
    return permissionManager.getUserMetadata();
  }

  public Set<Role> getAllUserRoles() {
    return permissionManager.findAllRolesByUsername(getUsername());
  }

  public UserStatus getUserStatus() {
    return userStatusService.findOrCreateUserStatus(getUsername());
  }

}
