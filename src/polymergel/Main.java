package polymergel;

import cn.nukkit.block.Block;
import cn.nukkit.plugin.PluginBase;
import polymergel.block.Lava;
import polymergel.block.Water;

public class Main extends PluginBase {
	@Override
	public void onLoad() {
		Block.list[Block.WATER] = Water.class;
		Block.list[Block.LAVA] = Lava.class;
	}
}
