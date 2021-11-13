package net.mine_diver.mmbtest.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.*;

import static net.mine_diver.mmbtest.MMBTest.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class Blocks {

    @EventListener
    private static void registerBlocks(BlockRegistryEvent event) {
        int blocksAmount = 2000;
        BLOCKS = new TemplateBlockBase[blocksAmount];
        Random random = new Random(42);
        for (int i = 0; i < blocksAmount; i++)
            BLOCKS[i] = new ColouredBlock(of(MODID, "test_block_" + i), Material.DIRT, random.nextInt()).setHardness(0.8F).setSounds(BlockBase.GLASS_SOUNDS).setTranslationKey(MODID, "testBlock" + i);
    }
    
    public static TemplateBlockBase[] BLOCKS;
}
