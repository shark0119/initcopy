package cn.com.unary.initcopy.filecopy.filepacker;

import java.util.List;

import cn.com.unary.initcopy.grpc.entity.DiffFileInfo;

public interface SyncDiffFilePackAndResolve extends FilePackAndResolve{
	/**
	 * 设置目标端校验数据，为rsync做准备
	 * @param diffFileInfo 目标端文件的校验数据
	 */
	void setFileDiffInfos(List<DiffFileInfo> diffFileInfo);
}
