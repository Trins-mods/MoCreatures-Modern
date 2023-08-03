/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class MoCBlockGrass extends Block {

    public MoCBlockGrass(MapColor mapColor) {
        super(Material.GRASS, mapColor);
        setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
    }

    public MoCBlockGrass(Material material, MapColor mapColor) {
        super(material, mapColor);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, MoCBlocks.wyvdirt.getDefaultState());
            } else {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        IBlockState blockstate = worldIn.getBlockState(blockpos1.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

                        if (iblockstate1.getBlock() == MoCBlocks.wyvdirt && worldIn.getLightFromNeighbors(blockpos1.up()) >= 4
                                && blockstate.getLightOpacity(worldIn, blockpos1.up()) <= 2) {
                            worldIn.setBlockState(blockpos1, MoCBlocks.wyvgrass.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
