package com.suibi_ry.common.exception.file;

import com.suibi_ry.common.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author suibi_ry
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
