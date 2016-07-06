package lilithscythemod.Entity.EntityRenderer;

import lilithscythemod.Entity.EntityModel.ModelSolid;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderLilithscytheEffect {
	//RenderTexture
	 public static final ResourceLocation AcidBreakTextures =new ResourceLocation("lilithscythemod:textures/particle/AcidBreak.png");
	 public static final ResourceLocation MagicLilithCircleTextures =new ResourceLocation("lilithscythemod:textures/particle/LilithMagicCircle.png");
	 public static final ResourceLocation MagicLilithFog =new ResourceLocation("lilithscythemod:textures/particle/Lilithfog.png");

	 //RenderType
	    public static final byte RenderType_Circle = 0;
		public static final byte RenderType_Band = 1;
		public static final byte RenderType_Beam = 2;
		public static final byte RenderType_Sphere = 3;
		public static final byte RenderType_Cylinder = 4;
		public static final byte RenderType_Spark = 5;
		public static final byte RenderType_EffectA = 6;
		public static final byte RenderType_EffectB = 7;

		public static void render(byte renderType, double r, double length, float rotation, boolean permeate, int par0, int par1)
		{
			GL11.glPushMatrix();
			if(permeate)
			{
				GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		        GL11.glDisable(GL11.GL_LIGHTING);
		        GL11.glDepthMask(true);
		        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		        int i = 15728880;
		        int j = i % 65536;
		        int k = i / 65536;
		        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			}
			else
			{
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_BLEND);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            GL11.glDepthMask(false);
			}

			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			int alpha = permeate ? 128 : 255;

			switch(renderType)
			{
			case RenderType_Circle:renderCircle(r, alpha,par0);break;
			case RenderType_Sphere:renderSphere(r, rotation, alpha, par0);break;
			case RenderType_Cylinder:renderCylinder(r, rotation, alpha, par0);break;
			}

			if(permeate)
			{
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
		        GL11.glDepthMask(true);
			}
			else
			{
				GL11.glDepthMask(true);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LIGHTING);

			}
			GL11.glPopMatrix();
		}
		/**
		 * 円を描画
		 * @param r
		 * @param color
		 * @param alpha
		 */
		private static void renderCircle(double r, int alpha,int textureType)
		{
			double minU = 0.0F;
	        double maxU = 1.0F;
	        double minV = 0.0F;
	        double maxV = 1.0F;
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();

	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV(-r, 0.0D, -r, minU, minV);
	        tessellator.addVertexWithUV(-r, 0.0D, r, maxU, minV);
	        tessellator.addVertexWithUV(r, 0.0D, r, maxU, maxV);
	        tessellator.addVertexWithUV(r, 0.0D, -r, minU, maxV);

	        tessellator.addVertexWithUV(r, 0.0D, -r, minU, minV);
	        tessellator.addVertexWithUV(r, 0.0D, r, maxU, minV);
	        tessellator.addVertexWithUV(-r, 0.0D, r, maxU, maxV);
	        tessellator.addVertexWithUV(-r, 0.0D, -r, minU, maxV);

	        tessellator.draw();
		}
		/**
		 * 円柱を描画(作成中）
		 * @param r
		 * @param color
		 * @param alpha
		 */
		private static void renderCylinder(double r, float alpha,int textureType,int height)
		{
			double[][] sp = ModelSolid.Cylinder;
			double minU = 0.0F;
	        double maxU = 1.0F;
	        double minV = 0.0F;
	        double maxV = 1.0F;
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();

	        for(int Size=0;Size<ModelSolid.Cylinder.length-1;Size++){
	        	tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        	tessellator.addVertexWithUV(sp[Size+1][0]*r,height,sp[Size+1][1]*r, minU, minV);
	        	tessellator.addVertexWithUV(sp[Size][0]*r,height,sp[Size][1]*r, maxU, minV);
	        	tessellator.addVertexWithUV(sp[Size][0]*r,0.0D,sp[Size][1]*r,  maxU, maxV);
	        	tessellator.addVertexWithUV(sp[Size+1][0]*r,0.0D,sp[Size+1][1]*r, minU, maxV);
	        	
	        	tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        	tessellator.addVertexWithUV(sp[Size+1][0]*r,0.0D,sp[Size+1][1]*r, minU, maxV);
	 	        tessellator.addVertexWithUV(sp[Size][0]*r,0.0D,sp[Size][1]*r,maxU, maxV);
	 	        tessellator.addVertexWithUV(sp[Size][0]*r,height,sp[Size][1]*r, maxU, minV);
	 	        tessellator.addVertexWithUV(sp[Size+1][0]*r,height,sp[Size+1][1]*r, minU, minV);
	        }
	        tessellator.draw();
		}
		/**
		 * 球体を描画
		 * @param r
		 * @param rotation
		 * @param alpha
		 * @param changeColor
		 */
		private static void renderSphere(double r, float rotation, int alpha, int changeColor)
		{
			float[][] sp = ModelSolid.sphere;
			double minU = 0.0D;
			double maxU = 1.0D;
			double minV = 0.0D;
			double maxV = 1.0D;
			int c1;

			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			for(int i0 = 0; i0 < 8; ++i0)
			{
				for(int i1 = 0; i1 < 16; ++i1)
				{
					int ii0 = i0 * 16 + i1;
					int ii1 = (i0 + 1) * 16 + i1;
					int ii2 = (i0 + 1) * 16 + (i1 + 1) % 16;
					int ii3 = i0 * 16 + (i1 + 1) % 16;

			        tessellator.setNormal(0.0F, 1.0F, 0.0F);
			        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r, sp[ii0][2] * r, maxU, maxV);
			        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r, sp[ii1][2] * r, maxU, minV);
			        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r, sp[ii2][2] * r, minU, minV);
			        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r, sp[ii3][2] * r, minU, maxV);

			        tessellator.setNormal(0.0F, 1.0F, 0.0F);
			        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r, sp[ii3][2] * r, maxU, maxV);
			        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r, sp[ii2][2] * r, maxU, minV);
			        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r, sp[ii1][2] * r, minU, minV);
			        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r, sp[ii0][2] * r, minU, maxV);
				}
			}
			tessellator.draw();
		}
}
