package com.suibi_ry.project.blog.mapper;

import com.suibi_ry.project.blog.domain.SuibiBlogLabel;

import java.util.List;

/**
 * TODO
 *
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.mapper
 * @date 2020/7/21 16:22
 */
public interface SuibiBlogLabelMapper {
    /**
     * 查询博客标签
     *
     * @param labelId 博客标签ID
     * @return 博客标签
     */
    public SuibiBlogLabel selectSuibiBlogLabelById(Long labelId);

    /**
     * 查询博客标签列表
     *
     * @param suibiBlogLabel 博客标签
     * @return 博客标签集合
     */
    public List<SuibiBlogLabel> selectSuibiBlogLabelList(SuibiBlogLabel suibiBlogLabel);

    /**
     * 新增博客标签
     *
     * @param suibiBlogLabel 博客标签
     * @return 结果
     */
    public int insertSuibiBlogLabel(SuibiBlogLabel suibiBlogLabel);

    /**
     * 修改博客标签
     *
     * @param suibiBlogLabel 博客标签
     * @return 结果
     */
    public int updateSuibiBlogLabel(SuibiBlogLabel suibiBlogLabel);

    /**
     * 删除博客标签
     *
     * @param labelId 博客标签ID
     * @return 结果
     */
    public int deleteSuibiBlogLabelById(Long labelId);

    /**
     * 批量删除博客标签
     *
     * @param labelIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSuibiBlogLabelByIds(Long[] labelIds);
}
