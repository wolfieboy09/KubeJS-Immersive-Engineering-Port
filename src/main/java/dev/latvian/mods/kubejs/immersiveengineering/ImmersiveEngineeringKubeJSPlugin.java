package dev.latvian.mods.kubejs.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.*;
import blusunrize.immersiveengineering.api.energy.ThermoelectricSource;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.util.RecipeSerializers;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.immersiveengineering.event.IEEvents;
import dev.latvian.mods.kubejs.immersiveengineering.event.MultiblockFormEventJS;
import dev.latvian.mods.kubejs.immersiveengineering.recipe.*;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ImmersiveEngineeringKubeJSPlugin extends KubeJSPlugin {
	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.addListener(ImmersiveEngineeringKubeJSPlugin::onMultiblockForm);
	}

	@Override
	public void registerEvents() {
		IEEvents.GROUP.register();
	}

	@Override
	public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
		event.registerShaped(RecipeSerializers.TURN_AND_COPY_SERIALIZER.getId());

		event.register(AlloyRecipe.SERIALIZER.getId(), AlloyRecipeJS::new);
		event.register(ArcFurnaceRecipe.SERIALIZER.getId(), ArcFurnaceRecipeJS::new);
		event.register(BlastFurnaceFuel.SERIALIZER.getId(), BlastFurnaceFuelRecipeJS::new);
		event.register(BlastFurnaceRecipe.SERIALIZER.getId(), BlastFurnaceRecipeJS::new);
		event.register(BlueprintCraftingRecipe.SERIALIZER.getId(), BlueprintCraftingRecipeJS::new);
		event.register(BottlingMachineRecipe.SERIALIZER.getId(), BottlingMachineRecipeJS::new);
		event.register(ClocheFertilizer.SERIALIZER.getId(), ClocheFertilizerRecipeJS::new);
		event.register(ClocheRecipe.SERIALIZER.getId(), ClocheRecipeJS::new);
		event.register(CokeOvenRecipe.SERIALIZER.getId(), CokeOvenRecipeJS::new);
		event.register(CrusherRecipe.SERIALIZER.getId(), CrusherRecipeJS::new);
		//event.register("immersiveengineering:fermenter", FermenterRecipeSerializer::new);
		event.register(MetalPressRecipe.SERIALIZER.getId(), MetalPressRecipeJS::new);
		//event.register("immersiveengineering:mineral_mix", MineralMixSerializer::new);
		event.register(MixerRecipe.SERIALIZER.getId(), MixerRecipeJS::new);
		//event.register("immersiveengineering:refinery", RefineryRecipeSerializer::new);
		event.register(SawmillRecipe.SERIALIZER.getId(), SawmillRecipeJS::new);
		event.register(SqueezerRecipe.SERIALIZER.getId(), SqueezerRecipeJS::new);
		event.register(ThermoelectricSource.SERIALIZER.getId(), ThermoelectricSourceJS::new);

		//event.register("immersivepetroleum:distillation", DistillationRecipeSerializer::new);
		//event.register("immersivepetroleum:reservoirs", ReservoirTypeSerializer::new);
	}

	@SubscribeEvent
	public static void onMultiblockForm(MultiblockHandler.MultiblockFormEvent event) {
		if (IEEvents.MULTIBLOCK_FORM.post(event.getMultiblock().getUniqueName(),new MultiblockFormEventJS(event))) {
			event.setCanceled(true);
		}
	}
}
