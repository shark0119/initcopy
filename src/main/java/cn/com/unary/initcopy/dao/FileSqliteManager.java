package cn.com.unary.initcopy.dao;

import java.util.List;

import cn.com.unary.initcopy.entity.FileInfo;

/**
 * 文件信息管理的基于 Sqlite 的实现
 * @author shark
 *
 */
public class FileSqliteManager implements InfceFileManager {

	@Override
	public FileInfo query(String fileId) {
		
		return null;
	}

	@Override
	public List<FileInfo> query(List<String> fileIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(FileInfo fi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(List<FileInfo> fis) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String fileId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(List<String> fileIds) {
		// TODO Auto-generated method stub

	}

}
