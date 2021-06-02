package com.backbook.wiki.controller;


import com.backbook.wiki.req.CategoryQueryReq;
import com.backbook.wiki.req.CategorySaveReq;
import com.backbook.wiki.resp.CategoryQueryResp;
import com.backbook.wiki.resp.CategorySaveResp;
import com.backbook.wiki.resp.CommonResp;
import com.backbook.wiki.resp.PageResp;
import com.backbook.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;


    @GetMapping("/all")
    public CommonResp<List<CategoryQueryResp>> all(){
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setMessage("this is category");
        resp.setContent(list);
        return resp;
    }

    //@Valid校验生效
    @GetMapping("/list")
    public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq categoryQueryReq){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(categoryQueryReq);
        resp.setMessage("this is category");
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<PageResp<CategorySaveResp>> save(@Valid @RequestBody CategorySaveReq categorySaveReq){
        CommonResp<PageResp<CategorySaveResp>> resp = new CommonResp<>();
        categoryService.save(categorySaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<PageResp<CategorySaveResp>> delete(@PathVariable Long id){
        CommonResp<PageResp<CategorySaveResp>> resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }


}
