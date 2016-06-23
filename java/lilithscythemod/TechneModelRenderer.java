package lilithscythemod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Techneを使用したModelのRendererクラス（デフォルトのRenderで問題ある場合のみ）
 * */
public class TechneModelRenderer extends ModelRenderer{
	   private int displayList;
	   private boolean compiled;
	   private ModelBase baseModel;

	   	public TechneModelRenderer(ModelBase p_i1173_1_)
	    {
	        this(p_i1173_1_, (String)null);
	    }

	    public TechneModelRenderer(ModelBase par1ModelBase, int par2, int par3)
	    {
	        this(par1ModelBase);
	        this.setTextureOffset(par2, par3);

	    }


	    public TechneModelRenderer(ModelBase p_i1173_1_, String string) {
			super(p_i1173_1_, string);
		}

		@Override
	    @SideOnly(Side.CLIENT)
	    public void render(float par1)
	    {
	        if (!this.isHidden)
	        {
	            if (this.showModel)
	            {
	                if (!this.compiled)
	                {
	                    this.compileDisplayList(par1);
	                }

	                GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
	                int var2;

	                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
	                {
	                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
	                    {
	                        GL11.glCallList(this.displayList);

	                        if (this.childModels != null)
	                        {
	                            for (var2 = 0; var2 < this.childModels.size(); ++var2)
	                            {
	                                ((ModelRenderer)this.childModels.get(var2)).render(par1);
	                            }
	                        }
	                    }
	                    else
	                    {
	                        GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
	                        GL11.glCallList(this.displayList);

	                        if (this.childModels != null)
	                        {
	                            for (var2 = 0; var2 < this.childModels.size(); ++var2)
	                            {
	                                ((ModelRenderer)this.childModels.get(var2)).render(par1);
	                            }
	                        }

	                        GL11.glTranslatef(-this.rotationPointX * par1, -this.rotationPointY * par1, -this.rotationPointZ * par1);
	                    }
	                }
	                else
	                {
	                    GL11.glPushMatrix();
	                    GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
	                   //ここの順番を変更
	                    if (this.rotateAngleY != 0.0F)
	                    {
	                        GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
	                    }

	                    if (this.rotateAngleZ != 0.0F)
	                    {
	                        GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
	                    }


	                    if (this.rotateAngleX != 0.0F)
	                    {
	                        GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
	                    }

	                    GL11.glCallList(this.displayList);

	                    if (this.childModels != null)
	                    {
	                        for (var2 = 0; var2 < this.childModels.size(); ++var2)
	                        {
	                            ((ModelRenderer)this.childModels.get(var2)).render(par1);
	                        }
	                    }

	                    GL11.glPopMatrix();
	                }

	                GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
	            }
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    private void compileDisplayList(float par1)
	    {
	        this.displayList = GLAllocation.generateDisplayLists(1);
	        GL11.glNewList(this.displayList, GL11.GL_COMPILE);
	        Tessellator var2 = Tessellator.instance;

	        for (int var3 = 0; var3 < this.cubeList.size(); ++var3)
	        {
	            ((ModelBox)this.cubeList.get(var3)).render(var2, par1);
	        }

	        GL11.glEndList();
	        this.compiled = true;
	    }


}
