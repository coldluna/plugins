package org.grace.luna.principle;

import java.util.List;

/**
 * 系统操作interface
 *
 * @author Artest
 * Created on 2022/8/31 11:42:29
 */
public interface SystemOperations {

    /**
     * 打开指定目录
     *
     * @param directory directory
     * @return 是否打开目录
     */
    boolean cd(String directory);

    /**
     * 打开上级目录
     *
     * @return 是否打开目录
     */
    boolean toParent();

    /**
     * 输出当前所在目录
     *
     * @return 当前所在目录
     */
    String pwd();

    /**
     * 在当前目录下创建新的目录
     *
     * @param directory 目录名
     * @return 是否创建成功
     */
    boolean mkdir(String directory);

    /**
     * 创建指定文件夹及其父目录，从根目录开始创建，创建完成后回到默认的工作目录
     *
     * @param dir 文件夹路径，绝对路径
     */
    void mkDirs(String dir);

    /**
     * 遍历某个目录下所有文件和目录，不会递归遍历
     *
     * @param path 需要遍历的目录
     * @return 文件和目录列表
     */
    List<String> ls(String path);

    /**
     * 文件或目录是否存在
     *
     * @param path 目录
     * @return 是否存在
     */
    boolean exist(String path);

    /**
     * 删除指定目录下的指定文件
     *
     * @param path 目录路径
     * @return 是否存在
     */
    boolean delFile(String path);

    /**
     * 删除文件夹及其文件夹下的所有文件
     *
     * @param dirPath 文件夹路径
     * @return boolean 是否删除成功
     */
    boolean delDir(String dirPath);

}
