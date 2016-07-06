package lilithscythemod.Skill;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityLib;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CheshireBullet extends Skill{

	private static int i=0;
	private int duration = 10;
	private int powerLevel = 3;
	@Override
	public float getCoolTime(){
		return 0;
	}
	@Override
	public double getGlobalCoolDown(){
		return 0.0D;
	}
	@Override
	public float getChargeTime(){
		return 0.0F;
	}
	@Override
	public String getName() {
		return "CheshireBullet";
	}
	@Override
	public void invocationSkill(World world,EntityLivingBase attaker){

		  for(EntityLiving entity : EntityLib.getSearchEntity(world, (EntityLivingBase)attaker, 10, 35D, 60F, 60F))
		  {
			  if(attaker instanceof EntityPlayer) ((EntityPlayer) attaker).attackTargetEntityWithCurrentItem(entity);
			 entity.attackEntityFrom(DamageSource.magic,ModCore.AliceMaterial.getDamageVsEntity()*0.1F);
			 if(!PotionEffectManager.activeEffects((EntityLivingBase)entity, ModCore.MODID, "Percentagedamage")){
				PotionEffectManager.applyEffect((EntityLivingBase) entity, ModCore.MODID,"Percentagedamage", duration, powerLevel);
			}
			  entity.hurtResistantTime=0;
		  }
	}

}
