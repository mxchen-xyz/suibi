package com.suibi_ry.project.blog.controller;

import java.util.List;

import com.suibi_ry.common.utils.SecurityUtils;
import com.suibi_ry.project.blog.service.ISuibiBlogTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suibi_ry.framework.aspectj.lang.annotation.Log;
import com.suibi_ry.framework.aspectj.lang.enums.BusinessType;
import com.suibi_ry.project.blog.domain.SuibiBlogType;
import com.suibi_ry.framework.web.controller.BaseController;
import com.suibi_ry.framework.web.domain.AjaxResult;
import com.suibi_ry.common.utils.poi.ExcelUtil;
import com.suibi_ry.framework.web.page.TableDataInfo;

/**
 * 博客类型Controller
 * 
 * @author suibi_ry
 * @date 2020-07-17
 */
@RestController
@RequestMapping("/blog/type")
public class SuibiBlogTypeController extends BaseController
{
    @Autowired
    private ISuibiBlogTypeService suibiBlogTypeService;
    /**博客分类 POST 操作**/
    /**
     * 新增博客类型
     */
    @PreAuthorize("@ss.hasPermi('blog:type:add')")
    @Log(title = "博客类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SuibiBlogType suibiBlogType)
    {
        return toAjax(suibiBlogTypeService.insertSuibiBlogType(suibiBlogType));
    }

    /**博客分类 DELETE 操作**/

    /**
     * 删除博客类型
     */
    @PreAuthorize("@ss.hasPermi('blog:type:remove')")
    @Log(title = "博客类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(suibiBlogTypeService.deleteSuibiBlogTypeByIds(typeIds));
    }
    /**博客分类 PUT 操作**/

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('blog:type:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SuibiBlogType type)
    {
        type.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(suibiBlogTypeService.updateTypeStatus(type));
    }

    /**
     * 修改博客类型
     */
    @PreAuthorize("@ss.hasPermi('blog:type:edit')")
    @Log(title = "博客类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuibiBlogType suibiBlogType)
    {
        return toAjax(suibiBlogTypeService.updateSuibiBlogType(suibiBlogType));
    }

    /**博客分类 GET 操作**/

    /**
     * 查询博客类型列表
     */
    @PreAuthorize("@ss.hasPermi('blog:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuibiBlogType suibiBlogType)
    {
        System.out.println("已经进入TableDataInfo");
        startPage();
        List<SuibiBlogType> list = suibiBlogTypeService.selectSuibiBlogTypeList(suibiBlogType);
        return getDataTable(list);
    }

    /**
     * 导出博客类型列表
     */
    @PreAuthorize("@ss.hasPermi('blog:type:export')")
    @Log(title = "博客类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SuibiBlogType suibiBlogType)
    {
        List<SuibiBlogType> list = suibiBlogTypeService.selectSuibiBlogTypeList(suibiBlogType);
        ExcelUtil<SuibiBlogType> util = new ExcelUtil<>(SuibiBlogType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取博客类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(suibiBlogTypeService.selectSuibiBlogTypeById(typeId));
    }



}
