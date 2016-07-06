package lilithscythemod.Entity.EntityEffect;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityShot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMagicCircle extends EntityShot{

	 public final static String EXT_PROP_NAME = ModCore.MODID + "MagicCircleData";
	 public float MaxRange;
	 public float r;
	 public float s;
	 public float Circlerotation;
	 public float CircleMaxExpansionTime = 20;

	 private final int RangeDatawacherNumber = 22;
	 private final int CircleRotationNumber  = 21;
//Construct
	public EntityMagicCircle(World par1World){
		super(par1World);
		this.setSize(5f,5f);
	}
	public EntityMagicCircle(World par1World,EntityLivingBase par2EntityLivingBase, float range, float rotationSpeed) {
		super(par1World, par2EntityLivingBase);
		this.r = 0;
		this.MaxRange =range;
		this.s = rotationSpeed;
		this.setMasterName(par2EntityLivingBase.getCommandSenderName());
		this.Circlerotation=par2EntityLivingBase.rotationYaw;
		this.setSize(range,range);

	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(18, String.valueOf(0));
        this.dataWatcher.addObject(19, "");
		this.dataWatcher.addObject(CircleRotationNumber, Float.valueOf(0F));
		this.dataWatcher.addObject(RangeDatawacherNumber, Float.valueOf(0F));
	}
	@Override
	@SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9){}

	/** 毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
		super.onUpdate();
		if(!this.worldObj.isRemote){
			setCircleExpansionRange(CircleMaxExpansionTime);
			setCircleRotation(this.s);
		}else{
			if(getMaster()!=null)this.setPosition(getMaster().posX,getMaster().posY-getMaster().height,getMaster().posZ);
		}
	}
	//消滅プロセス
	@Override
	protected void deadProcess()
    {
		if(this.getMaster()==null){
			if(!this.worldObj.isRemote)this.setDead();
		}else if(this.getMaster().isDead){
    		this.setDead();
    	}else if(!this.getMaster().isUsingItem()){
    		this.setDead();
    	}
    }
	//SAVE&LOAD
	@Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
    	super.readEntityFromNBT(p_70037_1_);
			   String s = p_70037_1_.getString("Master");
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
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
    	super.writeEntityToNBT(p_70014_1_);
		 if (this.getMasterName() == null)
	        {
			 	p_70014_1_.setString("Master", "");
	        }
	        else
	        {
	        	p_70014_1_.setString("Master", this.getMasterName());
	        }

	}
    //EntityLook
    public Vec3 getOwnerLookVec3()
	{
    	return this.getMaster().getLook(1.0F);
    }
    //Master
	public String getMasterName()
    {
        return this.dataWatcher.getWatchableObjectString(19);
    }
	private void setMasterName(String par1Str)
    {
	        this.dataWatcher.updateObject(19, par1Str);
    }
	public EntityPlayer getMaster()
    {
			return this.worldObj.getPlayerEntityByName(this.getMasterName());
	}
    //AboutCircle
    public float getCircleRange(){
    	return this.dataWatcher.getWatchableObjectFloat(RangeDatawacherNumber);
    }

    private void setCircleExpansionRange(float expansionPar){
    	if(this.MaxRange>this.r)this.r += this.MaxRange/expansionPar;
    		this.dataWatcher.updateObject(this.RangeDatawacherNumber, r);
    }
    public float getCircleRotation(){
    	return this.dataWatcher.getWatchableObjectFloat(CircleRotationNumber);
    }
    private void setCircleRotation(float rotationSpeed){
    	this.Circlerotation += rotationSpeed;
    	if(this.Circlerotation>=360)this.Circlerotation-=1;
    	this.dataWatcher.updateObject(this.CircleRotationNumber, this.Circlerotation);
    }
    //EntityPenetrateFlag
    @Override
	public boolean isPenetrateEntity(){
    	return true;
    }
}
