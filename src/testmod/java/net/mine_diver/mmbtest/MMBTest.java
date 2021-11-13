package net.mine_diver.mmbtest;

import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false, registerStatic = false))
public class MMBTest {

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();
}
