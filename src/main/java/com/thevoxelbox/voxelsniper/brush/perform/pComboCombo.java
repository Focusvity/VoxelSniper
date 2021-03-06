/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import com.thevoxelbox.voxelsniper.CoreProtectManager;
import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * @author Voxel
 */
public class pComboCombo extends vPerformer
{

    private byte d;
    private byte dr;
    private int i;
    private int ir;
    private Player player;

    public pComboCombo()
    {
        name = "Combo-Combo";
    }

    @Override
    public void init(SnipeData v)
    {
        w = v.getWorld();
        d = v.getData();
        dr = v.getReplaceData();
        i = v.getVoxelId();
        ir = v.getReplaceId();
        player = v.owner().getPlayer();
    }

    @Override
    public void info(Message vm)
    {
        vm.performerName(name);
        vm.voxel();
        vm.replace();
        vm.data();
        vm.replaceData();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Block b)
    {
        if (b.getTypeId() == ir && b.getData() == dr)
        {
            if (b.getType() != Material.AIR)
            {
                CoreProtectManager.getCoreProtectAPI().logRemoval(player.getName(), b.getLocation(), b.getType(), b.getData());
            }
            h.put(b);
            b.setTypeIdAndData(i, d, true);
            CoreProtectManager.getCoreProtectAPI().logPlacement(player.getName(), b.getLocation(), Material.getMaterial(i), d);
        }
    }

    @Override
    public boolean isUsingReplaceMaterial()
    {
        return true;
    }
}
