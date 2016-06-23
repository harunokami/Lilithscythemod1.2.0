package lilithscythemod.Entity.EntityEffect;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityShot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMagicCircle extends EntityShot{

	 public final static String EXT_PROP_NAME = ModCore.MODID + "MagicCircleData";
	 public float MaxRange;
	 public float r;
	 public float s;
	 public float Circlerotation;
	 public float CircleMaxExpansionTime = 20;
	 public EntityLivingBase master;
	 
	 private final int RangeDatawacherNumber = 22;
	 private final int CircleRotationNumber  = 21;
//Construct
	public EntityMagicCircle(World par1World){
		super(par1World);
	}
	public EntityMagicCircle(World par1World,EntityLivingBase par2EntityLivingBase, float range, float rotationSpeed) {
		super(par1World, par2EntityLivingBase);
		this.r = 0;
		this.master= par2EntityLivingBase;
		this.MaxRange =range;
		this.s = rotationSpeed;
		this.setMasterName(par2EntityLivingBase.getCommandSenderName());
		this.Circlerotation=par2EntityLivingBase.rotationYaw;

	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(18, String.valueOf(0));
        this.dataWatcher.addObject(19, "");
		this.dataWatcher.addObject(CircleRotationNumber, Float.valueOf(0F));
		this.dataWatcher.addObject(RangeDatawacherNumber, Float.valueOf(0F));
	}
	/** 毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
    	super.onUpdate();
		if(!this.worldObj.isRemote){
			setCircleExpansionRange(CircleMaxExpansionTime);
			setCircleRotation(this.s);
			this.setPosition(getMaster().posX, getMaster().posY, getMaster().posZ);
		}
    }
	//消滅プロセス
	protected void deadProcess()
    {
		if(!this.worldObj.isRemote && this.getMaster() == null)
		{
			this.setDead();
		}else if(this.getMaster() instanceof EntityPlayer){
    		if( !((EntityPlayer) this.getMaster()).isUsingItem()){
    			this.setDead();
    		}
    		if(this.getMaster().isDead)this.setDead();
    	}
    }
	//SAVE&LOAD
	@Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
    	super.readEntityFromNBT(p_70037_1_);
			if(this.getMaster()!=null)
	        {
	            NBTTagList nbttaglist = this.getEntityData().getTagList("Pos", 6);	            
	            this.setPosition(getMaster().posX, getMaster().posY, getMaster().posZ);
	        }
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
		if(this.master!=null){
			p_70014_1_.setTag("Pos", this.newDoubleNBTList(new double[] {this.master.posX, this.master.posY + (double)this.master.ySize, this.master.posZ}));
		}
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
    	this.dataWatcher.updateObject(this.CircleRotationNumber, this.Circlerotation);
    }

    //EntityPenetrateFlag
    @Override
	public boolean isPenetrateEntity(){
    	return true;
    }
}
