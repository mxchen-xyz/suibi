package com.suibi_ry.project.blog.service;

import java.util.List;
import com.suibi_ry.project.blog.domain.SuibiBlogType;

/**
 * 博客类型Service接口
 * 
 * @author mxchen
 * @date 2020-07-17
 */
public interface ISuibiBlogTypeService
{
    /**博客分类 POST 操作**/

    /**
     * 新增博客类型
     *
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    int insertSuibiBlogType(SuibiBlogType suibiBlogType);


    /**博客分类 DELETE 操作**/

    /**
     * 批量删除博客类型
     *
     * @param typeIds 需要删除的博客类型ID
     * @return 结果
     */
    int deleteSuibiBlogTypeByIds(Long[] typeIds);

    /**
     * 删除博客类型信息
     *
     * @param typeId 博客类型ID
     * @return 结果
     */
    int deleteSuibiBlogTypeById(Long typeId);

    /**博客分类 PUT 操作**/

    /**
     * 修改博客类型
     *
     * @param suibiBlogType 博客类型
     * @return 结果
     */
    int updateSuibiBlogType(SuibiBlogType suibiBlogType);
    int updateTypeStatus(SuibiBlogType type);

    /**博客分类 GET 操作**/
    /**
     * 查询博客类型
     * 
     * @param typeId 博客类型ID
     * @return 博客类型
     */
    SuibiBlogType selectSuibiBlogTypeById(Long typeId);

    /**
     * 查询博客类型列表
     * 
     * @param suibiBlogType 博客类型
     * @return 博客类型集合
     */
    List<SuibiBlogType> selectSuibiBlogTypeList(SuibiBlogType suibiBlogType);



}
