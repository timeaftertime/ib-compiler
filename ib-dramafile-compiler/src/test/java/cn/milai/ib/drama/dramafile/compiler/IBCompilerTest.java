package cn.milai.ib.drama.dramafile.compiler;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import org.junit.Test;

import cn.milai.ib.drama.dramafile.compiler.IBCompiler;

public class IBCompilerTest {
	private final byte[] expectedBytes = {
		// IFNTBT
		0x0, 0x6, 0x49, 0x46, 0x4e, 0x54, 0x42, 0x54,
		// majorVersion, minorVersion
		0x0, 0x1, 0x0, 0x0,
		// ConstantPool Size = 22
		0x0, 0x16,
		// [1] UTF8(4) cn.milai.ib.demo.drama.Hello
		0x4, 0x0, 0x1c, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x64, 0x65, 0x6d, 0x6f,
		0x2e, 0x64, 0x72, 0x61, 0x6d, 0x61, 0x2e, 0x48, 0x65, 0x6c, 0x6c, 0x6f,
		// [2] UTF8(4) 测试用的剧本
		0x4, 0x0, 0x12, -0x1a, -0x4b, -0x75, -0x18, -0x51, -0x6b, -0x19, -0x6c, -0x58, -0x19, -0x66, -0x7c, -0x1b,
		-0x77, -0x59, -0x1a, -0x64, -0x54,
		// [3] UTF8(4) main
		0x4, 0x0, 0x4, 0x6d, 0x61, 0x69, 0x6e,
		// [4] UTF8(4) ()V
		0x4, 0x0, 0x3, 0x28, 0x29, 0x56,
		// [5] Int(1) 277
		0x1, 0x0, 0x0, 0x1, 0x15,
		// [6] Int(1) 517
		0x1, 0x0, 0x0, 0x2, 0x5,
		// [7] UTF8(4) /img/speaker.gif
		0x4, 0x0, 0x10, 0x2f, 0x69, 0x6d, 0x67, 0x2f, 0x73, 0x70, 0x65, 0x61, 0x6b, 0x65, 0x72, 0x2e, 0x67, 0x69, 0x66,
		// [8] UTF8(4) new_feature_about_dialog
		0x4, 0x0, 0x18, 0x6e, 0x65, 0x77, 0x5f, 0x66, 0x65, 0x61, 0x74, 0x75, 0x72, 0x65, 0x5f, 0x61, 0x62, 0x6f, 0x75,
		0x74, 0x5f, 0x64, 0x69, 0x61, 0x6c, 0x6f, 0x67,
		// [9] UTF8(4) cn.milai.ib.component.form.DramaDialog
		0x4, 0x0, 0x26, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x6f, 0x6d, 0x70,
		0x6f, 0x6e, 0x65, 0x6e, 0x74, 0x2e, 0x66, 0x6f, 0x72, 0x6d, 0x2e, 0x44, 0x72, 0x61, 0x6d, 0x61, 0x44, 0x69,
		0x61, 0x6c, 0x6f, 0x67,
		// [10] UTF(4) IIPS
		0x4, 0x0, 0x4, 0x49, 0x49, 0x50, 0x53,
		// [11] Int(1) 222
		0x1, 0x0, 0x0, 0x0, -0x22,
		// [12] Int(1) 0
		0x1, 0x0, 0x0, 0x0, 0x0,
		// [13] UTF8(4) cn.milai.ib.character.plane.WelcomePlane
		0x4, 0x0, 0x28, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x57, 0x65, 0x6c, 0x63, 0x6f, 0x6d,
		0x65, 0x50, 0x6c, 0x61, 0x6e, 0x65,
		// [14] UTF8(4) II
		0x4, 0x0, 0x2, 0x49, 0x49,
		// [15] Int(1) -138
		0x1, -0x1, -0x1, -0x1, 0x76,
		// [16] Long(2) 10
		0x2, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xa,
		// [17] Int(1) 111
		0x1, 0x0, 0x0, 0x0, 0x6f,
		// [18] UTF8(4) cn.milai.ib.character.plane.FollowPlane
		0x4, 0x0, 0x27, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x46, 0x6f, 0x6c, 0x6c, 0x6f, 0x77,
		0x50, 0x6c, 0x61, 0x6e, 0x65,
		// [19] Int(1) 444
		0x1, 0x0, 0x0, 0x1, -0x44,
		// [20] Long(2) 20
		0x2, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x14,
		// [21] Int(1) -67
		0x1, -0x1, -0x1, -0x1, -0x43,
		// [22] UTF8(4) cn.milai.ib.character.plane.MissileBoss
		0x4, 0x0, 0x27, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x4d, 0x69, 0x73, 0x73, 0x69, 0x6c,
		0x65, 0x42, 0x6f, 0x73, 0x73,
		// dramaCode[1] dramaName[2]
		0x0, 0x1, 0x0, 0x2,
		// Method Count = 1
		0x0, 0x1,
		// name[3] descriptor[4]
		0x0, 0x3, 0x0, 0x4,
		// method byteSize = 78
		0x0, 0x4e,
		// ldc(8) 5(277)
		0x8, 0x0, 0x5,
		// ldc(8) 6(517)
		0x8, 0x0, 0x6,
		// img(6) 7("/img/speaker.gif")
		0x6, 0x0, 0x7,
		// ldc(8) 8("new_feature_about_dialog")
		0x8, 0x0, 0x8,
		// add(1) DramaDialog(9) IIPS(10)
		0x1, 0x0, 0x9, 0x0, 0xa,
		// ldc(8) 11(222)
		0x8, 0x0, 0xb,
		// ldc(8) 12(0)
		0x8, 0x0, 0xc,
		// add(1) WelcomePlane(13) II(14)
		0x1, 0x0, 0xd, 0x0, 0xe,
		// ldc(8) 5(277)
		0x8, 0x0, 0x5,
		// ldc(8) 15(-138)
		0x8, 0x0, 0xf,
		// add(1) WelcomePlane(13) II(14)
		0x1, 0x0, 0xd, 0x0, 0xe,
		// sleep(2) 10(16)
		0x2, 0x0, 0x10,
		// ldc(8) 17(111)
		0x8, 0x0, 0x11,
		// ldc(8) 12(0)
		0x8, 0x0, 0xc,
		// add(1) FollowPlane(18) II(14)
		0x1, 0x0, 0x12, 0x0, 0xe,
		// ldc(8) 19(444)
		0x8, 0x0, 0x13,
		// ldc(8) 12(0)
		0x8, 0x0, 0xc,
		// add(1) FollowPlane(18) II(14)
		0x1, 0x0, 0x12, 0x0, 0xe,
		// sleep(2) 20(20)
		0x2, 0x0, 0x14,
		// ldc(8) 277(5)
		0x8, 0x0, 0x5,
		// ldc(8) -67(21)
		0x8, 0x0, 0x15,
		// add(1) MissileBoos(22) II(14)
		0x1, 0x0, 0x16, 0x0, 0xe,
	};

	@Test
	public void testCompiler() throws IOException {
		byte[] bytes = IBCompiler.compile(IBCompilerTest.class.getResource("/testIBCompiler.drama").openStream());
		assertArrayEquals(expectedBytes, bytes);
	}

}