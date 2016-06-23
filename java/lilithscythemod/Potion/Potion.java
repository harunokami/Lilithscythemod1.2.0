package lilithscythemod.Potion;

import net.minecraft.entity.EntityLivingBase;

public abstract class Potion {

	/**
	 * @return 登録するPotion名
	 */
	public abstract String getName();
	/**
	 * Potionによる効果を処理するメソッド
	 */
	public abstract void effect(EntityLivingBase target, int powerLevel);

	/**
	 * デフォルトのミルクによるバフ解除効果を受けるかどうか
	 * @return true:解除する false:解除しない
	 */
	public boolean milkRemove()
	{
		return true;
	}


}
