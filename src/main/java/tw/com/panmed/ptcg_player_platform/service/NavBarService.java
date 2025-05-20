package tw.com.panmed.ptcg_player_platform.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.wnameless.apt.INamedResource;
import com.github.wnameless.spring.boot.up.permission.PermittedUser;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import net.sf.rubycollect4j.Ruby;

@Service("navBar")
public class NavBarService {

  public static final String RESOURCE = "RESOURCE";
  public static final String RESOURC_PATH = "RESOURCE_PATH";
  public static final String QUERY_STRING = "QUERY_STRING";

  public static final String GROUP_NAME = "GROUP_NAME";
  public static final String GROUP_ORDER = "GROUP_ORDER";
  public static final String ITEM_NAME = "ITEM_NAME";
  public static final String ITEM_ORDER = "ITEM_ORDER";
  public static final String ITEM_ICON = "ITEM_ICON";
  public static final String ITEM_HEADING = "ITEM_HEADING";
  public static final String ITEM_HEADING_ICON = "ITEM_HEADING_ICON";

  @Autowired
  List<INamedResource> namedResources;

  @SneakyThrows
  public Map<String, List<NavBarCollapsableItem>> getGroupItems(PermittedUser<?> user) {
    var groupCollapsableItems = new LinkedHashMap<String, List<NavBarCollapsableItem>>();

    var navItems = new ArrayList<NavBarItem>();
    for (var nr : namedResources) {
      String resourceName = nr.getClass().getDeclaredField(RESOURCE).get(nr).toString();
      String groupName = nr.getClass().getDeclaredField(GROUP_NAME).get(nr).toString();
      int groupOrder = (int) nr.getClass().getDeclaredField(GROUP_ORDER).get(nr);
      String itemName = nr.getClass().getDeclaredField(ITEM_NAME).get(nr).toString();
      int itemOrder = (int) nr.getClass().getDeclaredField(ITEM_ORDER).get(nr);
      String itemHref = nr.getClass().getDeclaredField(RESOURC_PATH).get(nr).toString();
      String itemIcon = nr.getClass().getDeclaredField(ITEM_ICON).get(nr).toString();

      var builder = NavBarItem.builder().groupName(groupName).groupOrder(groupOrder)
          .itemName(itemName).itemOrder(itemOrder).itemHref(itemHref).itemIcon(itemIcon)
          .resourceName(resourceName);

      boolean hasItemHeading = Arrays.asList(nr.getClass().getDeclaredFields()).stream()
          .anyMatch(f -> f.getName().equals(ITEM_HEADING));
      if (hasItemHeading) {
        String itemHeading = nr.getClass().getDeclaredField(ITEM_HEADING).get(nr).toString();
        builder.itemHeading(itemHeading);
      }
      boolean hasItemHeadingIcon = Arrays.asList(nr.getClass().getDeclaredFields()).stream()
          .anyMatch(f -> f.getName().equals(ITEM_HEADING_ICON));
      if (hasItemHeadingIcon) {
        String itemHeadingIcon =
            nr.getClass().getDeclaredField(ITEM_HEADING_ICON).get(nr).toString();
        builder.itemHeadingIcon(itemHeadingIcon);
      }

      var navItem = builder.build();
      navItems.add(navItem);
    }

    Ruby.Array.of(navItems).sortByǃ(item -> item.getItemOrder())
        .sortByǃ(item -> item.getGroupOrder());
    navItems.forEach(item -> {
      if (user != null && user.existsPermission(item.getResourceName())
          && !user.canRead(item.getResourceName())) {
        return;
      }

      if (groupCollapsableItems.get(item.getGroupName()) == null) {
        groupCollapsableItems.put(item.getGroupName(), new ArrayList<>());
      }
      if (!item.isNestedItem()) {
        groupCollapsableItems.get(item.getGroupName()).add(new NavBarCollapsableItem(item));
      } else {
        var ciOpt = groupCollapsableItems.get(item.getGroupName()).stream()
            .filter(ci -> item.getItemHeading().equals(ci.getHeading())).findAny();
        if (ciOpt.isPresent()) {
          ciOpt.get().getNestedItems().add(item);
        } else {
          if (!item.isNestedItem()) {
            groupCollapsableItems.get(item.getGroupName()).add(new NavBarCollapsableItem(item));
          } else {
            groupCollapsableItems.get(item.getGroupName()).add(
                new NavBarCollapsableItem(item.getItemHeading(), item.getItemHeadingIcon(), item));
          }
        }
      }
    });

    return groupCollapsableItems;
  }

  @Data
  public class NavBarCollapsableItem {

    private NavBarItem item;

    private final String heading;

    private final String headingIcon;

    private List<NavBarItem> nestedItems = new ArrayList<>();

    public NavBarCollapsableItem(NavBarItem navBarItem) {
      heading = navBarItem.getItemName();
      headingIcon = "";
      item = navBarItem;
    }

    public NavBarCollapsableItem(String heading, String headingIcon, NavBarItem navBarItem) {
      this.heading = heading;
      this.headingIcon = headingIcon;
      nestedItems.add(navBarItem);
    }

    public boolean hasChildren() {
      return !nestedItems.isEmpty();
    }

    public boolean anyHrefContainsPath(String path) {
      return nestedItems.stream().anyMatch(ni -> ni.getItemHref().contains(path));
    }

  }

  @Builder
  @Data
  public static class NavBarItem {

    private final String resourceName;

    private final String groupName;

    private final int groupOrder;

    private final int itemOrder;

    private final String itemName;

    private final String itemHref;

    private final String itemIcon;

    private String itemHeading;

    private String itemHeadingIcon;

    public boolean isNestedItem() {
      return itemHeading != null;
    }

  }

}
