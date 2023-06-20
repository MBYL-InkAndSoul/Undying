package inkandsoul.crafttech.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AbstractCauldronBlock.class)
public interface AbstractCauldronBlockAccessor {
	@Invoker("isEntityInsideContent")
	public boolean isEntityTouchingFluid(BlockState state, BlockPos pos, Entity entity);
}
