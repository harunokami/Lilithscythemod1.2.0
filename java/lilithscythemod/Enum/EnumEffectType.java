package lilithscythemod.Enum;

import net.minecraft.util.ResourceLocation;

public enum EnumEffectType {

	Nothing("nothing",0,new ResourceLocation(""))
	,WAVE("WAVE",1000,new ResourceLocation(""))
	,BEAM("BEAM",5,new ResourceLocation(""))
	,CIRCLE("サークル",1000,new ResourceLocation(""))
	,LILITHCIRCLE("LilithMagicCircle",1000,new ResourceLocation("lilithscythemod:textures/particle/LilithMagicCircle.png"))
	,MOONOFCHAIN("MoonOfChain",1000,new ResourceLocation("lilithscythemod:textures/particle/MoonOfChain.png"))
	,HEAL("回復",50,new ResourceLocation(""));

	public final String name;
	public final int life;
	public final ResourceLocation Location;

	private EnumEffectType(String name, int life,ResourceLocation location)
	{
		this.name = name;
		this.life = life;
		this.Location=location;
	}
    public static EnumEffectType getEffectTypeFromString(String par1)
    {
    	EnumEffectType[] enumArray = values();
        for(EnumEffectType enumA : enumArray)
        {
            if(enumA.toString().equals(par1))
            {
                return enumA;
            }
        }
        return Nothing;
    }
    public static ResourceLocation getEffectTextureLocation(String par1)
    {
    	EnumEffectType[] enumArray = values();
        for(EnumEffectType enumA : enumArray)
        {
            if(enumA.toString().equals(par1))
            {
                return enumA.Location;
            }
        }
        return Nothing.Location;
    }

}
