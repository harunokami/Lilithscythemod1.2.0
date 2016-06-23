package lilithscythemod.Potion.PotionEffect;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityDataManager;
import lilithscythemod.Potion.Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class PotionSuitBullet extends Potion{

	AttributeModifier SuitBulletBuffAttackDamage=new AttributeModifier(ModCore.SuitBulletUUID,"SuitBulletBuffAttackDamage",0,0);
	Multimap multimap;
	private double defaultAttackDamage = 0;
	private double preAttackDamage = 0;
	private double Attackdamage=0;
	public static final String PreSuitBulletState ="PreSuitBulletState";
	public static final String defaultSuitBulletState ="defaultSuitBulletState";
	@Override
	public String getName() {

		return "SuitBullet";
	}
/*powerLv1あたり、1％の攻撃力増加
 *攻撃力を持たないEntityに対しては効果なし
 */
	@Override
	public void effect(EntityLivingBase target, int powerLevel) {
		if(EntityDataManager.activeData(target, PreSuitBulletState)){
			this.preAttackDamage=EntityDataManager.getData(target, PreSuitBulletState);
		}
		if(target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttribute()!=null){
				defaultAttackDamage =Math.round(target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
		}
		if(EntityDataManager.activeData(target, defaultSuitBulletState))defaultAttackDamage -= EntityDataManager.getData(target, defaultSuitBulletState);

		Attackdamage = defaultAttackDamage * powerLevel/50;

		if(preAttackDamage!=defaultAttackDamage){
			preAttackDamage=Attackdamage;
			SuitBulletBuffAttackDamage= new AttributeModifier(ModCore.SuitBulletUUID,"SuitBulletBuffAttackDamage", Attackdamage, 0);
			multimap=getMap();
			this.saveAttackDamage(target, preAttackDamage, Attackdamage);
			target.getAttributeMap().applyAttributeModifiers(multimap);
	 		multimap.clear();
		}
	}

	public Multimap getMap()
    {
        HashMultimap multimap = HashMultimap.create();
        multimap.put((SharedMonsterAttributes.attackDamage).getAttributeUnlocalizedName(), SuitBulletBuffAttackDamage);
        return multimap;
    }

	private void saveAttackDamage(EntityLivingBase entity,double prePar,double Par){
		EntityDataManager.EntityCustomData(entity, PreSuitBulletState, prePar);
		EntityDataManager.EntityCustomData(entity, defaultSuitBulletState, Par);
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}
}
