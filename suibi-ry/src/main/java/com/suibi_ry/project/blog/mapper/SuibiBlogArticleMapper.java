package com.suibi_ry.project.blog.mapper;

import java.util.List;
import com.suibi_ry.project.blog.domain.SuibiBlogArticle;

/**
 * 博客文章Mapper接口
 * 
 * @author mxchen
 * @date 2020-07-19
 */
public interface SuibiBlogArticleMapper 
{
    /**
     * 查询博客文章
     * 
     * @param articleId 博客文章ID
     * @return 博客文章
     */
    public SuibiBlogArticle selectSuibiBlogArticleById(Long articleId);

    /**
     * 查询博客文章列表
     * 
     * @param suibiBlogArticle 博客文章
     * @return 博客文章集合
     */
    public List<SuibiBlogArticle> selectSuibiBlogArticleList(SuibiBlogArticle suibiBlogArticle);

    /**
     * 新增博客文章
     * 
     * @param suibiBlogArticle 博客文章
     * @return 结果
     */
    public int insertSuibiBlogArticle(SuibiBlogArticle suibiBlogArticle);

    /**
     * 修改博客文章
     * 
     * @param suibiBlogArticle 博客文章
     * @return 结果
     */
    public int updateSuibiBlogArticle(SuibiBlogArticle suibiBlogArticle);

    /**
     * 删除博客文章
     * 
     * @param articleId 博客文章ID
     * @return 结果
     */
    public int deleteSuibiBlogArticleById(Long articleId);

    /**
     * 批量删除博客文章
     * 
     * @param articleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSuibiBlogArticleByIds(Long[] articleIds);
}
