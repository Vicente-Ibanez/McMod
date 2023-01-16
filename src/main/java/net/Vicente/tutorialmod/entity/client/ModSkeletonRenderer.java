package net.Vicente.tutorialmod.entity.client;

import net.Vicente.tutorialmod.entity.custom.ModSkeleton;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Skeleton;

public class ModSkeletonRenderer extends SkeletonRenderer {


    public ModSkeletonRenderer(EntityRendererProvider.Context p_174380_) {
        super(p_174380_);
    }
}
