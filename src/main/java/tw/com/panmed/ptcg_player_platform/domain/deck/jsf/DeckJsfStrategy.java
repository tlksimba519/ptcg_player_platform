package tw.com.panmed.ptcg_player_platform.domain.deck.jsf;

import java.util.Map;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.wnameless.spring.boot.up.jsf.JsfStrategy;
import com.github.wnameless.spring.boot.up.jsf.JsonSchemaForm;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import net.sf.rubycollect4j.Ruby;
import tw.com.panmed.ptcg_player_platform.domain.card.CardRepository;
import tw.com.panmed.ptcg_player_platform.domain.deck.Deck;

@Component
public class DeckJsfStrategy implements JsfStrategy<Deck> {

  @Autowired
  CardRepository cardRepository;

  @Override
  public Class<Deck> getDocumentType() {
    return Deck.class;
  }

  @Override
  public BiFunction<Deck, JsonSchemaForm, Map<String, Object>> schemaStrategy() {
    return (caller, jsf) -> {
      DocumentContext docCtx = JsonPath.parse(jsf.getSchema());

      var cards = Ruby.Enumerator.of(cardRepository.findAll())
          .toH(e -> Ruby.Entry.of(e.getId(), e.getCardName()));
      docCtx.put("$.properties.cards.items", "enum", cards.keys());
      docCtx.put("$.properties.cards.items", "enumNames", cards.values());

      return docCtx.read("$", new TypeRef<Map<String, Object>>() {});
    };
  }

  @Override
  public BiFunction<Deck, JsonSchemaForm, Map<String, Object>> uiSchemaStrategy() {
    return (caller, jsf) -> {
      DocumentContext docCtx = JsonPath.parse(jsf.getUiSchema());

      docCtx.put("$.cards", "ui:widget", "checkboxes");

      // var cards = Ruby.Enumerator.of(cardRepository.findAll())
      // .toH(e -> Ruby.Entry.of(e.getId(), e.getCardName()));
      // docCtx.put("$.properties.cards.items", "enum", cards.keys());
      // docCtx.put("$.properties.cards.items", "enumNames", cards.values());

      return docCtx.read("$", new TypeRef<Map<String, Object>>() {});
    };
  }

  @Override
  public BiFunction<Deck, JsonSchemaForm, Map<String, Object>> formDataStrategy() {
    return null;
  }

}
