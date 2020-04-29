package com.github.niefy.modules.wx.manage;

import com.github.niefy.common.utils.R;
import com.github.niefy.modules.wx.form.WxUserBatchTaggingForm;
import com.github.niefy.modules.wx.form.WxUserTagForm;
import com.github.niefy.modules.wx.service.WxUserService;
import com.github.niefy.modules.wx.service.WxUserTagsService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/wxUserTags")
public class WxUserTagsManageController {
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private WxUserTagsService wxUserTagsService;

    /**
     * 查询用户标签
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:wxuser:info")
    public R list() throws WxErrorException {
        List<WxUserTag> wxUserTags =  wxUserTagsService.getWxTags();
        return R.ok().put("list",wxUserTags);
    }

    /**
     * 修改或新增标签
     */
    @PostMapping("/save")
    @RequiresPermissions("wx:wxuser:save")
    public R save(@RequestBody WxUserTagForm form) throws WxErrorException{
        Long tagid = form.getId();
        if(tagid==null || tagid<=0){
            wxUserTagsService.creatTag(form.getName());
        }else {
            wxUserTagsService.updateTag(tagid,form.getName());
        }
        return R.ok();
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete/{tagid}")
    @RequiresPermissions("wx:wxuser:save")
    public R delete(@PathVariable("tagid") Long tagid) throws WxErrorException{
        if(tagid==null || tagid<=0){
            return R.error("标签ID不得为空");
        }
        wxUserTagsService.deleteTag(tagid);
        return R.ok();
    }

    /**
     * 批量给用户打标签
     */
    @PostMapping("/batchTagging")
    @RequiresPermissions("wx:wxuser:save")
    public R batchTagging(@RequestBody WxUserBatchTaggingForm form) throws WxErrorException{
        wxUserTagsService.batchTagging(form.getTagid(),form.getOpenidList());
        return R.ok();
    }
    /**
     * 批量移除用户标签
     */
    @PostMapping("/batchUnTagging")
    @RequiresPermissions("wx:wxuser:save")
    public R batchUnTagging(@RequestBody WxUserBatchTaggingForm form) throws WxErrorException{
        wxUserTagsService.batchUnTagging(form.getTagid(),form.getOpenidList());
        return R.ok();
    }
}
