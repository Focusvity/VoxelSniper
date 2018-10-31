package com.thevoxelbox.voxelsniper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

class VoxelSniperUpdater
{
    private Plugin plugin;
    private VoxelSniper.BuildProperties build = VoxelSniper.build;
    private String oldHead = build.head;
    private String path = this.getFilePath();

    VoxelSniperUpdater(Plugin plugin)
    {
        this.plugin = plugin;
    }

    void update()
    {
        try
        {
            String versionLink = "https://focusvity.me/voxelsniper/version.txt";
            URL url = new URL(versionLink);
            URLConnection con = url.openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            if (!reader.ready())
            {
                return;
            }

            String newHead = reader.readLine();
            reader.close();

            if (oldHead.equals("${git.commit.id.abbrev}") || oldHead.equals("unknown"))
            {
                VoxelSniper.getInstance().getLogger().info("No Git head detected, not updating VoxelSniper");
                return;
            }

            if (!newHead.equals(oldHead))
            {
                String dlLink = "https://focusvity.me/voxelsniper/VoxelSniper.jar";
                url = new URL(dlLink);
                con = url.openConnection();
                InputStream in = con.getInputStream();
                FileOutputStream out = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, size);
                }

                out.close();
                in.close();
                VoxelSniper.getInstance().getLogger().info("Update to VoxelSniper applied successfully");
            } else {
                VoxelSniper.getInstance().getLogger().info("No updates are available for VoxelSniper");
            }
        }
        catch (IOException ex)
        {
            VoxelSniper.getInstance().getLogger().info("There was an error applying an update to VoxelSniper");
        }
    }

    private String getFilePath()
    {
        if (plugin instanceof JavaPlugin)
        {
            try
            {
                Method method = JavaPlugin.class.getDeclaredMethod("getFile");
                boolean wasAccessible = method.isAccessible();
                method.setAccessible(true);
                File file = (File)method.invoke(plugin);
                method.setAccessible(wasAccessible);

                return file.getPath();
            }
            catch (Exception e)
            {
                return "plugins" + File.separator + plugin.getName();
            }
        }
        else
        {
            return "plugins" + File.separator + "VoxelSniper.jar";
        }
    }
}