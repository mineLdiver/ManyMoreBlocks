package net.mine_diver.mmbtest.item;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.util.*;

import static net.mine_diver.mmbtest.MMBTest.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class Items {

    @EventListener
    private static void reigsterItems(ItemRegistryEvent event) {
        int itemAmount = 5000;
        ITEMS = new TemplateItemBase[itemAmount];
        Random random = new Random(42);
        for (int i = 0; i < itemAmount; i++)
            ITEMS[i] = new ColouredItem(29000 + i/*of(MODID, "test_item_" + i)*/, random.nextInt()).setTranslationKey(MODID, "testItem" + i);
    }

    public static TemplateItemBase[] ITEMS;
}
