package com.ibothub.love.template.lang;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.Test;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/8/9 11:04
 */
public class VfsTests {

    @Test
    public void testStore() throws FileSystemException {
        FileSystemManager fsm = VFS.getManager();
        FileObject fileObject = fsm.resolveFile("file:///D:/Images/assets");
        fileObject.forEach(file->{
            System.out.println(file.getPath());
        });
    }
}
