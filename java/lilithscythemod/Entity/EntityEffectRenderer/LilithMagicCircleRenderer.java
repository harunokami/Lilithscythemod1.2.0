package lilithscythemod.Entity.EntityEffectRenderer;

import lilithscythemod.Entity.EntityEffect.EntityMagicCircle;
import lilithscythemod.Entity.EntityRenderer.RenderLilithscytheEffect;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LilithMagicCircleRenderer extends Render{

	  private ResourceLocation tex =new ResourceLocation("lilithscythemod:textures/particle/LilithMagicCircle.png");

	  public LilithMagicCircleRenderer(){
		  super();
		  this.shadowSize = 0.0F;
	  }
	public void renderCircle(EntityMagicCircle p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) {
		this.bindTexture(tex);
		GL11.glPushMatrix();
    	GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_+0.15F, (float)p_76986_6_);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = 1;
        GL11.glScalef(f2, f2, f2);
        RenderLilithscytheEffect.render(RenderLilithscytheEffect.RenderType_Circle, p_76986_1_.getCircleRange(), 0, p_76986_1_.getCircleRotation(), false, 0, 0);
        this.bindTexture(RenderLilithscytheEffect.MagicLilithFog);
        RenderLilithscytheEffect.render(RenderLilithscytheEffect.RenderType_Cylinder, p_76986_1_.getCircleRange(), 0, p_76986_1_.getCircleRotation(), false, 2, 0);
       	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
	}
	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) {
		this.renderCircle((EntityMagicCircle)p_76986_1_,  p_76986_2_, p_76986_4_,  p_76986_6_,  p_76986_8_, p_76986_9_);
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return tex;
	}


}
