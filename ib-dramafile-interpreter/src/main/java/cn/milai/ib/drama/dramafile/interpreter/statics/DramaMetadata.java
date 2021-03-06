package cn.milai.ib.drama.dramafile.interpreter.statics;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.common.io.InputStreams;
import cn.milai.ib.ex.IBIOException;

/**
 * 剧本元数据，对应 .drama 文件
 * 2019.12.29
 * @author milai
 */
public class DramaMetadata {

	/**
	 * 魔数 Infiniy Battle，一个固定 MUTF8 字符串，2 个字节长度，2个字节字符串
	 */
	private static final String MAGIC_NUMBER = "IFNTBT";

	/**
	 * 主版本号，2 个字节无符号数
	 */
	private int majorVersion;

	/**
	 * 次版本号，2 个字节无符号数
	 */
	private int minorVersion;

	/**
	 * 剧本唯一标识 常量池序号
	 */
	private int dramaCodeIndex;

	/**
	 * 剧本可读名 常量池序号
	 */
	private int dramaNameIndex;

	/**
	 * 常量池
	 */
	private ConstantPool pool;

	private byte[] clipBytes;

	public DramaMetadata(byte[] data) {
		DataInputStream reader = new DataInputStream(new ByteArrayInputStream(data));
		try {
			readMagicNumber(reader);
			readVerions(reader);
			readConstantPool(reader);
			readDramaCodeAndName(reader);
			readMethods(reader);
		} catch (NumberFormatException e) {
			throw new IBIOException(e);
		} catch (IOException e) {
			throw new IBIOException(e);
		}
	}

	private void readMagicNumber(DataInputStream reader) throws NumberFormatException, IOException {
		if (!MAGIC_NUMBER.equals(reader.readUTF())) {
			throw new DramaFileFormatException();
		}
	}

	private void readVerions(DataInputStream reader) throws IOException {
		majorVersion = reader.readUnsignedShort();
		minorVersion = reader.readUnsignedShort();
	}

	private void readDramaCodeAndName(DataInputStream reader) throws IOException {
		dramaCodeIndex = reader.readUnsignedShort();
		dramaNameIndex = reader.readUnsignedShort();
	}

	private void readConstantPool(DataInputStream reader) throws IOException {
		this.pool = new ConstantPool(reader);
	}

	private void readMethods(DataInputStream reader) throws IOException {
		// 剧本片段个数，现固定为 1
		reader.readUnsignedShort();
		// 剧本片段名序号，现固定为 1
		reader.readUnsignedShort();
		// 剧本片段描述符序号，现固定为 2
		reader.readUnsignedShort();
		// 剧本片段长度
		int len = reader.readUnsignedShort();
		clipBytes = new byte[len];
		InputStreams.toBytes(reader, clipBytes);
	}

	/**
	 * 获取剧情的字节数组
	 * @return
	 */
	public byte[] getClipBytes() { return clipBytes; }

	/**
	 * 获取下标为 index 的 int 类型常量
	 * @param index
	 * @return
	 */
	public int getIntConst(int index) {
		return pool.getInt(index).getValue();
	}

	/**
	 * 获取下标为 index 的 long 类型常量
	 * @param index
	 * @return
	 */
	public long getLongConst(int index) {
		return pool.getLong(index).getValue();
	}

	/**
	 * 获取下标为 index 的 float 类型常量
	 * @param index
	 * @return
	 */
	public float getFloatConst(int index) {
		return pool.getFloat(index).getValue();
	}

	/**
	 * 获取下标为 index 的 String 类型常量
	 * @param index
	 * @return
	 */
	public String getUTF8Const(int index) {
		return pool.getUTF8(index).getValue();
	}

	/**
	 * 获取下标为 index 的值类型(int/float/String)常量 
	 * @param index
	 * @return
	 */
	public Object getValueConst(int index) {
		return pool.getValue(index).getValue();
	}

	public int getMajorVersion() { return majorVersion; }

	public int getMinorVersion() { return minorVersion; }

	/**
	 * 获取剧本对应的 Code
	 * @return
	 */
	public String getCode() { return pool.getUTF8(dramaCodeIndex).getValue(); }

	/**
	 * 获取剧本对应的 Name
	 * @return
	 */
	public String getName() { return pool.getUTF8(dramaNameIndex).getValue(); }

}
