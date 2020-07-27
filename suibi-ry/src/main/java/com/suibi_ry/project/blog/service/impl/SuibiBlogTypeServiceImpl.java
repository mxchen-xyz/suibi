package com.suibi_ry.project.blog.service.impl;

import java.util.List;

import com.suibi_ry.common.exception.CustomException;
import com.suibi_ry.common.utils.DateUtils;
import com.suibi_ry.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suibi_ry.project.blog.mapper.SuibiBlogTypeMapper;
import com.suibi_ry.project.blog.domain.SuibiBlogType;
import com.suibi_ry.project.blog.service.ISuibiBlogTypeService;

/**
 * 博客类型Service业务层处理
 * 
 * @author suibi_ry
 * @date 2020-07-17
 */
@Service
public class SuibiBlogTypeServiceImpl implements ISuibiBlogTypeService
{
    @Autowired(required = false)//有就直接注入，没有就不注入
    private SuibiBlogTypeMapper suibiBlogTypeMapper;
    /**博客分类 POST 操作**/
    /**
     * 新增博客类型
     *
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    @Override
    public int insertSuibiBlogType(SuibiBlogType suibiBlogType)
    {
        suibiBlogType.setCreateTime(DateUtils.getNowDate());
        return suibiBlogTypeMapper.insertSuibiBlogType(suibiBlogType);
    }
    /**博客分类 DELETE 操作**/
    /**
     * 批量删除博客类型
     *
     * @param typeIds 需要删除的博客类型ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogTypeByIds(Long[] typeIds)
    {
        for(long typeId : typeIds)  {
            if(typeId == 1000){
                throw new CustomException("选中的项包含默认分类");
            }
        }
        return suibiBlogTypeMapper.deleteSuibiBlogTypeByIds(typeIds);
    }

    /**
     * 删除博客类型信息
     *
     * @param typeId 博客类型ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogTypeById(Long typeId)
    {
        //查询该类型是否能够修改，删除
        if (typeId == 1000)
        {
            throw new CustomException("不允许操作默认分类");
        }
        return suibiBlogTypeMapper.deleteSuibiBlogTypeById(typeId);
    }

    /**博客分类 PUT 操作**/

    /**
     * 修改博客类型
     *
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    @Override
    public int updateSuibiBlogType(SuibiBlogType suibiBlogType)
    {
        //查询该类型是否能够修改，删除
        if (StringUtils.isNotNull(suibiBlogType.getTypeId()) && suibiBlogType.getTypeId() == 1000)
        {
            throw new CustomException("不允许操作默认分类");
        }
        suibiBlogType.setUpdateTime(DateUtils.getNowDate());
        System.out.println(suibiBlogType.getTypeStatus());
        return suibiBlogTypeMapper.updateSuibiBlogType(suibiBlogType);
    }

    @Override
    public int updateTypeStatus(SuibiBlogType type) {
        if(StringUtils.isNotNull(type.getTypeId())&&StringUtils.isEmpty(type.getTypeStatus())){
            throw new CustomException("操作失败");
        }
        //查询该类型是否能够修改，删除
        if (type.getTypeId() == 1000){
            throw new CustomException("不允许操作默认分类");
        }
       return suibiBlogTypeMapper.updateSuibiBlogType(type);
    }


    /**博客分类 GET 操作**/

    /**
     * 查询博客类型
     * 
     * @param typeId 博客类型ID
     * @return 博客类型
     */
    @Override
    public SuibiBlogType selectSuibiBlogTypeById(Long typeId)
    {
        return suibiBlogTypeMapper.selectSuibiBlogTypeById(typeId);
    }

    /**
     * 查询博客类型列表
     * 
     * @param suibiBlogType 博客类型
     * @return 博客类型
     */
    @Override
    public List<SuibiBlogType> selectSuibiBlogTypeList(SuibiBlogType suibiBlogType)
    {
        return suibiBlogTypeMapper.selectSuibiBlogTypeList(suibiBlogType);
    }

}
