package com.suibi_ry.common.exception.file;

/**
 * 文件名称超长限制异常类
 * 
 * @author suibi_ry
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
