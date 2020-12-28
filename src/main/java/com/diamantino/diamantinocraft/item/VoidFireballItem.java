
package com.diamantino.diamantinocraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import com.diamantino.diamantinocraft.DiamantinocraftModElements;

@DiamantinocraftModElements.ModElement.Tag
public class VoidFireballItem extends DiamantinocraftModElements.ModElement {
	@ObjectHolder("diamantinocraft:void_fireball")
	public static final Item block = null;
	public VoidFireballItem(DiamantinocraftModElements instance) {
		super(instance, 63);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("void_fireball");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
