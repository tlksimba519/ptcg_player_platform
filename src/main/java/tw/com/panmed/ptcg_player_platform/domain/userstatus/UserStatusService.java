package tw.com.panmed.ptcg_player_platform.domain.userstatus;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.github.wnameless.spring.boot.up.permission.role.Role;
import tw.com.panmed.ptcg_player_platform.permission.AppWebPermissionManager;

@Service
public class UserStatusService {

    @Autowired
    UserStatusRepository userStatusRepository;
    @Autowired
    AppWebPermissionManager appWebPermissionManager;

    public UserStatus updateRole(String username, String roleName) {
        var userStatus = findOrCreateUserStatus(username);

        var roles = appWebPermissionManager.findAllRolesByUsername(username);
        var roleOpt =
                roles.stream().filter(r -> Objects.equals(r.getRoleName(), roleName)).findAny();
        if (roleOpt.isPresent()) {
            userStatus.setRole(roleOpt.get());
            return userStatusRepository.save(userStatus);
        }

        return userStatus;
    }

    public UserStatus findOrCreateUserStatus(String username) {
        var userStatusOpt =
                userStatusRepository.findOne(QUserStatus.userStatus.username.eq(username));
        if (userStatusOpt.isPresent()) {
            initUserStatus(userStatusOpt.get());
            return userStatusOpt.get();
        }

        var userStatus = new UserStatus();
        userStatus.setUsername(username);
        initUserStatus(userStatus);
        return userStatusRepository.save(userStatus);
    }

    private void initUserStatus(UserStatus userStatus) {
        var username = userStatus.getUsername();
        if (username == null)
            return;

        var roles = appWebPermissionManager.findAllRolesByUsername(username);
        var role = userStatus.getRole();
        if (!roles.contains(role)) {
            if (!roles.isEmpty()) {
                userStatus.setRole(roles.iterator().next());
            } else {
                userStatus.setRole(null);
            }
        }
    }

    public boolean isUserRoleActive(String username, Role role) {
        var userStatus = findOrCreateUserStatus(username);
        return Objects.equals(userStatus.getRole(), role);
    }

    public boolean isCurrentUserRoleActive(Role role) {
        return isUserRoleActive(SecurityContextHolder.getContext().getAuthentication().getName(),
                role);
    }

}
