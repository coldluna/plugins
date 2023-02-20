package org.grace.luna.template.ftp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.grace.luna.principle.FileOperations;
import org.grace.luna.principle.SystemOperations;

import java.util.List;

/**
 * 文件操作template
 *
 * @author Artest
 * Created on 2022/8/31 12:02:38
 */
public abstract class FileTemplate implements FileOperations, SystemOperations {

    /**
     * 打开上级目录
     *
     * @return 是否打开目录
     */
    public boolean toParent() {
        return cd("..");
    }

    /**
     * 创建指定文件夹及其父目录，从根目录开始创建，创建完成后回到默认的工作目录
     *
     * @param dir 文件夹路径，绝对路径
     */
    public void mkDirs(String dir) {
        final String[] dirs = StrUtil.trim(dir).split("[\\\\/]+");

        final String now = pwd();
        if (dirs.length > 0 && StrUtil.isEmpty(dirs[0])) {
            //首位为空，表示以/开头
            this.cd(StrUtil.SLASH);
        }
        for (int i = 0; i < dirs.length; i++) {
            if (StrUtil.isNotEmpty(dirs[i])) {
                if (!cd(dirs[i])) {
                    //目录不存在时创建
                    mkdir(dirs[i]);
                    cd(dirs[i]);
                }
            }
        }
        // 切换回工作目录
        cd(now);
    }

    /**
     * 文件或目录是否存在
     *
     * @param path 目录
     * @return 是否存在
     */
    public boolean exist(String path) {
        final String fileName = FileUtil.getName(path);
        final String dir = StrUtil.removeSuffix(path, fileName);
        final List<String> names = ls(dir);
        return containsIgnoreCase(names, fileName);
    }

    /**
     * 是否包含指定字符串，忽略大小写
     *
     * @param names      文件或目录名列表
     * @param nameToFind 要查找的文件或目录名
     * @return 是否包含
     */
    protected boolean containsIgnoreCase(List<String> names, String nameToFind) {
        if (CollUtil.isEmpty(names)) {
            return false;
        }
        if (StrUtil.isEmpty(nameToFind)) {
            return false;
        }
        for (String name : names) {
            if (nameToFind.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
