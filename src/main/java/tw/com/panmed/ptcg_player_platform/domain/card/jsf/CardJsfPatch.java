package tw.com.panmed.ptcg_player_platform.domain.card.jsf;

import java.util.Map;
import java.util.function.Function;
import com.github.wnameless.spring.boot.up.jsf.JsonSchemaForm;
import com.github.wnameless.spring.boot.up.jsf.WebActionJsfPatch;
import tw.com.panmed.ptcg_player_platform.model.jsondata.Card.CardSchemaData;

public interface CardJsfPatch extends WebActionJsfPatch<CardSchemaData, String> {

  @Override
  default Function<? super JsonSchemaForm, Map<String, Object>> newActionUiSchemaPatch() {
    return jsf -> {
      var uiSchema = jsf.getUiSchema();

      uiSchema.put("img", Map.of("ui:widget", "imageWidget"));

      return uiSchema;
    };
  }

  @Override
  default Function<? super JsonSchemaForm, Map<String, Object>> showActionUiSchemaPatch() {
    return jsf -> {
      var uiSchema = jsf.getUiSchema();

      uiSchema.put("img", Map.of("ui:widget", "imageWidget"));

      return uiSchema;
    };
  }
}
