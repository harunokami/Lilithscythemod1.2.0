package lilithscythemod.Entity.EntityEffectRenderer;

import lilithscythemod.Enum.EnumEffectType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class EntityMoonOfChainEffect  extends Render{

	 private ResourceLocation tex =EnumEffectType.getEffectTextureLocation("MOONOFCHAIN");

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_)
	{
		this.bindTexture(tex);
		GL11.glPushMatrix();
    	GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_ + p_76986_1_.getEyeHeight(), (float)p_76986_6_);
    	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    	GL11.glScalef(1, 1, 1);
    	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {

		return tex;
	}

}
