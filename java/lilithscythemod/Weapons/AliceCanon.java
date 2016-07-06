package lilithscythemod.Weapons;

import lilithscythemod.ModCore;
import lilithscythemod.Skill.SkillManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AliceCanon extends LilithscytheWeapons{

	public AliceCanon(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setTextureName("lilithscythemod:ariceCanonIcon");
		this.maxStackSize = 1;
		this.setMaxDamage(-1);
	}
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
		return true;
    }
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {

		if(checkSkillChange(world)){this.setChangeMode(itemStack);}

	}

	/** 右クリックでの使用時に呼ばれるメソッド。*/
	@Override
	public  ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par2World.isRemote)return par1ItemStack;
		if(par1ItemStack.getTagCompound()==null)return par1ItemStack;

		switch(par1ItemStack.getTagCompound().getByte(Tag_Mode)){
		case Firstmode :
			break;
		case SecondMode:
			SkillManager.applyEffect(par3EntityPlayer, ModCore.MODID, "CheshireBullet");
			break;
		case ThirdMode:
			SkillManager.applyEffect(par3EntityPlayer, ModCore.MODID, "SuitBullet");
			break;
		}

		return par1ItemStack;
	}
    /**
     * 右クリック時の動作のタイプ。
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer par3EntityPlayer, Entity par1entity)
    {
       return super.onLeftClickEntity(stack, par3EntityPlayer, par1entity);
    }

	public float getExtendedReach(ItemStack itemstack) {
		return 6;
	}
	@Override
	public int getRiseDamage(){
		return 30;
	}
}
