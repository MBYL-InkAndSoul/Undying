package inkandsoul.crafttech;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface Washable {
	public static final Event<Washable> EVENT = EventFactory.createEventResult(Washable.class);

	public static void setFluidAmount(BlockState state, int value){
		state.setValue(LayeredCauldronBlock.LEVEL, value);
	}

	public static void shrinkFluidAmount(BlockState state, int value){
		state.setValue(LayeredCauldronBlock.LEVEL, state.getValue(LayeredCauldronBlock.LEVEL)-value);
	}

	public static boolean cauldronFluidIsWater(BlockState state){
		return BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().equals("water_cauldron");
	}

	public static boolean cauldronFluidIsPowderSnow(BlockState state){
		return BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().equals("powder_snow_cauldron");
	}

	public static boolean cauldronFluidIsLava(BlockState state){
		return BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().equals("lava_cauldron");
	}

	// public static Fluid getCauldronFluid(BlockState state){
	// 	return state.getV
	// }

	ItemStack wasWashed(Level world, BlockState state, BlockPos pos, ItemStack stack);
}
