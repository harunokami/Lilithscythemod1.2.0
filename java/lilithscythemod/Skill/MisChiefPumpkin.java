package lilithscythemod.Skill;

import lilithscythemod.ModCore;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class MisChiefPumpkin extends Skill{
	private static final int duration = 20;
	private static final int powerLevel = 20;
	@Override
	public float getCoolTime(){
		return 8;
	}
	@Override
	public double getGlobalCoolDown(){
		return 3.0D;
	}
	@Override
	public float getChargeTime(){
		return 0.0F;
	}
	@Override
	public String getName() {
		return "MisChiefPumpkin";
	}
	@Override
	public void invocationSkill(World world,EntityLivingBase attaker){
		PotionEffectManager.applyEffect(attaker, ModCore.MODID,"MischiefPumpkin", duration, powerLevel);
	}

}
