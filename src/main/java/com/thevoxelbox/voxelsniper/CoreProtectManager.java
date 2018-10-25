package com.thevoxelbox.voxelsniper;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class CoreProtectManager
{

    private static CoreProtectAPI cpApi = null;

    public static CoreProtect getCoreProtect()
    {
        CoreProtect cp = null;

        try
        {
            final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CoreProtect");

            if (plugin != null && plugin instanceof CoreProtect)
            {
                cp = (CoreProtect) plugin;
            }
        }
        catch (Exception ex)
        {
            VoxelSniper.getInstance().getLogger().severe(ex.getMessage());
        }

        return cp;
    }

    public static CoreProtectAPI getCoreProtectAPI()
    {
        if (cpApi == null)
        {
            try
            {
                final CoreProtect cp = getCoreProtect();
                cpApi = cp.getAPI();

                if (!cp.isEnabled() || !cpApi.isEnabled())
                {
                    return null;
                }
            }
            catch (Exception ex)
            {
                VoxelSniper.getInstance().getLogger().severe(ex.getMessage());
            }
        }
        return cpApi;
    }

    public static boolean isEnabled()
    {
        final CoreProtect cp = getCoreProtect();
        return cp != null && cp.isEnabled();
    }
}
