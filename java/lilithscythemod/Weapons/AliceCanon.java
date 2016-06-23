package lilithscythemod.Weapons;

import lilithscythemod.ClientProxy;
import lilithscythemod.ModCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AliceCanon extends LilithscytheWeapons{

	public AliceCanon(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName("AliceCanon")
		.setTextureName("lilithscythemod:AliceCanon")
		.setCreativeTab(ModCore.LilithscytheTab);
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

		if(!world.isRemote)
		{
			if(ClientProxy.weaponActionKey.isPressed()){
				this.setChangeMode(itemStack);
			}
		}

	}
	/*アリスの武器は3種類の形態を持つ
	 * 1:canonMode
	 * 2:vulcanMode
	 * 3:mineMode
	 * この3つを特定のキーで切り替えるメソッド*/
	private void setChangeMode(ItemStack item){
		NBTTagCompound nbt =  item.getTagCompound();
		if(nbt==null){
			  nbt =new NBTTagCompound();
			  nbt.setByte(Tag_Mode, this.modeCanon);
			  item.setTagCompound(nbt);
		}
			switch (nbt.getByte(Tag_Mode)){
			case modeCanon :
				nbt.setByte(Tag_Mode, modeVulcan);
				break;
			case modeVulcan:
				nbt.setByte(Tag_Mode, modeMine);
				break;
			case modeMine:
				nbt.setByte(Tag_Mode, modeCanon);
				break;
			}

	}
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer par3EntityPlayer, Entity par1entity)
    {
       return super.onLeftClickEntity(stack, par3EntityPlayer, par1entity);
    }
	public  ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		 return par1ItemStack;
	}
	public float getExtendedReach(ItemStack itemstack) {
		return 6;
	}

	public static final String Tag_Mode ="AliceCanonMode";
	public static final byte modeCanon 	=0x00;
	public static final byte modeVulcan	=0x01;
	public static final byte modeMine 	=0x02;

}
