package tw.com.panmed.ptcg_player_platform.permission.group;

import com.github.wnameless.spring.boot.up.SpringBootUp;
import com.github.wnameless.spring.boot.up.permission.role.Rolify;
import com.github.wnameless.spring.boot.up.web.LocalizationName;

public enum PTCGRole implements Rolify, LocalizationName {

  PLAYER, PTCG_STAFF;

  @Override
  public String getRoleName() {
    return name();
  }

  @Override
  public String getI18nDisplayName() {
    return SpringBootUp.getMessage(name(), new Object[] {}, name());
  }

  @Override
  public String toString() {
    return getI18nDisplayName();
  }

}
