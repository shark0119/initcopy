package cn.com.unary.initcopy.filecopy.io;

import java.util.List;

import cn.com.unary.initcopy.grpc.entity.DiffFileInfo;

public interface InfceFilePackAndAnaDiff {
	void setFileDiffInfos(List<DiffFileInfo> diffFileInfo);// 设置目标端校验数据，为rsync做准备
}
