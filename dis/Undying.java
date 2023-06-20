public class Undying {
    public static void giveUnbreakableTag(ItemStack stack, Level world) {
        if (world.isClientSide || !stack.isDamageableItem()) {
            return;
        }
        String regName = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        if (LIST.contains(regName)) {
            if (WHITE_OR_BLACK) {
                return;
            }
        } else {
            if (!WHITE_OR_BLACK) {
                return;
            }
        }
        stack.getOrCreateTag().putBoolean("Unbreakable", true);
        // if (!itemStack.isDamageableItem() || !ModConfig.ENABLE || world.isClientSide
        // || BuiltInRegistries.ITEM.getId(itemStack.getItem()) == null) return;
        // String regName =
        // BuiltInRegistries.ITEM.getId(itemStack.getItem()).toString();
        // List<? extends String> list = BLACKLIST_OR_WHITELIST_DAMAGEABLE_ITEMS.get();
        // if (MODE.get().equals("B")) {
        // if (list.contains(regName)) return;
        // } else {
        // if (!list.contains(regName)) return;
        // }
        // stack.getOrCreateTag().putBoolean("Unbreakable", true);
    }
}
