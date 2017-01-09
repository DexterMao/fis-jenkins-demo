
package com.kakasure;

import org.mybatis.generator.api.ShellRunner;

import com.kakasure.plugin.MyBatisGeneratorPlugin;

public class BqfMbatisGeneratorStart {

	public static void main(String[] args) {
		String config = MyBatisGeneratorPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		String[] arg = { "-configfile", config };
		ShellRunner.main(arg);
	}
}
