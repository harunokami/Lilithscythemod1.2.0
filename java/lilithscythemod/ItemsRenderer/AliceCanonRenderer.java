package lilithscythemod.ItemsRenderer;

import lilithscythemod.Weapons.WeaponsModel.AliceCanonModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class AliceCanonRenderer implements IItemRenderer{
	private AliceCanonModel aliceCanonModel;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation tex =new ResourceLocation("lilithscythemod:textures/items/AliceCanon.png");
	public AliceCanonRenderer(){
	 aliceCanonModel= new AliceCanonModel();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED||type == ItemRenderType.EQUIPPED_FIRST_PERSON||type == ItemRenderType.ENTITY ;//装備時だけ
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Entity entity=(Entity)data[1];
		mc.renderEngine.bindTexture(tex);
	    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	    GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	    GL11.glPushMatrix();
	    GL11.glScalef(0.3F,0.3F,0.3F);
	    if(type.compareTo(ItemRenderType.EQUIPPED)==0){
	    GL11.glRotatef(90,1.0F,0.0F,0.0F);
	    GL11.glRotatef(275,0.0F,0.0F,1.0F);
	    GL11.glRotatef(-20,1.0F,0.0F,0.0F);
	    }else if(type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON)){
	    	GL11.glRotatef(90,1.0F,0.0F,0.0F);
		    GL11.glRotatef(275,0.0F,0.0F,1.0F);
		    GL11.glRotatef(40,1.0F,0.0F,0.0F);
		    GL11.glRotatef(-10,0.0F,1.0F,0.0F);
		    
	    }
	    //
	    GL11.glTranslatef(0, 2.5F, -3F);
	    aliceCanonModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
	    GL11.glPopMatrix();
	    GL11.glDisable(GL12.GL_RESCALE_NORMAL);

	}
}
