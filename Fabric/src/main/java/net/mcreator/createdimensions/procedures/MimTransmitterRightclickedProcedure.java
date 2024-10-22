package net.mcreator.createdimensions.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandFunction;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Optional;

public class MimTransmitterRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.END_ROD, x, y, z, 20, 1, 1, 1, 1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.activate")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.activate")), SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		{
			ItemStack _ist = itemstack;
			if (_ist.hurt(1, RandomSource.create(), null)) {
				_ist.shrink(1);
				_ist.setDamageValue(0);
			}
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("create_dimensions:land_of_the_mim"))) {
			new Object() {
				private int ticks = 0;

				public void startDelay(LevelAccessor world) {
					ServerTickEvents.END_SERVER_TICK.register((server) -> {
						this.ticks++;
						if (this.ticks == 40) {
							if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
								ResourceKey<Level> destinationType = Level.OVERWORLD;
								if (_player.level().dimension() == destinationType)
									return;
								ServerLevel nextLevel = _player.server.getLevel(destinationType);
								if (nextLevel != null) {
									_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
									_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
									_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
									for (MobEffectInstance _effectinstance : _player.getActiveEffects())
										_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
									_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
								}
							}
							{
								Entity _ent = entity;
								_ent.teleportTo(
										((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
												? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level().getLevelData().getXSpawn())
												: 0),
										((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
												? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level().getLevelData().getYSpawn())
												: 0),
										((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
												? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level().getLevelData().getZSpawn())
												: 0));
								if (_ent instanceof ServerPlayer _serverPlayer)
									_serverPlayer.connection.teleport(
											((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
													? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level().getLevelData().getXSpawn())
													: 0),
											((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
													? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level().getLevelData().getYSpawn())
													: 0),
											((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
													? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level().getLevelData().getZSpawn())
													: 0),
											_ent.getYRot(), _ent.getXRot());
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							return;
						}
					});
				}
			}.startDelay(world);
		} else {
			new Object() {
				private int ticks = 0;

				public void startDelay(LevelAccessor world) {
					ServerTickEvents.END_SERVER_TICK.register((server) -> {
						this.ticks++;
						if (this.ticks == 40) {
							if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
								ResourceKey<Level> destinationType = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("create_dimensions:the_mim"));
								if (_player.level().dimension() == destinationType)
									return;
								ServerLevel nextLevel = _player.server.getLevel(destinationType);
								if (nextLevel != null) {
									_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
									_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
									_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
									for (MobEffectInstance _effectinstance : _player.getActiveEffects())
										_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
									_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
								}
							}
							{
								Entity _ent = entity;
								_ent.teleportTo(1, 50, 1);
								if (_ent instanceof ServerPlayer _serverPlayer)
									_serverPlayer.connection.teleport(1, 50, 1, _ent.getYRot(), _ent.getXRot());
							}
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 1, false, false));
							new Object() {
								private int ticks = 0;

								public void startDelay(LevelAccessor world) {
									ServerTickEvents.END_SERVER_TICK.register((server) -> {
										this.ticks++;
										if (this.ticks == 20) {
											for (int index0 = 0; index0 < 4; index0++) {
												{
													Entity _ent = entity;
													if (!_ent.level().isClientSide() && _ent.getServer() != null) {
														Optional<CommandFunction> _fopt = _ent.getServer().getFunctions().get(new ResourceLocation("create_dimensions:idk"));
														if (_fopt.isPresent())
															_ent.getServer().getFunctions().execute(_fopt.get(), _ent.createCommandSourceStack());
													}
												}
											}
											return;
										}
									});
								}
							}.startDelay(world);
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							return;
						}
					});
				}
			}.startDelay(world);
		}
	}
}
