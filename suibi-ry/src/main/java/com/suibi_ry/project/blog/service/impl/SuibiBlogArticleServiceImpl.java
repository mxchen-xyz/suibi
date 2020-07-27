package com.suibi_ry.project.blog.service.impl;

import java.util.List;
import com.suibi_ry.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suibi_ry.project.blog.mapper.SuibiBlogArticleMapper;
import com.suibi_ry.project.blog.domain.SuibiBlogArticle;
import com.suibi_ry.project.blog.service.ISuibiBlogArticleService;

/**
 * 博客文章Service业务层处理
 * 
 * @author mxchen
 * @date 2020-07-19
 */
@Service
public class SuibiBlogArticleServiceImpl implements ISuibiBlogArticleService 
{
    @Autowired(required = false)
    private SuibiBlogArticleMapper suibiBlogArticleMapper;

    /**
     * 查询博客文章
     *
     * @param articleId 博客文章ID
     * @return 博客文章
     */
    @Override
    public SuibiBlogArticle selectSuibiBlogArticleById(Long articleId)
    {
        return suibiBlogArticleMapper.selectSuibiBlogArticleById(articleId);
    }

    /**
     * 查询博客文章列表
     *
     * @param suibiBlogArticle 博客文章
     * @return 博客文章
     */
    @Override
    public List<SuibiBlogArticle> selectSuibiBlogArticleList(SuibiBlogArticle suibiBlogArticle)
    {
        return suibiBlogArticleMapper.selectSuibiBlogArticleList(suibiBlogArticle);
    }

    /**
     * 新增博客文章
     *
     * @param suibiBlogArticle 博客文章
     * @return 结果
     */
    @Override
    public int insertSuibiBlogArticle(SuibiBlogArticle suibiBlogArticle)
    {
        suibiBlogArticle.setCreateTime(DateUtils.getNowDate());
        return suibiBlogArticleMapper.insertSuibiBlogArticle(suibiBlogArticle);
    }

    /**
     * 修改博客文章
     *
     * @param suibiBlogArticle 博客文章
     * @return 结果
     */
    @Override
    public int updateSuibiBlogArticle(SuibiBlogArticle suibiBlogArticle)
    {
        suibiBlogArticle.setUpdateTime(DateUtils.getNowDate());
        return suibiBlogArticleMapper.updateSuibiBlogArticle(suibiBlogArticle);
    }

    /**
     * 批量删除博客文章
     *
     * @param articleIds 需要删除的博客文章ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogArticleByIds(Long[] articleIds)
    {
        return suibiBlogArticleMapper.deleteSuibiBlogArticleByIds(articleIds);
    }

    /**
     * 删除博客文章信息
     *
     * @param articleId 博客文章ID
     * @return 结果
     */
    @Override
    public int deleteSuibiBlogArticleById(Long articleId)
    {
        return suibiBlogArticleMapper.deleteSuibiBlogArticleById(articleId);
    }
}
