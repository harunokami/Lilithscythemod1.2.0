package lilithscythemod.Event;

import lilithscythemod.Armors.AuraKingdomArmor;
import lilithscythemod.Entity.EntityMob.AuraKingdomsMob;
import lilithscythemod.Weapons.LilithscytheWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LilithAttackEventHook {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void damegeProtect(LivingHurtEvent e){
			EntityLivingBase target =  e.entityLiving;
			Entity attackerEntity =e.source.getEntity();
			float damageAmmount =e.ammount;
			int damageRiseAmmountLv=0;
			int protectLv =0;

			damageRiseAmmountLv = this.getDamageAmmount(attackerEntity);
			protectLv = getAmmountProtection(target);

			damageAmmount *= ( 100F + (damageRiseAmmountLv - protectLv) )/100F;
			if(damageAmmount>-1)e.ammount = damageAmmount;
			System.out.println(damageAmmount);
	}


	private int getDamageAmmount(Entity attacker){
		Item weapon=null;
		if(attacker instanceof EntityPlayer){
			if(((EntityPlayer)attacker).getHeldItem()!=null){
				weapon = ((EntityPlayer) attacker).inventory.getCurrentItem().getItem();
				if(weapon instanceof LilithscytheWeapons){
					int var = ((LilithscytheWeapons)weapon).getRiseDamage();
					return var>=0?var:0;
				}
			}
		}
		return 0;
	}

	private int getAmmountProtection(Entity defender){
		int protectVar=0;

		if(defender instanceof AuraKingdomsMob){
			protectVar = ((AuraKingdomsMob)defender).getDamegeProtectionValue();
		}else if(defender instanceof EntityPlayer){
			 ItemStack ArmoritemStack[] = ((EntityPlayer)defender).inventory.armorInventory;
			 for(int i=0;i<ArmoritemStack.length;i++){
				 ItemStack itemstack = ArmoritemStack[i];
				 if(itemstack!=null){
					 protectVar+=getArmorDamageProtection(itemstack);
				 }
			 }
		}

		return protectVar;
	}


	private int getArmorDamageProtection(ItemStack itemstack) {
		if(itemstack.getItem()!=null){
			if(itemstack.getItem() instanceof AuraKingdomArmor){
				return ((AuraKingdomArmor)itemstack.getItem()).getProtectionDamageLv();
			}
		}
		return 0;
	}
}