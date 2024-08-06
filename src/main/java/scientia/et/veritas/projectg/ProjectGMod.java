package scientia.et.veritas.projectg;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("projectg")
public class ProjectGMod {

    public static final String MOD_ID = "projectg";
    public static final Logger LOGGER = LoggerFactory.getLogger(ProjectGMod.class);

    public ProjectGMod(IEventBus bus) {
        bus.addListener(FMLClientSetupEvent.class, (fmlClientSetupEvent -> {
            fmlClientSetupEvent.enqueueWork( () -> {
                ModList.get().getModContainerById(MOD_ID).ifPresent(modContainer -> {
                    LOGGER.info("Project G is loaded and ready! , currently at vesrion: {}", modContainer.getModInfo().getVersion());
                });
            });
        }));
    }
}
