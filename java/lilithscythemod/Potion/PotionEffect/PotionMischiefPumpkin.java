package lilithscythemod.Potion.PotionEffect;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityDataManager;
import lilithscythemod.Potion.Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class PotionMischiefPumpkin extends Potion {
	AttributeModifier MischiefPumpkinBuffAttackDamage =new AttributeModifier(ModCore.mischiefPumpkinUUID,"MischiefPumpkinBuffAttackDamage",0,0);
	Multimap multimap;
	private double defaultAttackDamage = 0;
	private double preAttackDamage = 0;
	private double Attackdamage=0;
	public static final String PreMischiefPumpkinState ="PreMischiefPumpkinState";
	public static final String defaultMischiefPumpkinState ="defaultMischiefPumpkinState";

	@Override
	public String getName() {
		return "MischiefPumpkin";
	}
	/*powerLv1あたり、1％の攻撃力増加
	 *攻撃力を持たないEntityに対しては効果なし
	 */
	@Override
	public void effect(EntityLivingBase target, int powerLevel){

		if(EntityDataManager.activeData(target, PreMischiefPumpkinState)){
			this.preAttackDamage=EntityDataManager.getData(target, PreMischiefPumpkinState);
		}

		if(target.getEntityAttribute(SharedMonsterAttributes.attackDamage)!=null){
				defaultAttackDamage =Math.round(target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
		}

		if(EntityDataManager.activeData(target, defaultMischiefPumpkinState))defaultAttackDamage -= EntityDataManager.getData(target, defaultMischiefPumpkinState);

		Attackdamage = Math.round(defaultAttackDamage * powerLevel / 100);

		if(preAttackDamage != defaultAttackDamage){
			preAttackDamage=Attackdamage;
			MischiefPumpkinBuffAttackDamage= new AttributeModifier(ModCore.mischiefPumpkinUUID,"MischiefPumpkinBuffAttackDamage", Attackdamage, 0);
			multimap=getMap();
			this.saveAttackDamage(target, preAttackDamage, Attackdamage);
			target.getAttributeMap().applyAttributeModifiers(multimap);
	 		multimap.clear();
		}
	}

	public Multimap getMap()
    {
        HashMultimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), MischiefPumpkinBuffAttackDamage);
        return multimap;
    }
	private void saveAttackDamage(EntityLivingBase entity,double prePar,double Par){
		EntityDataManager.EntityCustomData(entity, PreMischiefPumpkinState, prePar);
		EntityDataManager.EntityCustomData(entity, defaultMischiefPumpkinState, Par);
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}

}

