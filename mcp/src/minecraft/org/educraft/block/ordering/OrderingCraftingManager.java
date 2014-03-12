package org.educraft.block.ordering;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import org.educraft.EduCraft;
import org.educraft.item.BaseNumber;

/**
 * The crafting manager is used by the container to determine which patterns of
 * 
 * @author ianknight
 * 
 */
public enum OrderingCraftingManager {
	/**
	 * Singleton instance of the crafting manager.
	 */
	INSTANCE;

	/**
	 * Checks the given crafting matrix to see whether it matches any of the
	 * recipes known to this crafting manager.
	 * 
	 * @param inventory
	 *            the crafting matrix to check
	 * @return an ItemStack containing the result of the recipe, or null if the
	 *         pattern is not recognised
	 */
	public ItemStack findMatchingRecipe(InventoryCrafting inventory) {
		ItemStack stack, stack0;

		// if any of the input slots is null, or not a number, reject
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			stack = inventory.getStackInSlot(i);
			if ((stack == null) || !(stack.getItem() instanceof BaseNumber)) {
				return null;
			}
		}

		// if any of the inputs are in the wrong order, reject
		for (int i = 0; i < inventory.getSizeInventory() - 1; i++) {
			stack = inventory.getStackInSlot(i);
			stack0 = inventory.getStackInSlot(i + 1);
			if (stack.getItemDamage() >= stack0.getItemDamage()) {
				return null;
			}
		}

		return new ItemStack(EduCraft.KEY);
	}
}
