package com.suibi_ry.project.blog.controller;

import com.suibi_ry.common.utils.SecurityUtils;
import com.suibi_ry.common.utils.poi.ExcelUtil;
import com.suibi_ry.framework.aspectj.lang.annotation.Log;
import com.suibi_ry.framework.aspectj.lang.enums.BusinessType;
import com.suibi_ry.framework.web.domain.AjaxResult;
import com.suibi_ry.framework.web.page.TableDataInfo;
import com.suibi_ry.project.blog.domain.SuibiBlogArticle;
import com.suibi_ry.project.blog.service.ISuibiBlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suibi_ry.framework.web.controller.BaseController;

import java.util.List;

/**
 * 博客文章Controller
 *
 * @author mxchen
 * @date 2020-07-19
 */
@RestController
@RequestMapping("/blog/article")
public class SuibiBlogArticleController extends BaseController {
    @Autowired
    private ISuibiBlogArticleService suibiBlogArticleService;

    /**
     * 查询博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuibiBlogArticle suibiBlogArticle) {
        startPage();
        List<SuibiBlogArticle> list = suibiBlogArticleService.selectSuibiBlogArticleList(suibiBlogArticle);
        return getDataTable(list);
    }

    /**
     * 导出博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:export')")
    @Log(title = "博客文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SuibiBlogArticle suibiBlogArticle) {
        List<SuibiBlogArticle> list = suibiBlogArticleService.selectSuibiBlogArticleList(suibiBlogArticle);
        ExcelUtil<SuibiBlogArticle> util = new ExcelUtil<SuibiBlogArticle>(SuibiBlogArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取博客文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
        return AjaxResult.success(suibiBlogArticleService.selectSuibiBlogArticleById(articleId));
    }

    /**
     * 新增博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:add')")
    @Log(title = "博客文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SuibiBlogArticle suibiBlogArticle) {
        suibiBlogArticle.setUserId(SecurityUtils.getLonginUserId());
        return toAjax(suibiBlogArticleService.insertSuibiBlogArticle(suibiBlogArticle));
    }

    /**
     * 修改博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:edit')")
    @Log(title = "博客文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuibiBlogArticle suibiBlogArticle) {
        return toAjax(suibiBlogArticleService.updateSuibiBlogArticle(suibiBlogArticle));
    }

    /**
     * 删除博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:remove')")
    @Log(title = "博客文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds) {
        return toAjax(suibiBlogArticleService.deleteSuibiBlogArticleByIds(articleIds));
    }
}
