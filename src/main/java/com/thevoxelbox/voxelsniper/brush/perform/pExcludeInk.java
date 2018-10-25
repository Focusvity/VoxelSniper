/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import com.thevoxelbox.voxelsniper.CoreProtectManager;
import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.util.VoxelList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * @author Voxel
 */
public class pExcludeInk extends vPerformer
{

    private VoxelList excludeList;
    private byte data;
    private Player player;

    public pExcludeInk()
    {
        name = "Exclude Ink";
    }

    @Override
    public void info(Message vm)
    {
        vm.performerName(name);
        vm.voxelList();
        vm.data();
    }

    @Override
    public void init(SnipeData v)
    {
        w = v.getWorld();
        data = v.getData();
        excludeList = v.getVoxelList();
        player = v.owner().getPlayer();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Block b)
    {
        if (!excludeList.contains(new int[]{b.getTypeId(), b.getData()}))
        {
            if (b.getType() != Material.AIR)
            {
                CoreProtectManager.getCoreProtectAPI().logRemoval(player.getName(), b.getLocation(), b.getType(), b.getData());
            }
            h.put(b);
            b.setData(data);
            CoreProtectManager.getCoreProtectAPI().logPlacement(player.getName(), b.getLocation(), b.getType(), data);
        }
    }
}
