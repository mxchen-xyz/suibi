package com.suibi_ry.project.blog.mapper;

import java.util.List;
import com.suibi_ry.project.blog.domain.SuibiBlogType;

/**
 * 博客类型Mapper接口
 * 
 * @author suibi_ry
 * @date 2020-07-17
 */
public interface SuibiBlogTypeMapper 
{
    /**
     * 查询博客类型
     * 
     * @param typeId 博客类型ID
     * @return 博客类型
     */
    public SuibiBlogType selectSuibiBlogTypeById(Long typeId);

    /**
     * 查询博客类型列表
     * 
     * @param suibiBlogType 博客类型
     * @return 博客类型集合
     */
    public List<SuibiBlogType> selectSuibiBlogTypeList(SuibiBlogType suibiBlogType);

    /**
     * 新增博客类型
     * 
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    public int insertSuibiBlogType(SuibiBlogType suibiBlogType);

    /**
     * 修改博客类型
     * 
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    public int updateSuibiBlogType(SuibiBlogType suibiBlogType);

    /**
     * 删除博客类型
     * 
     * @param typeId 博客类型ID
     * @return 结果
     */
    public int deleteSuibiBlogTypeById(Long typeId);

    /**
     * 批量删除博客类型
     * 
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSuibiBlogTypeByIds(Long[] typeIds);
}
