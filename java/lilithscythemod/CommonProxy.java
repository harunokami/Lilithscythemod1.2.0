package lilithscythemod;

import lilithscythemod.Event.PotionEffectEventHook;
import lilithscythemod.Potion.PotionEffectManager;
import lilithscythemod.Potion.PotionEventHandler;
import lilithscythemod.Potion.PotionEffect.BreakProtect;
import lilithscythemod.Potion.PotionEffect.Erode;
import lilithscythemod.Potion.PotionEffect.Percentagedamage;
import lilithscythemod.Potion.PotionEffect.PotionMischiefPumpkin;
import lilithscythemod.Potion.PotionEffect.PotionMoonOfChain;
import lilithscythemod.Potion.PotionEffect.PotionSuitBullet;
import lilithscythemod.Skill.CheshireBullet;
import lilithscythemod.Skill.MisChiefPumpkin;
import lilithscythemod.Skill.SkillManager;
import lilithscythemod.Skill.SuitBullet;
import lilithscythemod.Skill.TestSkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
/**
 * CommonProxy
 * */
public class CommonProxy
{

	public void registerRenderers()
	{
	}

	public World getClientWorld() {
		return null;
	}
	public void registerRenderInformation(){

	}
	public EntityPlayer getEntityPlayerInstance() {
		return null;
	}

	//Skill登録
	public void registerSkills(){
		 SkillManager.registerSkill(ModCore.MODID, new TestSkill());
		 SkillManager.registerSkill(ModCore.MODID, new MisChiefPumpkin());
		 SkillManager.registerSkill(ModCore.MODID, new SuitBullet());
		 SkillManager.registerSkill(ModCore.MODID, new CheshireBullet());
	}
	//Lilithポーションの登録
	public void registerLilithPotions(){
		 FMLLog.info("[PseudoPotion] Initializating");
		 MinecraftForge.EVENT_BUS.register(new PotionEventHandler());
		 MinecraftForge.EVENT_BUS.register(new PotionEffectEventHook());
		 FMLLog.info("[PseudoPotion] Initializated");
		 PotionEffectManager.registerPotion(ModCore.MODID, new PotionSuitBullet());
		 PotionEffectManager.registerPotion(ModCore.MODID, new PotionMischiefPumpkin());
		 PotionEffectManager.registerPotion(ModCore.MODID, new PotionMoonOfChain());
		 PotionEffectManager.registerPotion(ModCore.MODID, new BreakProtect());
		 PotionEffectManager.registerPotion(ModCore.MODID, new Percentagedamage());
		 PotionEffectManager.registerPotion(ModCore.MODID, new Erode());
		 FMLCommonHandler.instance().bus().register(new PotionEventHandler());
		 FMLCommonHandler.instance().bus().register(new PotionEffectEventHook());
	}
}