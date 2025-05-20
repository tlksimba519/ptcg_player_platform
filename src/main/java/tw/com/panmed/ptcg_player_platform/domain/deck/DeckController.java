package tw.com.panmed.ptcg_player_platform.domain.deck;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.github.wnameless.spring.boot.up.web.AjaxRestfulWebAction;
import com.github.wnameless.spring.boot.up.web.HtmlRestfulWebAction;
import com.github.wnameless.spring.boot.up.web.ModelAttributes.Page;
import com.github.wnameless.spring.boot.up.web.ModelPolicy;
import com.github.wnameless.spring.boot.up.web.QuickRestfulController;
import tw.com.panmed.ptcg_player_platform.model.jsondata.Deck.DeckSchemaData;

@RequestMapping(NRDeck.RESOURCE_PATH)
@Controller
public class DeckController extends QuickRestfulController<DeckRepository, Deck, String> implements
    HtmlRestfulWebAction<DeckSchemaData, String>, AjaxRestfulWebAction<DeckSchemaData, String> {

  @Override
  protected void quickConfigure(ModelPolicy<Deck> policy) {}

  @Override
  public void indexAction(ModelAndView mav, MultiValueMap<String, String> params) {
    mav.addObject(Page.name(), itemRepository.findAll(predicate, pageable));
  }

  @Override
  public void showAction(String id, ModelAndView mav, MultiValueMap<String, String> params) {}

  @Override
  public void newAction(ModelAndView mav, MultiValueMap<String, String> params) {}

  @Override
  public void createAction(ModelAndView mav, MultiValueMap<String, String> params,
      DeckSchemaData data) {
    item.setPojoWithPopulation(data);
    itemRepository.save(item);
  }

  @Override
  public void editAction(String id, ModelAndView mav, MultiValueMap<String, String> params) {}

  @Override
  public void updateAction(String id, ModelAndView mav, MultiValueMap<String, String> params,
      DeckSchemaData data) {
    item.setPojoWithPopulation(data);
    itemRepository.save(item);
  }

  @Override
  public void deleteAction(String id, ModelAndView mav, MultiValueMap<String, String> params) {
    itemRepository.deleteById(id);
    mav.addObject(Page.name(), itemRepository.findAll(predicate, pageable));
  }

}
