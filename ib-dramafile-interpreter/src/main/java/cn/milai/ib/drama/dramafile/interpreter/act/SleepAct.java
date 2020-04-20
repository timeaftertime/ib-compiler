package cn.milai.ib.drama.dramafile.interpreter.act;

import java.io.IOException;

import cn.milai.ib.container.Container;
import cn.milai.ib.drama.dramafile.act.ActType;
import cn.milai.ib.drama.dramafile.interpreter.runtime.Frame;
import cn.milai.ib.util.WaitUtil;

/**
 * 使剧情休眠（无动作）指定帧数的动作
 *
 * 2019.12.20
 *
 * @author milai
 */
public class SleepAct extends AbstractAct {

	/**
	 * 应该休眠的帧数
	 */
	private int sleepFrameIndex;

	@Override
	public ActType getCode() {
		return ActType.SLEEP;
	}

	@Override
	protected void action(Frame frame, Container container) throws Exception {
		long sleepFrame = frame.getClip().getLongConst(sleepFrameIndex);
		WaitUtil.wait(container, sleepFrame);
	}

	@Override
	protected void readOperands(ByteReader reader) throws IOException {
		sleepFrameIndex = reader.readUint16();
	}

}