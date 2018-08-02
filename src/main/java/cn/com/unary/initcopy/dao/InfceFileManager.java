package cn.com.unary.initcopy.dao;

import java.io.IOException;
import java.util.List;

import cn.com.unary.initcopy.entity.FileInfo;

/**
 * 用于持久化文件的相关信息
 * @author shark
 *
 */
public interface InfceFileManager {
	/**
	 * 根据文件Id查询文件信息实体
	 * @param fileId 文件ID
	 * @return 文件信息实体，如没有返回 null
	 */
	FileInfo query (String fileId);
	/**
	 * 根据指定的文件 ID 集合查询出文件信息集合
	 * @param fileIds
	 * @return Id为空时返回空集合
	 * @throws IOException 
	 */
	List<FileInfo> query (List<String> fileIds);
	/**
	 * 保存文件实体信息
	 * @param fi
	 */
	void save (FileInfo fi);
	/**
	 * 保存传进来的数据，如无文件Id，则做新增操作，自动生成Id。
	 * 如有文件Id，则做保存操作。
	 * @param bfis
	 */
	void save (List<FileInfo> fis);
	/**
	 * 根据文件 Id 删除文件实体信息
	 * @param fileId
	 */
	void delete (String fileId);
	/**
	 * 根据文件Id集合删除文件实体信息
	 * @param fileIds
	 */
	void delete (List<String> fileIds);
}
