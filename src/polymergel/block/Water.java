package polymergel.block;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockLiquid;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

public class Water extends BlockLiquid {

	protected Water(int meta) {
		super(meta);
	}

	@Override
	public String getName() {
		return "Water";
	}

	@Override
	public int getId() {
		return WATER;
	}
	
	@Override
    public boolean place(Item item, Block block, Block target, int face, double fx, double fy, double fz) {
        return this.place(item, block, target, face, fx, fy, fz, null);
    }

    @Override
    public boolean place(Item item, Block block, Block target, int face, double fx, double fy, double fz, Player player) {
        return this.getLevel().setBlock(this, this, true, false);
    }
    
    @Override
    public BlockColor getColor() {
        return BlockColor.WATER_BLOCK_COLOR;
    }

    @Override
    public void onEntityCollide(Entity entity) {
        super.onEntityCollide(entity);

        if (entity.fireTicks > 0) {
            entity.extinguish();
        }
    }

	@Override
	public BlockLiquid getBlock(int meta) {
		return new Water(0);
	}
	
	@Override
	public int onUpdate(int type) {
		return 0;
	}
}
