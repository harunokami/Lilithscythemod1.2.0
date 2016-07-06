package lilithscythemod.Weapons;

import java.util.Random;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityLib;
import lilithscythemod.Entity.EntityEffect.EntityMagicCircle;
import lilithscythemod.Potion.PotionEffectManager;
import lilithscythemod.Skill.SkillManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class ItemLilithScythe  extends LilithscytheWeapons
{

	//初期設定
    public ItemLilithScythe(net.minecraft.item.Item.ToolMaterial par1)
    {
        super(par1);
        //リリスサイズの登録
        setTextureName("lilithscythemod:Lilithscythe");
        this.setMaxDamage(-1);//壊れない

    }
  //Randomクラスのインスタンス化
    Random rnd = new Random();

	//機能
    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
    	//Potionの効果時間（【20tick ≒ 1秒】なので）
		int duration = 20 * 20;
		//PotionのLv
		int amplifier = 20;
    	 ((EntityLivingBase) p_77644_2_).addPotionEffect(new PotionEffect(Potion.wither.id,duration,amplifier));
         PotionEffectManager.applyEffect((EntityLivingBase)p_77644_2_, ModCore.MODID, "BreakProtect", duration, amplifier);
		return true;

    }
	@Override
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		super.onUpdate(itemStack, world, entity, slot, isHeld);
		 if (EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId,itemStack)<=1) {
	            itemStack.addEnchantment(Enchantment.looting, 5);
	            itemStack.addEnchantment(Enchantment.featherFalling, 10);
	            itemStack.addEnchantment(Enchantment.respiration, 3);
		 }
		 rnd = new Random();
		/* if(entity instanceof EntityPlayer){
			 if( ((EntityPlayer) entity).getItemInUseCount()>0 && ((EntityPlayer) entity).getItemInUse().getItem() instanceof ItemLilithScythe){
				//Randomクラスのインスタンス化
		         Random rnd = new Random();
		         for (int l = 0; l < 10; ++l)
		         {
		             double d0 = (double)((float)entity.posX + rnd.nextFloat()-0.1F);
		             double d1 = (double)((float)entity.posY + rnd.nextFloat()-0.1F);
		             double d2 = (double)((float)entity.posZ + rnd.nextFloat()-0.1F);
		             double d3 = 0.0D;
		             double d4 = 0.0D;
		             double d5 = 0.0D;
		             int i1 = rnd.nextInt(2) * 2 - 1;
		             d3 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
		             d4 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
		             d5 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;

		             d0 = (double)entity.posX + 0.25D * (double)i1;
		             d3 = (double)(rnd.nextFloat() * 2.0F * (float)i1);
		                 world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		         }
			 }
		 }*/
	    }
	//SkillChange
    /**
     * 左クリックの処理
     */
	    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer par3EntityPlayer, Entity par1entity)
	    {
           return super.onLeftClickEntity(stack, par3EntityPlayer, par1entity);
	    }


	    /**
	     * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
	     */
	    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	    {
	    	int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

	     float f = (float)j / 20.0F;
	     f = (f * f + f * 2.0F) / 3.0F;

	     if ((double)f < 3.0D)
	     {
	         return;
	     }

	     if (f > 1.0F)
	     {
	         f = 1.0F;
	     }
	     	for(EntityLiving entity : EntityLib.getSearchEntity(par2World, (EntityLivingBase)par3EntityPlayer, 10, 10D, 80F, 60F)){
	     		if(entity==null)return;
	  			 if(par3EntityPlayer instanceof EntityPlayer) ((EntityPlayer) par3EntityPlayer).attackTargetEntityWithCurrentItem(entity);
	  			entity.attackEntityFrom(DamageSource.generic,ModCore.LilithMaterial.getDamageVsEntity());
	  		}

	     }

	    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	    	return par1ItemStack;
	    }

	    /**
	     * 右クリックでの使用（タメ）時間の上限。
	     */
	    public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }
	    /**
	     * 右クリック時の動作のタイプ。
	     */
	    public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.bow;
	    }

	    /**
	     * 右クリックでの使用時に呼ばれるメソッド。
	     */
	    public  ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {

			//Potionの効果時間（【20tick ≒ 1秒】なので）
			int duration = 30 * 20;

			//PotionのLv
			int SpeedLv = 4;
			int JumpLv = 2;

			//PotionEffectの設定
			PotionEffect Speed = new PotionEffect(Potion.moveSpeed.id, duration, SpeedLv);
			PotionEffect Jump= new PotionEffect(Potion.jump.id, duration, JumpLv);
			PotionEffect FireResistance=new PotionEffect(Potion.fireResistance.id,duration,1);

			//PotionEffect(Effect)がEntityPlayerに付与されているかの判定
			boolean isMoveSpeed = par3EntityPlayer.isPotionActive(Speed.getPotionID());
			boolean isJump = par3EntityPlayer.isPotionActive(Jump.getPotionID());

			//PotionEffect(Effect)がEntityPlayerに付与されていない場合
			if( !isMoveSpeed||!isJump)
			{
				//Itemを振る動作
				par3EntityPlayer.swingItem();

				//ダメージ値を【1】増やす
				par1ItemStack.damageItem(1, par3EntityPlayer);

				//PotionEffect(Effect)をEntityPlayerに付与
				par3EntityPlayer.addPotionEffect(Speed);
				par3EntityPlayer.addPotionEffect(Jump);
				par3EntityPlayer.addPotionEffect(FireResistance);
			}
			 SkillManager.applyEffect(par3EntityPlayer, ModCore.MODID, "MisChiefPumpkin");
			//呼ばれた時間をpar1ItemStackに格納
	    	 par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	    	 EntityMagicCircle lilithcircle = new EntityMagicCircle(par3EntityPlayer.worldObj, par3EntityPlayer,3.0F,3.0F);
	         if(!par3EntityPlayer.worldObj.isRemote){
	        	 par3EntityPlayer.worldObj.spawnEntityInWorld(lilithcircle);
	         }
		     return par1ItemStack;
	    }

		public float getExtendedReach(ItemStack itemstack) {
			return 7;
		}
		@Override
		public int getRiseDamage(){
			return 60;
		}

}


