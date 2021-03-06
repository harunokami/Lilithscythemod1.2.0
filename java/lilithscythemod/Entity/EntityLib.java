package lilithscythemod.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lilithscythemod.lilithMath;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLib {

	private static double PosX;
	private static double PosY;
	private static double PosZ;
//初期位置設定
public static void setEntityPos(Entity entity,float adjustX,float adjustY,float adjustZ){
	entity.posX +=setPosX(entity, adjustZ, adjustX);
	entity.posY +=setPosY(entity, adjustY);
	entity.posZ +=setPosZ(entity, adjustZ, adjustX);
}
//初速度を指定
public static void setEntityMotion(Entity entity){
    entity.motionX = (double)(-MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) *
            MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI));
    entity.motionZ = (double)(MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) *
            MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI));
    entity.motionY = (double)(-MathHelper.sin(entity.rotationPitch / 180.0F * (float)Math.PI));
}
//初期状態の向きを決定（なにもずらさない用）
public static void setLocation(Entity entity,EntityLivingBase base)
{
	entity.setLocationAndAngles(base.posX, base.posY +
               (double)base.getEyeHeight(), base.posZ,
               base.rotationYaw , base.rotationPitch );
}


//初期状態の向きを決定(複数弾ずらして撃つ用）
public static void setLocation(Entity entity,EntityLivingBase base,float addYaw,float addPitch)
{
	entity.setLocationAndAngles(base.posX, base.posY +
               (double)base.getEyeHeight(), base.posZ,
               base.rotationYaw + addYaw, base.rotationPitch + addPitch);

}

//SetPosX
public static double setPosX(Entity entity,float adjustZ,float adjustX){
	PosX = -(double)(MathHelper.sin( lilithMath.setrad(entity.rotationYaw) ) * (1.0F + adjustZ))
       		- (double)(MathHelper.cos( lilithMath.setrad(entity.rotationYaw) ) * adjustX);

	return PosX;

}
//SetPosY
public static double setPosY(Entity entity, float adjustY){
	PosY = 0.10000000149011612D + adjustY;
	return PosY;
}
//SetPosZ
public static double setPosZ(Entity entity,float adjustZ,float adjustX){
	PosZ = (double)(MathHelper.cos( lilithMath.setrad(entity.rotationYaw) ) * (1.0F + adjustZ))
		- (double)(MathHelper.sin( lilithMath.setrad(entity.rotationYaw) ) * adjustX);
	return PosZ;
}

/**
 * 指定した値を速度にセット
 * @param entity
 * @param x
 * @param y
 * @param z
 */
public static void setEntityMotion(Entity entity, double x,double y,double z){
	entity.motionX = x;
	entity.motionY = y;
	entity.motionZ = z;

}
/**
 * Entityの速度、角度などを設定する
 * @param entity
 * @param p_70186_1_
 * @param p_70186_3_
 * @param p_70186_5_
 * @param p_70186_7_
 * @param p_70186_8_
 */
public static void EntityThrowableHeading(Entity entity,double p_70186_1_, double p_70186_3_,double p_70186_5_, float p_70186_7_, float p_70186_8_){
	float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
	 p_70186_1_ /= (double)f2;
	 p_70186_3_ /= (double)f2;
	 p_70186_5_ /= (double)f2;
     p_70186_1_ *= (double)p_70186_7_;
     p_70186_3_ *= (double)p_70186_7_;
     p_70186_5_ *= (double)p_70186_7_;
       entity.motionX = p_70186_1_;
       entity.motionY = p_70186_3_;
       entity.motionZ = p_70186_5_;
       float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
       entity.prevRotationYaw = entity.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
       entity.prevRotationPitch = entity.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);

}

/**
 * ブロックにあたっているかどうか
 * @param entity
 * @param xTile
 * @param yTile
 * @param zTile
 * @return
 */
public static Boolean getBoundBlock(Entity entity,int xTile,int yTile,int zTile){
	Boolean bool = null;
	 //激突したブロックを確認している
     Block block = entity.worldObj.getBlock(xTile, yTile, zTile);
     //当たり判定に接触しているかどうか
     if (block.getMaterial() != Material.air){
    	 block.setBlockBoundsBasedOnState(entity.worldObj, xTile, yTile, zTile);
     	AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(entity.worldObj, xTile, yTile, zTile);
     	 if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ)))
         {
           return true;
         }

     }
	    return bool;

}
/**
 * 位置更新メソッド
 * @param entity
 * @param addX
 * @param addY
 * @param addZ
 */
public static void updatePos(Entity entity,double addX,double addY, double addZ){
	entity.posX += addX;
	entity.posY += addY;
	entity.posZ += addZ;
}
/**
 *
 * @param world :SearchWorld
 * @param attacker:AttackEntity
 * @param HeightRange:Height
 * @param Range:EntityRange
 * @param LeftRange
 * @param RightRange
 * @return Entity<List>
 */
public static List<EntityLiving> getSearchEntity(World world,EntityLivingBase attacker,int HeightRange,double Range,float LeftRange,float RightRange){
	Minecraft lmc = Minecraft.getMinecraft();
	  EntityLiving targetEntity=null;
	  List<EntityLiving> entityList = new ArrayList();
    if (!world.isRemote)
    {
   		 // 攻撃判定
   		 Entity lentity = null;
   			 if (lmc != null && lmc.objectMouseOver != null) {
   				 lentity = lmc.objectMouseOver.entityHit;
   			 }
   			 AxisAlignedBB var19 = attacker.boundingBox.expand(Range, HeightRange, Range);
   			 Iterator var20 = attacker.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();

   			 while(var20.hasNext()){
   				 targetEntity = (EntityLiving) var20.next();
   				 if ((Entity)targetEntity instanceof EntityPlayer) continue;
   				 if(!((Entity)targetEntity instanceof IMob)) continue;
   				 // 射程距離の判定、MOBの大きさを考慮
   				 double lln = Range + (double)targetEntity.width;
   				 lln *= lln;

   				 if (attacker.getDistanceSqToEntity(targetEntity)<=lln) {
   					 	// 範囲攻撃の対象
   					 double lvx = targetEntity.posX - attacker.posX;
   					 double lvz = attacker.posZ - targetEntity.posZ;
   					 float lyaw = (float)Math.toDegrees(Math.atan2(lvx, lvz));
   					 float lf = attacker.rotationYaw - lyaw;
   					 for (;lf > 360F; lf -= 360);
   					 for (;lf < 0F; lf += 360);
   					 float left=0;
   					 float right=0;
   					 if(LeftRange>0&&RightRange>0)left=180F + LeftRange; right = 180F - RightRange;

   					 if (lf > right && lf < left) {
   						entityList.add(targetEntity);
   					 }

   				 }
               }
  }
	return entityList;
}




}
