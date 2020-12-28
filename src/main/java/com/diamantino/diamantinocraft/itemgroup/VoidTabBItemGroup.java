
package com.diamantino.diamantinocraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.diamantino.diamantinocraft.block.TeleporterBlock;
import com.diamantino.diamantinocraft.DiamantinocraftModElements;

@DiamantinocraftModElements.ModElement.Tag
public class VoidTabBItemGroup extends DiamantinocraftModElements.ModElement {
	public VoidTabBItemGroup(DiamantinocraftModElements instance) {
		super(instance, 65);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabvoid_tab_b") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(TeleporterBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
