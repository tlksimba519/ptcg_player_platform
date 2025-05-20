package tw.com.panmed.ptcg_player_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

  @RequestMapping
  String index() {
    return "redirect:/decks";
  }

}
