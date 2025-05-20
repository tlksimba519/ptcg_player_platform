package tw.com.panmed.ptcg_player_platform.permission.role;

import java.util.Set;
import java.util.function.BooleanSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.wnameless.spring.boot.up.permission.ability.CanCRUD;
import com.github.wnameless.spring.boot.up.permission.ability.CanRead;
import com.github.wnameless.spring.boot.up.permission.role.ConditionalRoleEnum;
import com.github.wnameless.spring.boot.up.permission.role.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import tw.com.panmed.ptcg_player_platform.domain.card.CardResource;
import tw.com.panmed.ptcg_player_platform.domain.deck.DeckResource;
import tw.com.panmed.ptcg_player_platform.domain.userstatus.UserStatusService;
import tw.com.panmed.ptcg_player_platform.permission.group.AppRole;
import tw.com.panmed.ptcg_player_platform.permission.group.PTCGRole;

@CanRead({DeckResource.class})
@CanCRUD({CardResource.class})
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PTCGStaff implements ConditionalRoleEnum<PTCGRole> {

    @Autowired
    UserStatusService userStatusService;

    @Override
    public Set<Role> getMinorRoles() {
        return Set.of(AppRole.BASIC_USER.toRole());
    }

    @Override
    public PTCGRole getRoleEnum() {
        return PTCGRole.PTCG_STAFF;
    }

    @Override
    public BooleanSupplier getCondition() {
        return () -> userStatusService.isCurrentUserRoleActive(toRole());
    }

}
