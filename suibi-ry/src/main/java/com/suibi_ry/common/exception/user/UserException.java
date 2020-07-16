package com.suibi_ry.common.exception.user;

import com.suibi_ry.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author suibi_ry
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
