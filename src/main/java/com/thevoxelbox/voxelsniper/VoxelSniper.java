package com.thevoxelbox.voxelsniper;

import com.thevoxelbox.voxelsniper.brush.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.util.Properties;

/**
 * Bukkit extension point.
 */
public class VoxelSniper extends JavaPlugin
{
    public static final BuildProperties build = new BuildProperties();
    private static VoxelSniper instance;
    private final VoxelSniperListener voxelSniperListener = new VoxelSniperListener(this);
    private SniperManager sniperManager = new SniperManager(this);
    private VoxelSniperConfiguration voxelSniperConfiguration;
    private Brushes brushManager = new Brushes();

    /**
     * @return {@link VoxelSniper}
     */
    public static VoxelSniper getInstance()
    {
        return VoxelSniper.instance;
    }

    /**
     * Returns {@link com.thevoxelbox.voxelsniper.Brushes} for current instance.
     *
     * @return Brush Manager for current instance.
     */
    public Brushes getBrushManager()
    {
        return brushManager;
    }

    /**
     * Returns object for accessing global VoxelSniper options.
     *
     * @return {@link VoxelSniperConfiguration} object for accessing global VoxelSniper options.
     */
    public VoxelSniperConfiguration getVoxelSniperConfiguration()
    {
        return voxelSniperConfiguration;
    }

    /**
     * Returns {@link com.thevoxelbox.voxelsniper.SniperManager} for current instance.
     *
     * @return SniperManager
     */
    public SniperManager getSniperManager()
    {
        return sniperManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
    {
        if (sender instanceof Player)
        {
            String[] arguments = args;

            if (arguments == null)
            {
                arguments = new String[0];
            }

            return voxelSniperListener.onCommand((Player) sender, arguments, command.getName());
        }

        getLogger().info("Only Players can execute commands.");
        return true;
    }

    @Override
    public void onEnable()
    {
        VoxelSniper.instance = this;

        build.load(VoxelSniper.getInstance());
        registerBrushes();
        getLogger().info("Registered " + brushManager.registeredSniperBrushes() + " Sniper Brushes with " + brushManager.registeredSniperBrushHandles() + " handles.");

        saveDefaultConfig();
        voxelSniperConfiguration = new VoxelSniperConfiguration(getConfig());

        Bukkit.getPluginManager().registerEvents(this.voxelSniperListener, this);
        getLogger().info("Registered Sniper Listener.");
    }

    @Override
    public void onDisable()
    {
        VoxelSniperUpdater updater = new VoxelSniperUpdater(VoxelSniper.getInstance());
        updater.update();
    }

    /**
     * Registers all brushes.
     */
    public void registerBrushes()
    {
        brushManager.registerSniperBrush(BallBrush.class, "b", "ball");
        brushManager.registerSniperBrush(BiomeBrush.class, "bio", "biome");
        brushManager.registerSniperBrush(BlendBallBrush.class, "bb", "blendball");
        brushManager.registerSniperBrush(BlendDiscBrush.class, "bd", "blenddisc");
        brushManager.registerSniperBrush(BlendVoxelBrush.class, "bv", "blendvoxel");
        brushManager.registerSniperBrush(BlendVoxelDiscBrush.class, "bvd", "blendvoxeldisc");
        brushManager.registerSniperBrush(BlobBrush.class, "blob", "splatblob");
        brushManager.registerSniperBrush(BlockResetBrush.class, "brb", "blockresetbrush");
        brushManager.registerSniperBrush(BlockResetSurfaceBrush.class, "brbs", "blockresetbrushsurface");
        brushManager.registerSniperBrush(CanyonBrush.class, "ca", "canyon");
        brushManager.registerSniperBrush(CanyonSelectionBrush.class, "cas", "canyonselection");
        brushManager.registerSniperBrush(CheckerVoxelDiscBrush.class, "cvd", "checkervoxeldisc");
        brushManager.registerSniperBrush(CleanSnowBrush.class, "cls", "cleansnow");
        brushManager.registerSniperBrush(CloneStampBrush.class, "cs", "clonestamp");
        brushManager.registerSniperBrush(CometBrush.class, "com", "comet");
        brushManager.registerSniperBrush(CopyPastaBrush.class, "cp", "copypasta");
        brushManager.registerSniperBrush(CylinderBrush.class, "c", "cylinder");
        brushManager.registerSniperBrush(DiscBrush.class, "d", "disc");
        brushManager.registerSniperBrush(DiscFaceBrush.class, "df", "discface");
        brushManager.registerSniperBrush(DomeBrush.class, "dome", "domebrush");
        brushManager.registerSniperBrush(DrainBrush.class, "drain");
        brushManager.registerSniperBrush(EllipseBrush.class, "el", "ellipse");
        brushManager.registerSniperBrush(EllipsoidBrush.class, "elo", "ellipsoid");
        brushManager.registerSniperBrush(EntityBrush.class, "en", "entity");
        brushManager.registerSniperBrush(EntityRemovalBrush.class, "er", "entityremoval");
        brushManager.registerSniperBrush(EraserBrush.class, "erase", "eraser");
        brushManager.registerSniperBrush(ErodeBrush.class, "e", "erode");
        brushManager.registerSniperBrush(ExtrudeBrush.class, "ex", "extrude");
        brushManager.registerSniperBrush(FillDownBrush.class, "fd", "filldown");
        brushManager.registerSniperBrush(FlatOceanBrush.class, "fo", "flatocean");
        brushManager.registerSniperBrush(GenerateTreeBrush.class, "gt", "generatetree");
        brushManager.registerSniperBrush(HeatRayBrush.class, "hr", "heatray");
        brushManager.registerSniperBrush(JaggedLineBrush.class, "j", "jagged");
        brushManager.registerSniperBrush(JockeyBrush.class, "jockey");
        brushManager.registerSniperBrush(LightningBrush.class, "light", "lightning");
        brushManager.registerSniperBrush(LineBrush.class, "l", "line");
        brushManager.registerSniperBrush(MoveBrush.class, "mv", "move");
        brushManager.registerSniperBrush(OceanBrush.class, "o", "ocean");
        brushManager.registerSniperBrush(OverlayBrush.class, "over", "overlay");
        brushManager.registerSniperBrush(PaintingBrush.class, "paint", "painting");
        brushManager.registerSniperBrush(PullBrush.class, "pull");
        brushManager.registerSniperBrush(PunishBrush.class, "p", "punish");
        brushManager.registerSniperBrush(RandomErodeBrush.class, "re", "randomerode");
        brushManager.registerSniperBrush(RegenerateChunkBrush.class, "gc", "generatechunk");
        brushManager.registerSniperBrush(RingBrush.class, "ri", "ring");
        brushManager.registerSniperBrush(Rot2DBrush.class, "rot2", "rotation2d");
        brushManager.registerSniperBrush(Rot2DvertBrush.class, "rot2v", "rotation2dvertical");
        brushManager.registerSniperBrush(Rot3DBrush.class, "rot3", "rotation3d");
        brushManager.registerSniperBrush(RulerBrush.class, "r", "ruler");
        brushManager.registerSniperBrush(ScannerBrush.class, "sc", "scanner");
        brushManager.registerSniperBrush(SetBrush.class, "set");
        brushManager.registerSniperBrush(SetRedstoneFlipBrush.class, "setrf", "setredstoneflip");
        brushManager.registerSniperBrush(ShellBallBrush.class, "shb", "shellball");
        brushManager.registerSniperBrush(ShellSetBrush.class, "shs", "shellset");
        brushManager.registerSniperBrush(ShellVoxelBrush.class, "shv", "shellvoxel");
        brushManager.registerSniperBrush(SignOverwriteBrush.class, "sio", "signoverwriter");
        brushManager.registerSniperBrush(SnipeBrush.class, "s", "snipe");
        brushManager.registerSniperBrush(SnowConeBrush.class, "snow", "snowcone");
        brushManager.registerSniperBrush(SpiralStaircaseBrush.class, "sstair", "spiralstaircase");
        brushManager.registerSniperBrush(SplatterBallBrush.class, "sb", "splatball");
        brushManager.registerSniperBrush(SplatterDiscBrush.class, "sd", "splatdisc");
        brushManager.registerSniperBrush(SplatterOverlayBrush.class, "sover", "splatteroverlay");
        brushManager.registerSniperBrush(SplatterVoxelBrush.class, "sv", "splattervoxel");
        brushManager.registerSniperBrush(SplatterDiscBrush.class, "svd", "splatvoxeldisc");
        brushManager.registerSniperBrush(SplineBrush.class, "sp", "spline");
        brushManager.registerSniperBrush(StencilBrush.class, "st", "stencil");
        brushManager.registerSniperBrush(StencilListBrush.class, "sl", "stencillist");
        brushManager.registerSniperBrush(ThreePointCircleBrush.class, "tpc", "threepointcircle");
        brushManager.registerSniperBrush(TreeSnipeBrush.class, "t", "tree", "treesnipe");
        brushManager.registerSniperBrush(TriangleBrush.class, "tri", "triangle");
        brushManager.registerSniperBrush(UnderlayBrush.class, "under", "underlay");
        brushManager.registerSniperBrush(VoltMeterBrush.class, "volt", "voltmeter");
        brushManager.registerSniperBrush(VoxelBrush.class, "v", "voxel");
        brushManager.registerSniperBrush(VoxelDiscBrush.class, "vd", "voxeldisc");
        brushManager.registerSniperBrush(VoxelDiscFaceBrush.class, "vdf", "voxeldiscface");
        brushManager.registerSniperBrush(WarpBrush.class, "w", "warp");
    }

    public static class BuildProperties
    {

        public String head;

        public void load(VoxelSniper plugin)
        {
            try
            {
                final Properties gitprops;
                try (InputStream in = plugin.getResource("git.properties"))
                {
                    gitprops = new Properties();
                    gitprops.load(in);
                }

                head = gitprops.getProperty("git.commit.id.abbrev", "unknown");
            }
            catch (Exception ex)
            {
                VoxelSniper.getInstance().getLogger().severe("Could not load build properties! Did you compile with Netbeans/Maven?");
                VoxelSniper.getInstance().getLogger().severe(ex.toString());
            }
        }
    }
}
