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
public class pIncludeMat extends vPerformer
{

    private VoxelList includeList;
    private int id;
    private Player player;

    public pIncludeMat()
    {
        name = "Include Material";
    }

    @Override
    public void info(Message vm)
    {
        vm.performerName(name);
        vm.voxelList();
        vm.voxel();
    }

    @Override
    public void init(SnipeData v)
    {
        w = v.getWorld();
        id = v.getVoxelId();
        includeList = v.getVoxelList();
        player = v.owner().getPlayer();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Block b)
    {
        if (includeList.contains(new int[]{b.getTypeId(), b.getData()}))
        {
            if (b.getType() != Material.AIR)
            {
                CoreProtectManager.getCoreProtectAPI().logRemoval(player.getName(), b.getLocation(), b.getType(), b.getData());
            }
            h.put(b);
            b.setTypeId(id);
            CoreProtectManager.getCoreProtectAPI().logPlacement(player.getName(), b.getLocation(), Material.getMaterial(id), b.getData());
        }
    }
}
