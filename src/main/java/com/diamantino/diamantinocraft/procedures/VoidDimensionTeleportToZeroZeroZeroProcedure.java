package com.diamantino.diamantinocraft.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

import com.diamantino.diamantinocraft.DiamantinocraftModElements;
import com.diamantino.diamantinocraft.DiamantinocraftMod;

@DiamantinocraftModElements.ModElement.Tag
public class VoidDimensionTeleportToZeroZeroZeroProcedure extends DiamantinocraftModElements.ModElement {
	public VoidDimensionTeleportToZeroZeroZeroProcedure(DiamantinocraftModElements instance) {
		super(instance, 38);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DiamantinocraftMod.LOGGER.warn("Failed to load dependency entity for procedure VoidDimensionTeleportToZeroZeroZero!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(0, 64, 0);
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(0, 64, 0, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
