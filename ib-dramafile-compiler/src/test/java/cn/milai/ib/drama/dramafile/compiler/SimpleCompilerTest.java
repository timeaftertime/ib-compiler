package cn.milai.ib.drama.dramafile.compiler;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import org.junit.Test;

public class SimpleCompilerTest {

	private final byte[] testSimpleCompilerTxt = {
		// IFNTBT
		0x0, 0x6, 0x49, 0x46, 0x4e, 0x54, 0x42, 0x54,
		// majorVersion, minorVersion
		0x0, 0x1, 0x0, 0x0,
		// dramaCode(18) dramaName(19)
		0x0, 0x11, 0x0, 0x12,
		// ConstantPool Size = 18
		0x0, 0x12,
		// [1] UTF8(4) cn.milai.ib.component.form.DramaDialog
		0x4, 0x0, 0x26, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x6f, 0x6d, 0x70,
		0x6f,
		0x6e, 0x65, 0x6e, 0x74, 0x2e, 0x66, 0x6f, 0x72, 0x6d, 0x2e, 0x44, 0x72, 0x61, 0x6d, 0x61, 0x44, 0x69, 0x61,
		0x6c, 0x6f, 0x67,
		// [2] Float(3) 0.5
		0x3, 0x3f, 0x0, 0x0, 0x0,
		// [3] Float(3) 0.75
		0x3, 0x3f, 0x40, 0x0, 0x0,
		// [4] UTF8(4) cn.milai.ib.character.plane.PlayerPlane
		0x4, 0x0, 0x27, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x50, 0x6c, 0x61, 0x79, 0x65, 0x72,
		0x50, 0x6c, 0x61, 0x6e, 0x65,
		// [5] UTF8(4) new_feature_about_dialog
		0x4, 0x0, 0x18, 0x6e, 0x65, 0x77, 0x5f, 0x66, 0x65, 0x61, 0x74, 0x75, 0x72, 0x65, 0x5f, 0x61, 0x62, 0x6f, 0x75,
		0x74, 0x5f, 0x64, 0x69, 0x61, 0x6c, 0x6f, 0x67,
		// [6] UTF8(4) cn.milai.ib.character.plane.WelcomePlane
		0x4, 0x0, 0x28, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x57, 0x65, 0x6c, 0x63, 0x6f, 0x6d,
		0x65, 0x50, 0x6c, 0x61, 0x6e, 0x65,
		// [7] Float(3) 0.4
		0x3, 0x3e, -0x34, -0x34, -0x33,
		// [8] Float(3) 0
		0x3, 0x0, 0x0, 0x0, 0x0,
		// [9] Float(3) -0.2
		0x3, -0x42, 0x4c, -0x34, -0x33,
		// [10] Long(2) 10
		0x2, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xa,
		// [11] UTF8(4) cn.milai.ib.character.plane.FollowPlane
		0x4, 0x0, 0x27, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x46, 0x6f, 0x6c, 0x6c, 0x6f, 0x77,
		0x50, 0x6c, 0x61, 0x6e, 0x65,
		// [12] Float(3) 0.2
		0x3, 0x3e, 0x4c, -0x34, -0x33,
		// [13] Float(3) 0.8
		0x3, 0x3f, 0x4c, -0x34, -0x33,
		// [14] Long(2) 20
		0x2, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x14,
		// [15] UTF8(4) cn.milai.ib.character.plane.MissileBoss
		0x4, 0x0, 0x27, 0x63, 0x6e, 0x2e, 0x6d, 0x69, 0x6c, 0x61, 0x69, 0x2e, 0x69, 0x62, 0x2e, 0x63, 0x68, 0x61, 0x72,
		0x61, 0x63, 0x74, 0x65, 0x72, 0x2e, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x4d, 0x69, 0x73, 0x73, 0x69, 0x6c,
		0x65, 0x42, 0x6f, 0x73, 0x73,
		// [16] Float(3) -0.1
		0x3, -0x43, -0x34, -0x34, -0x33,
		// [17] UTF8(4) testDialog
		0x4, 0x0, 0xa, 0x74, 0x65, 0x73, 0x74, 0x44, 0x69, 0x61, 0x6c, 0x6f, 0x67,
		// [18] UTF8(4) 测试用的剧本
		0x4, 0x0, 0x12, -0x1a, -0x4b, -0x75, -0x18, -0x51, -0x6b, -0x19, -0x6c, -0x58, -0x19, -0x66, -0x7c, -0x1b,
		-0x77, -0x59, -0x1a, -0x64, -0x54,
		// dialog(3)  DramaDialog(1)  0.5(2) 0.75(3) PlayerPlane(4) new_feature_about_dialog(5)
		0x3, 0x0, 0x1, 0x0, 0x2, 0x0, 0x3, 0x0, 0x4, 0x0, 0x5,
		// add(1) WelcomePlane(6) 0.4(7) 0(8)
		0x1, 0x0, 0x6, 0x0, 0x7, 0x0, 0x8,
		// add(1) WelcomePlane(6) 0.5(2) -0.2(9)
		0x1, 0x0, 0x6, 0x0, 0x2, 0x0, 0x9,
		// sleep(2) 10(10)
		0x2, 0x0, 0xa,
		// add(1) FollowPlane(11) 0.2(12) 0(8)
		0x1, 0x0, 0xb, 0x0, 0xc, 0x0, 0x8,
		// add(1) FollowPlane(11) 0.8(13) 0(8)
		0x1, 0x0, 0xb, 0x0, 0xd, 0x0, 0x8,
		// sleep(2) 20(14)
		0x2, 0x0, 0xe,
		// add(1) MissileBoss(15) 0.5(2) -0.1(16)
		0x1, 0x0, 0xf, 0x0, 0x2, 0x0, 0x10,
	};

	@Test
	public void testCompiler() throws IOException {
		byte[] bytes = SimpleCompiler.compile(
			SimpleCompilerTest.class.getResource("/testSimpleCompiler.drama")
				.openStream()
		);
		assertArrayEquals(testSimpleCompilerTxt, bytes);
	}

}
