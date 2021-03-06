/*
 * This file is part of EchoPet.
 *
 * EchoPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EchoPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EchoPet.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.echopet.compat.nms.v1_9_R2.entity.type;

import lombok.*;

import java.util.Random;

import com.dsh105.echopet.compat.api.entity.EntityPetType;
import com.dsh105.echopet.compat.api.entity.EntitySize;
import com.dsh105.echopet.compat.api.entity.IPet;
import com.dsh105.echopet.compat.api.entity.PetType;
import com.dsh105.echopet.compat.api.entity.SizeCategory;
import com.dsh105.echopet.compat.api.entity.type.nms.IEntityGiantPet;
import com.dsh105.echopet.compat.nms.v1_9_R2.entity.EntityInsentientPet;
import com.dsh105.echopet.compat.nms.v1_9_R2.entity.EntityInsentientPetData;

import net.minecraft.server.v1_9_R2.Block;
import net.minecraft.server.v1_9_R2.BlockPosition;
import net.minecraft.server.v1_9_R2.EntityGiantZombie;
import net.minecraft.server.v1_9_R2.SoundEffect;
import net.minecraft.server.v1_9_R2.World;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftGiant;

@EntitySize(width = 5.5F, height = 5.5F)
@EntityPetType(petType = PetType.GIANT)
public class EntityGiantPet extends EntityGiantZombie implements EntityInsentientPet, IEntityGiantPet {

    @Override
    public void makeStepSound() {
        this.playSound(Sound.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
    }

    @Override
    public Sound getIdleSound() {
        return Sound.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    public Sound getDeathSound() {
        return Sound.ENTITY_ZOMBIE_DEATH;
    }

    @Override
    public SizeCategory getSizeCategory() {
        return SizeCategory.OVERSIZE;
    }

    // EntityInsentientPet Implementations

    @Override
    public EntityGiantZombie getEntity() {
        return this;
    }

    @Getter
    private IPet pet;
    @Getter
    private final EntityInsentientPetData nmsData = new EntityInsentientPetData(this);

    @Override
    public void m() {
        super.m();
        onLive();
    }

    public void g(float sideMot, float forwMot) {
        move(sideMot, forwMot, super::g);
    }

    public EntityGiantPet(World world, IPet pet) {
        super(world);
        this.pet = pet;
        this.initiateEntityPet();
    }

    @Override
    public CraftGiant getBukkitEntity() {
        return (CraftGiant) super.getBukkitEntity();
    }

    // Access helpers

    @Override
    public Random random() {
        return this.random;
    }

    @Override
    public SoundEffect bS() {
        return EntityInsentientPet.super.bS();
    }

    @Override
    public void a(BlockPosition blockposition, Block block) {
        super.a(blockposition, block);
        onStep(blockposition, block);
    }

    @Override
    public SoundEffect G() {
        return EntityInsentientPet.super.G();
    }

    @Override
    public void setYawPitch(float f, float f1) {
        super.setYawPitch(f, f1);
    }
}
