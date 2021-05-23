package com.backbook.wiki.controller;


import com.backbook.wiki.req.EbookReq;
import com.backbook.wiki.resp.CommonResp;
import com.backbook.wiki.resp.EbookResp;
import com.backbook.wiki.resp.PageResp;
import com.backbook.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<EbookResp>> list(EbookReq ebookReq){
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(ebookReq);
        resp.setMessage("this is ebook");
        resp.setContent(list);
        return resp;
    }

}
