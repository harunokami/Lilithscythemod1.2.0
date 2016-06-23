package lilithscythemod.Entity.EntityEffect;

import lilithscythemod.ISoundPlayer;
import lilithscythemod.Enum.EnumEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEffect extends Entity implements ISoundPlayer{
	public EntityLivingBase target;
	public EntityPlayer player;
	public EntityLiving mob;
	EnumEffectType effecttype;
	public int lifetime;
	public int counter;
	public int counter2;

	public EntityEffect(World p_i1582_1_) {
		super(p_i1582_1_);
	}
	public EntityEffect(World par1World, EntityPlayer par2Player, EnumEffectType par3Type){
		super(par1World);
		this.effecttype = par3Type;
		this.lifetime = par3Type.life;
		this.player = par2Player;
		String n = par2Player.getCommandSenderName();
		this.setMasterName(n);
		this.setEffectType(par3Type);
	}

	public EntityEffect(World par1World, EntityLiving par2Mob, EnumEffectType par3Type)
	{
		super(par1World);
		this.effecttype = par3Type;
		this.lifetime = par3Type.life;
		this.mob = par2Mob;
		String n = par2Mob.getCommandSenderName();
		this.setMasterName(n);
		this.setEffectType(par3Type);

	}
	//EffectRegister
	private void setEffectType(EnumEffectType type) {
		this.dataWatcher.updateObject(20, type.toString());
	}
	protected EnumEffectType getMagicType()
	{
		return EnumEffectType.getEffectTypeFromString(this.dataWatcher.getWatchableObjectString(20));
	}

	//EntityMaster
	public String getMasterName()
    {
        return this.dataWatcher.getWatchableObjectString(19);
    }
	private void setMasterName(String n) {
		this.dataWatcher.updateObject(19, n);
	}
	public EntityPlayer getMaster()
    {
		return this.worldObj.getPlayerEntityByName(this.getMasterName());
	}
	public Vec3 getTargetVec3()
	{
		if(this.target != null)
		{
			return Vec3.createVectorHelper(this.target.posX - this.posX, this.target.posY - this.posY, this.target.posZ - this.posZ);
		}

		return null;
	}
	public Vec3 getOwnerLookVec3(float par1)
	{
		return this.getMaster().getLook(1.0F);
	}
	/**
	 * 19byte:MasterName
	 * 20byte:EffectType
	 */
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(18, String.valueOf(0));
        this.dataWatcher.addObject(19, "");
        this.dataWatcher.addObject(20, "");
	}

	//update
	public void onUpdate()
	{
		super.onUpdate();

		if(this.counter2 > 0)
		{
			--this.counter2;
		}

		if(!this.worldObj.isRemote && (this.getMaster() == null || this.counter > this.getMagicType().life))
		{
			this.setDead();
		}

		if(this.isDead)
		{
			this.onDeath();
		}

		++this.counter;
	}

	protected void onDeath(){}
/**
 * Save&Load
 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		   String s = par1NBTTagCompound.getString("Master");

	        if (s.length() > 0)
	        {
	            this.setMasterName(s);
	        }
	        else
	        {
	        	this.setMasterName("");
	        }

	}
	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		  if (this.getMasterName() == null)
	        {
	            par1NBTTagCompound.setString("Master","");
	        }
	        else
	        {
	            par1NBTTagCompound.setString("Master",this.getMasterName());
	        }

	}
	//サウンド発動地
	@Override
	public double getPosX() {
		return this.posX;
	}
	@Override
	public double getPosY() {
		return this.posY;
	}
	@Override
	public double getPosZ() {
		return this.posZ;
	}
	@Override
	public boolean shouldStopSound() {

		return this.isDead;
	}
	//Option
	@SideOnly(Side.CLIENT)
	public int randomInt(int par1)
	{
		return this.rand.nextInt(par1);
	}
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

	public float getBrightness(float par1)
    {
        return 1.0F;
    }

}
