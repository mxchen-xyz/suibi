package com.suibi_ry.project.blog.controller;

import com.suibi_ry.common.utils.poi.ExcelUtil;
import com.suibi_ry.framework.aspectj.lang.annotation.Log;
import com.suibi_ry.framework.aspectj.lang.enums.BusinessType;
import com.suibi_ry.framework.web.controller.BaseController;
import com.suibi_ry.framework.web.domain.AjaxResult;
import com.suibi_ry.framework.web.page.TableDataInfo;
import com.suibi_ry.project.blog.domain.SuibiBlogLabel;
import com.suibi_ry.project.blog.service.ISuibiBlogLabelService;
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

import java.util.List;

/**
 * TODO
 *
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.controller
 * @date 2020/7/21 16:33
 */
@RestController
@RequestMapping("/blog/label")
public class SuibiBlogLabelController extends BaseController {
    @Autowired
    private ISuibiBlogLabelService suibiBlogLabelService;

    /**
     * 查询博客标签列表
     */
    @PreAuthorize("@ss.hasPermi('blog:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuibiBlogLabel suibiBlogLabel)
    {
        startPage();
        List<SuibiBlogLabel> list = suibiBlogLabelService.selectSuibiBlogLabelList(suibiBlogLabel);
        return getDataTable(list);
    }

    /**
     * 导出博客标签列表
     */
    @PreAuthorize("@ss.hasPermi('blog:label:export')")
    @Log(title = "博客标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SuibiBlogLabel suibiBlogLabel)
    {
        List<SuibiBlogLabel> list = suibiBlogLabelService.selectSuibiBlogLabelList(suibiBlogLabel);
        ExcelUtil<SuibiBlogLabel> util = new ExcelUtil<SuibiBlogLabel>(SuibiBlogLabel.class);
        return util.exportExcel(list, "label");
    }

    /**
     * 获取博客标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:label:query')")
    @GetMapping(value = "/{labelId}")
    public AjaxResult getInfo(@PathVariable("labelId") Long labelId)
    {
        return AjaxResult.success(suibiBlogLabelService.selectSuibiBlogLabelById(labelId));
    }

    /**
     * 新增博客标签
     */
    @PreAuthorize("@ss.hasPermi('blog:label:add')")
    @Log(title = "博客标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SuibiBlogLabel suibiBlogLabel)
    {
        return toAjax(suibiBlogLabelService.insertSuibiBlogLabel(suibiBlogLabel));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('blog:type:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SuibiBlogLabel label)
    {
        return toAjax(suibiBlogLabelService.updateSuibiBlogLabel(label));
    }
    /**
     * 修改博客标签
     */
    @PreAuthorize("@ss.hasPermi('blog:label:edit')")
    @Log(title = "博客标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuibiBlogLabel suibiBlogLabel)
    {
        return toAjax(suibiBlogLabelService.updateSuibiBlogLabel(suibiBlogLabel));
    }

    /**
     * 删除博客标签
     */
    @PreAuthorize("@ss.hasPermi('blog:label:remove')")
    @Log(title = "博客标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{labelIds}")
    public AjaxResult remove(@PathVariable Long[] labelIds)
    {
        return toAjax(suibiBlogLabelService.deleteSuibiBlogLabelByIds(labelIds));
    }
}
