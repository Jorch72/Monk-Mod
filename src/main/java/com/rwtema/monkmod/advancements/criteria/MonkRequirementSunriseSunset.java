package com.rwtema.monkmod.advancements.criteria;

import com.rwtema.monkmod.data.MonkData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;

public class MonkRequirementSunriseSunset extends MonkRequirementTick {
	public MonkRequirementSunriseSunset(int stareTime) {
		super("mediate_sunrise", stareTime);
	}

	@Override
	protected void doTick(@Nonnull EntityPlayerMP player, @Nonnull MonkData monkData) {
		double celestialAngle = player.world.getCelestialAngle(0) * Math.PI * 2;
		double sunHeight = Math.cos(celestialAngle);
		if (Math.abs(sunHeight) < 0.1) {
			Vec3d vec3d1 = player.getLook(1.0F);
			Vec3d sunDir = new Vec3d(-Math.sin(celestialAngle), sunHeight, 0);

			if (sunDir.dotProduct(vec3d1) > 0.99) {
				if (monkData.increase(1, requirementLimit)) {
					grantLevel(player);
				}
			} else {
				monkData.resetProgress();
			}
		} else {
			monkData.resetProgress();
		}
	}
}
