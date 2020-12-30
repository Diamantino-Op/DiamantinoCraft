/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class DiamantinocraftModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside com.diamantino.diamantinocraft as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package com.diamantino.diamantinocraft;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {

	public static IJeiHelpers jeiHelper;

	@Override
    public ResourceLocation getPluginUid() {
    	
        return new ResourceLocation("diamantinocraft", "default");
        
    }

	@Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
    	
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        
    }

    private static List<IRecipe<?>> getRecipesOfType(IRecipeType<?> recipeType) {
        assert Minecraft.getInstance().world != null;
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
                .filter(r -> r.getType() == recipeType)
                .collect(Collectors.toList());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        
    }
    
}

