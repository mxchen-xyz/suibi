package com.suibi_ry.project.blog.service.impl;

import com.suibi_ry.common.utils.DateUtils;
import com.suibi_ry.project.blog.domain.SuibiBlogLabel;
import com.suibi_ry.project.blog.mapper.SuibiBlogLabelMapper;
import com.suibi_ry.project.blog.service.ISuibiBlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *博客标签Service业务层处理
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.service.impl
 * @date 2020/7/21 16:27
 */
@Service
public class SuibiBlogLabelServiceImpl implements ISuibiBlogLabelService {
    @Autowired(required = false)
    private SuibiBlogLabelMapper suibiBlogLabelMapper;

    /**
     * 查询博客标签
     *
     * @param labelId 博客标签ID
     * @return 博客标签
     */
    @Override
    public SuibiBlogLabel selectSuibiBlogLabelById(Long labelId)
    {
        return suibiBlogLabelMapper.selectSuibiBlogLabelById(labelId);
    }

    /**
     * 查询博客标签列表
     *
     * @param suibiBlogLabel 博客标签
     * @return 博客标签
     */
    @Override
    public List<SuibiBlogLabel> selectSuibiBlogLabelList(SuibiBlogLabel suibiBlogLabel)
    {
        return suibiBlogLabelMapper.selectSuibiBlogLabelList(suibiBlogLabel);
    }

    /**
     * 新增博客标签
     *
     * @param suibiBlogLabel 博客标签
     * @return 结果
     */
    @Override
    public int insertSuibiBlogLabel(SuibiBlogLabel suibiBlogLabel)
    {
        suibiBlogLabel.setCreateTime(DateUtils.getNowDate());
        return suibiBlogLabelMapper.insertSuibiBlogLabel(suibiBlogLabel);
    }

    /**
     * 修改博客标签
     *
     * @param suibiBlogLabel 博客标签
     * @return 结果
     */
    @Override
    public int updateSuibiBlogLabel(SuibiBlogLabel suibiBlogLabel)
    {
        suibiBlogLabel.setUpdateTime(DateUtils.getNowDate());
        return suibiBlogLabelMapper.updateSuibiBlogLabel(suibiBlogLabel);
    }

    /**
     * 批量删除博客标签
     *
     * @param labelIds 需要删除的博客标签ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogLabelByIds(Long[] labelIds)
    {
        return suibiBlogLabelMapper.deleteSuibiBlogLabelByIds(labelIds);
    }

    /**
     * 删除博客标签信息
     *
     * @param labelId 博客标签ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogLabelById(Long labelId)
    {
        return suibiBlogLabelMapper.deleteSuibiBlogLabelById(labelId);
    }
}
