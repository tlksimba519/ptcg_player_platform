package tw.com.panmed.ptcg_player_platform.domain.userstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.com.panmed.ptcg_player_platform.permission.AppPermittedUser;

@RequestMapping(NRUserStatus.RESOURCE_PATH)
@Controller
public class UserStatusController {

    @Autowired
    AppPermittedUser user;
    @Autowired
    UserStatusService userStatusService;

    @PostMapping("/{id}")
    String updateHtml(Model model, @RequestParam("role") String role) {
        userStatusService.updateRole(user.getUsername(), role);
        return "redirect:/";
    }

}
