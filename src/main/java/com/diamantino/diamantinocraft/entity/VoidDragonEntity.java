
package com.diamantino.diamantinocraft.entity;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.EnumSet;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableList;

import com.diamantino.diamantinocraft.item.VoidDragonFireballItem;
import com.diamantino.diamantinocraft.DiamantinocraftModElements;

@DiamantinocraftModElements.ModElement.Tag
public class VoidDragonEntity extends DiamantinocraftModElements.ModElement {
	public static EntityType entity = null;
	@ObjectHolder("diamantinocraft:entitybulletvoid_dragon")
	public static final EntityType arrow = null;
	public VoidDragonEntity(DiamantinocraftModElements instance) {
		super(instance, 39);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("void_dragon").setRegistryName("void_dragon");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13421773, -16764007, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("void_dragon_spawn_egg"));
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletvoid_dragon").setRegistryName("entitybulletvoid_dragon"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("diamantinocraft:void_biome").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity, 50, 2, 2));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				return new MobRenderer(renderManager, new ModelEnderDragonModel(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("diamantinocraft:textures/void_dragon.png");
					}
				};
			});
			RenderingRegistry.registerEntityRenderingHandler(arrow,
					renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 600);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 10);
		ammma = ammma.createMutableAttribute(Attributes.FLYING_SPEED, 0.3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 50;
			setNoAI(false);
			setCustomName(new StringTextComponent("Void Dragon"));
			setCustomNameVisible(true);
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vector3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 16) {
							Vector3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vector3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vector3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, this, this.world);
			double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
			double d1 = target.getPosX() - this.getPosX();
			double d3 = target.getPosZ() - this.getPosZ();
			entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
			world.addEntity(entityarrow);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	private static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(VoidDragonFireballItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(VoidDragonFireballItem.block, (int) (1));
		}
	}

	/**
	 * EnderDragonModel - Either Mojang or a mod author (Taken From Memory) Created
	 * using Tabula 8.0.0
	 */
	@OnlyIn(Dist.CLIENT)
	public static class ModelEnderDragonModel<T extends Entity> extends EntityModel<T> {
		public ModelRenderer field_229067_j_;
		public ModelRenderer field_78219_b;
		public ModelRenderer field_78217_d;
		public ModelRenderer field_229075_u_;
		public ModelRenderer field_229078_x_;
		public ModelRenderer field_229065_h_;
		public ModelRenderer field_229073_p_;
		public ModelRenderer field_229070_m_;
		public ModelRenderer field_78221_a;
		public ModelRenderer field_229068_k_;
		public ModelRenderer field_229069_l_;
		public ModelRenderer field_229076_v_;
		public ModelRenderer field_229077_w_;
		public ModelRenderer field_229079_y_;
		public ModelRenderer field_229080_z_;
		public ModelRenderer field_229066_i_;
		public ModelRenderer field_229074_t_;
		public ModelRenderer field_229071_n_;
		public ModelRenderer field_229072_o_;
		public ModelRenderer field_78220_c;
		public ModelEnderDragonModel() {
			this.textureWidth = 256;
			this.textureHeight = 256;
			this.field_229080_z_ = new ModelRenderer(this, 112, 0);
			this.field_229080_z_.setRotationPoint(0.0F, 31.0F, 4.0F);
			this.field_229080_z_.addBox(-9.0F, 0.0F, -20.0F, 18.0F, 6.0F, 24.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229080_z_, 0.7517109732416818F, 0.0F, 0.0F);
			this.field_229071_n_ = new ModelRenderer(this, 196, 0);
			this.field_229071_n_.setRotationPoint(0.0F, 32.0F, -4.0F);
			this.field_229071_n_.addBox(-6.0F, -2.0F, 0.0F, 12.0F, 32.0F, 12.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229071_n_, 0.5017109536787238F, 0.0F, 0.0F);
			this.field_229072_o_ = new ModelRenderer(this, 112, 0);
			this.field_229072_o_.setRotationPoint(0.0F, 31.0F, 4.0F);
			this.field_229072_o_.addBox(-9.0F, 0.0F, -20.0F, 18.0F, 6.0F, 24.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229072_o_, 0.7517109732416818F, 0.0F, 0.0F);
			this.field_229068_k_ = new ModelRenderer(this, 226, 138);
			this.field_229068_k_.setRotationPoint(0.0F, 20.0F, -1.0F);
			this.field_229068_k_.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229068_k_, -0.5017109536787238F, 0.0F, 0.0F);
			this.field_229067_j_ = new ModelRenderer(this, 112, 104);
			this.field_229067_j_.setRotationPoint(12.0F, 20.0F, 2.0F);
			this.field_229067_j_.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229067_j_, 1.3017107965693995F, 0.0F, 0.0F);
			this.field_229065_h_ = new ModelRenderer(this, -56, 88);
			this.field_229065_h_.mirror = true;
			this.field_229065_h_.setRotationPoint(12.0F, 5.0F, 2.0F);
			this.field_229065_h_.setTextureOffset(168, 0).addBox(0.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
			this.field_229065_h_.addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229065_h_, -0.07500000087546035F, -0.2500000029182012F, -0.10000000116728046F);
			this.field_78219_b = new ModelRenderer(this, 48, 0);
			this.field_78219_b.setRotationPoint(9.507418E-6F, 24.378252F, 168.75218F);
			this.field_78219_b.setTextureOffset(144, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
			this.field_78219_b.addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_78219_b, 0.05922154123674346F, 3.141592653589793F, 0.0F);
			this.field_78221_a = new ModelRenderer(this, 0, 0);
			this.field_78221_a.setRotationPoint(0.0F, 36.42932F, -58.84613F);
			this.field_78221_a.setTextureOffset(176, 44).addBox(-6.0F, -1.0F, -24.0F, 12.0F, 5.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.field_78221_a.setTextureOffset(112, 30).addBox(-8.0F, -8.0F, -10.0F, 16.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.field_78221_a.addBox(-5.0F, -12.0F, -4.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
			this.field_78221_a.setTextureOffset(112, 0).addBox(-5.0F, -3.0F, -22.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
			this.field_78221_a.addBox(3.0F, -12.0F, -4.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
			this.field_78221_a.setTextureOffset(112, 0).addBox(3.0F, -3.0F, -22.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_78221_a, 0.7853981633974483F, 0.0F, -0.0F);
			this.field_229073_p_ = new ModelRenderer(this, -56, 88);
			this.field_229073_p_.setRotationPoint(-12.0F, 5.0F, 2.0F);
			this.field_229073_p_.setTextureOffset(168, 0).addBox(-56.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
			this.field_229073_p_.addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229073_p_, -0.07500000087546035F, 0.2500000029182012F, 0.10000000116728046F);
			this.field_229069_l_ = new ModelRenderer(this, 144, 104);
			this.field_229069_l_.setRotationPoint(0.0F, 23.0F, 0.0F);
			this.field_229069_l_.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229069_l_, 0.7517109732416818F, 0.0F, 0.0F);
			this.field_229078_x_ = new ModelRenderer(this, 0, 0);
			this.field_229078_x_.setRotationPoint(-16.0F, 16.0F, 42.0F);
			this.field_229078_x_.addBox(-8.0F, -4.0F, -8.0F, 16.0F, 32.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229078_x_, 1.0017108929360987F, 0.0F, 0.0F);
			this.field_229077_w_ = new ModelRenderer(this, 144, 104);
			this.field_229077_w_.setRotationPoint(0.0F, 23.0F, 0.0F);
			this.field_229077_w_.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229077_w_, 0.7517109732416818F, 0.0F, 0.0F);
			this.field_229066_i_ = new ModelRenderer(this, -56, 136);
			this.field_229066_i_.mirror = true;
			this.field_229066_i_.setRotationPoint(56.0F, 0.0F, 0.0F);
			this.field_229066_i_.setTextureOffset(168, 0).addBox(0.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
			this.field_229066_i_.setTextureOffset(0, 8).addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229066_i_, 0.0F, 0.0F, 1.056973083451897F);
			this.field_78217_d = new ModelRenderer(this, 0, 0);
			this.field_78217_d.setRotationPoint(0.0F, 4.0F, 8.0F);
			this.field_78217_d.addBox(-12.0F, 0.0F, -16.0F, 24.0F, 24.0F, 64.0F, 0.0F, 0.0F, 0.0F);
			this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, -10.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
			this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 10.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
			this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 30.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
			this.field_78220_c = new ModelRenderer(this, 176, 65);
			this.field_78220_c.setRotationPoint(0.0F, 4.0F, -8.0F);
			this.field_78220_c.addBox(-6.0F, 0.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_78220_c, 0.20000000233456092F, 0.0F, 0.0F);
			this.field_229070_m_ = new ModelRenderer(this, 0, 0);
			this.field_229070_m_.setRotationPoint(16.0F, 16.0F, 42.0F);
			this.field_229070_m_.addBox(-8.0F, -4.0F, -8.0F, 16.0F, 32.0F, 16.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229070_m_, 1.0017108929360987F, 0.0F, 0.0F);
			this.field_229074_t_ = new ModelRenderer(this, -56, 136);
			this.field_229074_t_.setRotationPoint(-56.0F, 0.0F, 0.0F);
			this.field_229074_t_.setTextureOffset(168, 0).addBox(-56.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
			this.field_229074_t_.setTextureOffset(0, 8).addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229074_t_, 0.0F, 0.0F, -1.056973083451897F);
			this.field_229075_u_ = new ModelRenderer(this, 112, 104);
			this.field_229075_u_.setRotationPoint(-12.0F, 20.0F, 2.0F);
			this.field_229075_u_.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229075_u_, 1.3017107965693995F, 0.0F, 0.0F);
			this.field_229079_y_ = new ModelRenderer(this, 196, 0);
			this.field_229079_y_.setRotationPoint(0.0F, 32.0F, -4.0F);
			this.field_229079_y_.addBox(-6.0F, -2.0F, 0.0F, 12.0F, 32.0F, 12.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229079_y_, 0.5017109536787238F, 0.0F, 0.0F);
			this.field_229076_v_ = new ModelRenderer(this, 226, 138);
			this.field_229076_v_.setRotationPoint(0.0F, 20.0F, -1.0F);
			this.field_229076_v_.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, 0.0F, 0.0F);
			this.setRotateAngle(field_229076_v_, -0.5017109536787238F, 0.0F, 0.0F);
			this.field_229079_y_.addChild(this.field_229080_z_);
			this.field_229070_m_.addChild(this.field_229071_n_);
			this.field_229071_n_.addChild(this.field_229072_o_);
			this.field_229067_j_.addChild(this.field_229068_k_);
			this.field_229068_k_.addChild(this.field_229069_l_);
			this.field_229076_v_.addChild(this.field_229077_w_);
			this.field_229065_h_.addChild(this.field_229066_i_);
			this.field_78221_a.addChild(this.field_78220_c);
			this.field_229073_p_.addChild(this.field_229074_t_);
			this.field_229078_x_.addChild(this.field_229079_y_);
			this.field_229075_u_.addChild(this.field_229076_v_);
		}

		@Override
		public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green,
				float blue, float alpha) {
			ImmutableList.of(this.field_229067_j_, this.field_229065_h_, this.field_78219_b, this.field_78221_a, this.field_229073_p_,
					this.field_229078_x_, this.field_78217_d, this.field_229070_m_, this.field_229075_u_).forEach((modelRenderer) -> {
						modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
					});
		}

		@Override
		public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
