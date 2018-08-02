package cn.com.unary.initcopy.filecopy.io;

import java.util.List;

import cn.com.unary.initcopy.entity.DiffFileChunks;

public interface InfceIODiff extends InfceFile {
	void setDiffFileChunks (List<DiffFileChunks> diffFileChunks);// 设置块信息
}
