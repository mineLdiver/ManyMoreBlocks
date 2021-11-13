package net.mine_diver.mmbtest.item;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ColouredItem extends TemplateItemBase {

    private final int colour;

    public ColouredItem(int id, int colour) {
        super(id);
        this.colour = colour;
        setTexturePosition(0, 1);
    }

    @Override
    public int getNameColour(int i) {
        return colour;
    }
}
