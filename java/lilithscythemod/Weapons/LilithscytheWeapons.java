package lilithscythemod.Weapons;

import lilithscythemod.ClientProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LilithscytheWeapons extends ItemSword{


	//fields
	public static final String Tag_Mode ="LilithscytheWeaponsSkillMode";
	public static final byte Firstmode 	=0x00;
	public static final byte SecondMode	=0x01;
	public static final byte ThirdMode 	=0x02;

	public LilithscytheWeapons(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}

	public float getExtendedReach(ItemStack itemstack) {
		return 4;
	}
	public int getRiseDamage(){return 0;}

	public boolean checkSkillChange(World world){
		if(world.isRemote)return false;
			if(ClientProxy.weaponActionKey.isPressed()){
				return true;
			}
		return false;
	}
	public void setChangeMode(ItemStack item){
		this.setModeNBT(item);
	};

	private void setModeNBT(ItemStack item){
		NBTTagCompound nbt =  item.getTagCompound();
		System.out.println(item.getUnlocalizedName());
		if(nbt==null){
			  nbt =new NBTTagCompound();
			  nbt.setByte(Tag_Mode, this.Firstmode);
			  item.setTagCompound(nbt);
		}
		switch (nbt.getByte(Tag_Mode)){
		case Firstmode :
			nbt.setByte(Tag_Mode, SecondMode);
			break;
		case SecondMode:
			nbt.setByte(Tag_Mode, ThirdMode);
			break;
		case ThirdMode:
			nbt.setByte(Tag_Mode, Firstmode);
			break;
		}
		item.setTagCompound(nbt);
	};

}
