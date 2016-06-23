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
	AttributeModifier MischiefPumpkinBuffAttackDamage =new AttributeModifier(ModCore.mischiefPumpkinUUID,"mischiefPumpkinDamage",0,0);
	Multimap multimap;
	private double defaultAttackDamage = 0;
	private double preAttackDamage = 0;
	private double Attackdamage=0;
	public static final String PreMischiefPumpkinState ="PreMischiefPumpkinState";
	public static final String MischiefPumpkinState ="defaultMischiefPumpkinState";

	@Override
	public String getName() {
		return "MischiefPumpkin";
	}

	//preAttackDamage=前回までのターゲットの攻撃力
	//defaultAttackdamage=新しく取得した攻撃力(前回に追加した場合はその値が加算されている）
	//AttackDamage = 付加する攻撃値
	@Override
	public void effect(EntityLivingBase target, int powerLevel){

		//前回までの付加攻撃値を取得
		if(EntityDataManager.activeData(target, PreMischiefPumpkinState)){
			this.preAttackDamage=EntityDataManager.getData(target, PreMischiefPumpkinState);
		}
		if(target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttribute()!=null){
				defaultAttackDamage =Math.round(target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
		}
		if(EntityDataManager.activeData(target, MischiefPumpkinState))defaultAttackDamage -= EntityDataManager.getData(target, MischiefPumpkinState);
		//ステータス変化の条件
		//このバフ以外の影響でステータスが変化したとき
		//強いバフLvで上書きされたとき
		Attackdamage = Math.round(defaultAttackDamage * (powerLevel/50));
		if(preAttackDamage != defaultAttackDamage){
			preAttackDamage=Math.round(Attackdamage + defaultAttackDamage);
			MischiefPumpkinBuffAttackDamage= new AttributeModifier(ModCore.mischiefPumpkinUUID,"mischiefPumpkinDamage", Attackdamage, 0);
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
		EntityDataManager.EntityCustomData(entity, MischiefPumpkinState, Par);
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}

}

