package lilithscythemod.Skill;

import java.util.Iterator;

import lilithscythemod.ModCore;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SuitBullet extends Skill{
	private static final int duration = 20;
	private static final int powerLevel = 10;
	@Override
	public int getCoolTime(){
		return 20;
	}
	@Override
	public double getGlobalCoolDown(){
		return 3.0D;
	}
	@Override
	public float getChargeTime(){
		return 2.0F;
	}
	@Override
	public String getName() {
		return "SuitBullet";
	}
	@Override
	public void invocationSkill(World world,EntityLivingBase attaker){

		   Minecraft lmc = Minecraft.getMinecraft();
           // バフ判定
            EntityLiving targetEntity =null;
	         AxisAlignedBB var19 = attaker.boundingBox.expand(10.0D, 4.0D, 10.0D);
             Iterator var20 = attaker.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();

             while(var20.hasNext()){
	                  targetEntity = (EntityLiving) var20.next();
                 if ((Entity)targetEntity instanceof EntityMob) continue;
                 if(((Entity)targetEntity instanceof IBossDisplayData))continue;
                 // 射程距離の判定、MOBの大きさを考慮
                 double lln = 10.0D + (double)targetEntity.width;
                 lln *= lln;
                 	if (attaker.getDistanceSqToEntity(targetEntity)<=lln) {

                 		PotionEffectManager.applyEffect(targetEntity, ModCore.MODID,"SuitBullet", duration, powerLevel);
                 	}
	        }
	}
}
