package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.nms.NmsBasics;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.object.ArmorStandPacket;

import java.util.UUID;

abstract class ArmorStandPacketCommon implements ArmorStandPacket {

	private final NmsArmorStandPacket nmsArmorStandPacket;

	protected Object entityArmorStand;
	protected final Player player;

	public ArmorStandPacketCommon(NmsArmorStandPacket nmsArmorStandPacket, Player player) {
		this.nmsArmorStandPacket = nmsArmorStandPacket;
		this.player = player;
	}

	// -------------------------------------------- //
	// RAW
	// -------------------------------------------- //

	protected Object getWorldServer(World world) {
		Object craftWorld = nmsArmorStandPacket.classCraftWorld.cast(world);
		
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodCraftWorldGetHandle, craftWorld);
	}

	@Override
	public void createStand(Location location) {
		Object worldServer = getWorldServer(location.getWorld());
		
		this.entityArmorStand = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorEntityArmorStand, worldServer);
		
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetLocation, this.entityArmorStand, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}

	@Override
	public void spawnStand() {
		Object packetPlayOutSpawnEntityLiving = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorPacketPlayOutSpawnEntityLiving, this.entityArmorStand);
		NmsBasics.get().sendPacket(this.player, packetPlayOutSpawnEntityLiving);
	}

	@Override
	public boolean hasBasePlate() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandHasBasePlate, this.entityArmorStand);
	}

	@Override
	public void setBasePlate(boolean basePlate) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetBasePlate, this.entityArmorStand, basePlate);
	}

	@Override
	public boolean hasGravity() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandHasGravity, this.entityArmorStand);
	}

	@Override
	public void setGravity(boolean gravity) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetGravity, this.entityArmorStand, gravity);
	}

	@Override
	public boolean isInvisible() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandIsInvisible, this.entityArmorStand);
	}

	@Override
	public void setInvisible(boolean visible) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetInvisible, this.entityArmorStand, visible);
	}

	@Override
	public boolean hasArms() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandHasArms, this.entityArmorStand);
	}

	@Override
	public void setArms(boolean arms) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetArms, this.entityArmorStand, arms);
	}

	@Override
	public boolean isSmall() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandIsSmall, this.entityArmorStand);
	}

	@Override
	public void setSmall(boolean small) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetSmall, this.entityArmorStand, small);
	}

	@Override
	public Location getLocation() {
		Object world = ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetWorld, this.entityArmorStand);
		Object craftWorld = ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodWorldGetWorld, world);
		double x = ReflectionUtil.getField(nmsArmorStandPacket.fieldEntityArmorStandLocX, this.entityArmorStand);
		double y = ReflectionUtil.getField(nmsArmorStandPacket.fieldEntityArmorStandLocY, this.entityArmorStand);
		double z = ReflectionUtil.getField(nmsArmorStandPacket.fieldEntityArmorStandLocZ, this.entityArmorStand);
		float yaw = ReflectionUtil.getField(nmsArmorStandPacket.fieldEntityArmorStandYaw, this.entityArmorStand);
		float pitch = ReflectionUtil.getField(nmsArmorStandPacket.fieldEntityArmorStandPitch, this.entityArmorStand);
		
		return new Location((World) craftWorld, x, y, z, yaw, pitch);
	}

	@Override
	public void teleport(Location location) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetLocation, this.entityArmorStand, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		
		Object packet = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorPacketPlayOutEntityTeleport, this.entityArmorStand);
		NmsBasics.get().sendPacket(this.player, packet);
	}

	@Override
	public void remove() {
		int[] id = {getId()};
		Object packet = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorPacketPlayOutEntityDestroy, id);
		NmsBasics.get().sendPacket(this.player, packet);
		
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandDie, this.entityArmorStand);
	}

	@Override
	public boolean isDead() {
		return !(boolean)ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandIsAlive, this.entityArmorStand);
	}

	@Override
	public UUID getUniqueId() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetUniqueId, this.entityArmorStand);
	}

	@Override
	public String getCustomName() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetCustomName, this.entityArmorStand);
	}

	@Override
	public void setCustomName(String input) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetCustomName, this.entityArmorStand, input);
		
		Object dataWatcher = ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetDataWatcher, this.entityArmorStand);
		Object packet = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorPacketPlayOutEntityMetadata, getId(), dataWatcher, false);
		
		NmsBasics.get().sendPacket(this.player, packet);
	}

	@Override
	public void setCustomNameVisible(boolean customNameVisible) {
		ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandSetCustomNameVisible, this.entityArmorStand, customNameVisible);
		
		Object dataWatcher = ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetDataWatcher, this.entityArmorStand);
		Object packet = ReflectionUtil.invokeConstructor(nmsArmorStandPacket.constructorPacketPlayOutEntityMetadata, getId(), dataWatcher, false);
		
		NmsBasics.get().sendPacket(this.player, packet);
	}

	@Override
	public boolean isCustomNameVisible() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetCustomNameVisible, this.entityArmorStand);
	}

	protected int getId() {
		return ReflectionUtil.invokeMethod(nmsArmorStandPacket.methodEntityArmorStandGetId, this.entityArmorStand);
	}
}
