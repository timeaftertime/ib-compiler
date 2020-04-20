package cn.milai.ib.drama.dramafile.interpreter;

import cn.milai.ib.container.Container;
import cn.milai.ib.drama.AbstractDrama;
import cn.milai.ib.drama.dramafile.interpreter.act.Act;
import cn.milai.ib.drama.dramafile.interpreter.act.ActFactory;
import cn.milai.ib.drama.dramafile.interpreter.act.ByteReader;
import cn.milai.ib.drama.dramafile.interpreter.runtime.Clip;
import cn.milai.ib.drama.dramafile.interpreter.runtime.DramaSpace;
import cn.milai.ib.drama.dramafile.interpreter.runtime.Frame;

/**
 * 通过剧情文件定义的剧本
 * 2019.12.08
 * @author milai
 */
public class DramaFileDrama extends AbstractDrama {

	private static final String BACKGROUND_IMG = "/img/background.jpg";
	private DramaSpace dramaSpace;
	private ByteReader reader;

	public DramaFileDrama(DramaSpace dramaSpace) {
		reader = new ByteReader();
		this.dramaSpace = dramaSpace;
	}

	public String getDramaName() {
		return dramaSpace.getDramaName();
	}

	@Override
	public void run(Container container) {
		container.setBackgroud(image(BACKGROUND_IMG));
		while (!dramaSpace.isFinished() && !Thread.currentThread().isInterrupted()) {
			Frame frame = dramaSpace.currentFrame();
			Clip clip = frame.getClip();
			reader.reset(clip.getBytes(), frame.getPC());
			// 临时结束帧执行的方案，类似 return 命令
			if (!reader.hasMore()) {
				dramaSpace.popCurrentFrame();
				continue;
			}
			frame.synchronizeDramaSpacePC();
			Act act = ActFactory.create(reader.readUint8());
			act.initiailze(reader);
			frame.setPC(reader.getPC());
			act.execute(frame, container);
		}
	}

	@Override
	public String getName() {
		return dramaSpace.getDramaName();
	}

	@Override
	public String getCode() {
		return dramaSpace.getDramaCode();
	}

}