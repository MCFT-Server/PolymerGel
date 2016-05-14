package polymergel.block;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockLiquid;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityCombustByBlockEvent;
import cn.nukkit.event.entity.EntityDamageByBlockEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.BlockColor;

public class Lava extends BlockLiquid {

	protected Lava(int meta) {
		super(meta);
	}

	@Override
	public String getName() {
		return "Lava";
	}

	@Override
	public int getId() {
		return LAVA;
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
        return BlockColor.LAVA_BLOCK_COLOR;
    }
    
    @Override
    public void onEntityCollide(Entity entity) {
        entity.highestPosition -= (entity.highestPosition - entity.y) * 0.5;
        if (!entity.hasEffect(Effect.FIRE_RESISTANCE)) {
            EntityDamageByBlockEvent ev = new EntityDamageByBlockEvent(this, entity, EntityDamageEvent.CAUSE_LAVA, 4);
            entity.attack(ev);
        }

        EntityCombustByBlockEvent ev = new EntityCombustByBlockEvent(this, entity, 15);
        Server.getInstance().getPluginManager().callEvent(ev);
        if (!ev.isCancelled()) {
            entity.setOnFire(ev.getDuration());
        }

        super.onEntityCollide(entity);
    }

	@Override
	public BlockLiquid getBlock(int meta) {
		return new Lava(0);
	}
	
	@Override
	public int onUpdate(int type) {
		return 0;
	}
}
