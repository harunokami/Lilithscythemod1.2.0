package lilithscythemod.Potion.PotionEffect;

import lilithscythemod.Entity.EntityDataManager;
import lilithscythemod.Potion.Potion;
import net.minecraft.entity.EntityLivingBase;

public class PotionMoonOfChain extends Potion{

	private static float preHealth=0;
	public static final String State_MoonOfChainHealth ="Moon_Of_Chain_Health";

	@Override
	public String getName() {
		return "MoonOfChain";
	}

	@Override
	public void effect(EntityLivingBase target, int powerLevel) {

		if(this.getActiveHealCheck(target)){
			target.setHealth(preHealth);
		}else{
			this.setMoonOfChainHealth(target, target.getHealth());
		}
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}
	private void setMoonOfChainHealth(EntityLivingBase target,float setHealth){
		EntityDataManager.EntityCustomData(target, State_MoonOfChainHealth,(double)setHealth);
	}
	/**
	 * @param ムーンオブチェーン中に回復しようとしてるかどうかのチェック
	 * @param target :回復検知したい対象
	 * */
	public static boolean getActiveHealCheck(EntityLivingBase target){
		//前チックで既にかかっていたかどうか
		if( EntityDataManager.activeData(target, State_MoonOfChainHealth) ){
			//前チックのHPと現在のHPを比較して、特殊(ヒールイベントを使わずに直接HPを書き換えるタイプ)回復を検知して書き換えを行う
			preHealth = (float)EntityDataManager.getData(target, State_MoonOfChainHealth);
			if(preHealth<0)preHealth=0;
			return target.getHealth()>preHealth&&preHealth>0 ? true:false;
		}
		return false;
	}
}
