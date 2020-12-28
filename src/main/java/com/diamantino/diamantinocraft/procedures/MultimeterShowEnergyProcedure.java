package com.diamantino.diamantinocraft.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

import com.diamantino.diamantinocraft.DiamantinocraftModElements;
import com.diamantino.diamantinocraft.DiamantinocraftMod;

@DiamantinocraftModElements.ModElement.Tag
public class MultimeterShowEnergyProcedure extends DiamantinocraftModElements.ModElement {
	public MultimeterShowEnergyProcedure(DiamantinocraftModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency direction for procedure MultimeterShowEnergy!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency entity for procedure MultimeterShowEnergy!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency x for procedure MultimeterShowEnergy!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency y for procedure MultimeterShowEnergy!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency z for procedure MultimeterShowEnergy!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency world for procedure MultimeterShowEnergy!");
			return;
		}
		Direction direction = (Direction) dependencies.get("direction");
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((((new Object() {
				public int getEnergyStored(BlockPos pos) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
					return _retval.get();
				}
			}.getEnergyStored(new BlockPos((int) x, (int) y, (int) z)))) + "" + (" / ") + "" + ((new Object() {
				public int getMaxEnergyStored(BlockPos pos) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> _retval.set(capability.getMaxEnergyStored()));
					return _retval.get();
				}
			}.getMaxEnergyStored(new BlockPos((int) x, (int) y, (int) z)))) + "" + (" FE at ") + "" + (direction))), (true));
		}
	}
}
