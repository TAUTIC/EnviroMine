package enviromine.handlers.keybinds;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import enviromine.core.EM_ConfigHandler;
import enviromine.core.EnviroMine;

public class ReloadCustomObjects extends KeyHandler{

	public ReloadCustomObjects(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		super(keyBindings, repeatings);
	}
	private EnumSet<TickType> tickTypes = EnumSet.of(TickType.CLIENT);
	
	

	@Override
	public String getLabel() 
	{
		return "Reload Custom";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,	boolean tickEnd, boolean isRepeat) 
	{

		
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
	{
		
		System.out.println("TEst");
		if(tickEnd)
		{
			System.out.println("TEs t");
			Minecraft mc = Minecraft.getMinecraft();
			if((!(Minecraft.getMinecraft().isSingleplayer()) || !EnviroMine.proxy.isClient()) && Minecraft.getMinecraft().thePlayer != null)
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
				{
					mc.thePlayer.addChatMessage("Single player only function.");
				}
				return;
			}
			// prevents key press firing while gui screen or chat open, if that's what you want
			// if you want your key to be able to close the gui screen, handle it outside this if statement
			if(mc.currentScreen == null)
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
				{
					try
					{
						
						int Total = EM_ConfigHandler.initConfig();
						mc.thePlayer.addChatMessage("Reloading Config: Loaded " + Total +" custom Objects");
						
					} //try
					catch(NullPointerException e)
					{
						mc.thePlayer.addChatMessage("Failed to Load Custom Objects Files.");
					}
				}
				else
				{
					
					mc.thePlayer.addChatMessage("Must hold left shift to reload Custom Objects");
				}
				
				
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return tickTypes;
	}

}
