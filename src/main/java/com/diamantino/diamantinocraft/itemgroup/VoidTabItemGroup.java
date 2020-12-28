
package com.diamantino.diamantinocraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.diamantino.diamantinocraft.item.MultimeterItem;
import com.diamantino.diamantinocraft.DiamantinocraftModElements;

@DiamantinocraftModElements.ModElement.Tag
public class VoidTabItemGroup extends DiamantinocraftModElements.ModElement {
	public VoidTabItemGroup(DiamantinocraftModElements instance) {
		super(instance, 64);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabvoid_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MultimeterItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
