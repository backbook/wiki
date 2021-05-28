package com.backbook.wiki.controller;


import com.backbook.wiki.req.EbookQueryReq;
import com.backbook.wiki.req.EbookSaveReq;
import com.backbook.wiki.resp.CommonResp;
import com.backbook.wiki.resp.EbookQueryResp;
import com.backbook.wiki.resp.EbookSaveResp;
import com.backbook.wiki.resp.PageResp;
import com.backbook.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<EbookQueryResp>> list(EbookQueryReq ebookQueryReq){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setMessage("this is ebook");
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<PageResp<EbookSaveResp>> save(@RequestBody EbookSaveReq ebookSaveReq){
        CommonResp<PageResp<EbookSaveResp>> resp = new CommonResp<>();
        ebookService.save(ebookSaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<PageResp<EbookSaveResp>> delete(@PathVariable Long id){
        CommonResp<PageResp<EbookSaveResp>> resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }


}
