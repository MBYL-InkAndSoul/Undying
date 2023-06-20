package inkandsoul.iteminfo.mixin;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.architectury.registry.fuel.FuelRegistry;
import inkandsoul.iteminfo.ModConfig;
import inkandsoul.iteminfo.util.NumberUtil;
import inkandsoul.iteminfo.util.TimeUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "getTooltipLines", at = @At("RETURN"))
	public void defaultTooltip(@Nullable Player player, TooltipFlag context,
			CallbackInfoReturnable<List<Component>> info) {
		var stack = (ItemStack) (Object) this;
		var tooltip = info.getReturnValue();
	
		Item item = stack.getItem();
		var id = BuiltInRegistries.ITEM.getKey(item).toString();

		if (ModConfig.MORE_TOOLTIPS_LITERAL.containsKey(id)){
			ModConfig.MORE_TOOLTIPS_LITERAL.get(id).forEach(
				i->tooltip.add(Component.literal(i))
			);
		}
		if (ModConfig.MORE_TOOLTIPS_TRANSLATABLE.containsKey(id)){
			ModConfig.MORE_TOOLTIPS_TRANSLATABLE.get(id).forEach(
				i->tooltip.add(Component.translatable(i))
			);
		}

		if (ModConfig.MORE_INFO) {
			if (!context.isAdvanced() && (stack.isDamageableItem())) {
				tooltip.add(Component.translatable("item.durability", stack.getMaxDamage() - stack.getDamageValue(), 
					stack.getMaxDamage()).withStyle(ChatFormatting.RED));
			}
			if (FuelRegistry.get(stack)!=0){
				// tooltip.add(Component.literal("Burning time: "+ FuelRegistry.get(stack));
			}
			if (stack.isEdible()) {
				var component = item.getFoodProperties();
				// tooltip.add();
				tooltip.add(Component.translatable("item.hunger", component.getNutrition()).withStyle(ChatFormatting.GRAY));
				tooltip.add(Component.translatable("item.saturation", component.getSaturationModifier())
					.withStyle(ChatFormatting.GRAY));
				// tooltip.add(Component.literal("+" + component.getSaturationModifier() + " Saturation")
				// 	.withStyle(ChatFormatting.GRAY));
				if (!component.getEffects().isEmpty()) {
					tooltip.add(Component.translatable(component.getEffects().size() > 1 ? "item.effects" : "item.effect")
						.withStyle(ChatFormatting.BLUE));
					component.getEffects().forEach(i -> {
						String mutableText = "";
						mutableText = Component.translatable(i.getFirst().getDescriptionId()).getString()
						+ NumberUtil.converts(i.getFirst().getAmplifier())
						+ " " + TimeUtil.tickToSecIntoString(i.getFirst().getDuration());
						
						tooltip.add(Component.literal(" "+mutableText));
					});
				}
			}
		}

	}
}
